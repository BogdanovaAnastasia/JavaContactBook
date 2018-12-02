package org.itstep.msk;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Specification to find name exactly matching any word of the Contact name
 *
 * @author BogdanovaAnastasia
 * @version 1.0
 * */

public class FindByNameTreeMapSpecification implements TreeMapSpecification {
    private final String name;

    public FindByNameTreeMapSpecification(String name) {
        this.name = name;
    }

    @Override
    public Iterable<Contact> read(Map<String, NameComparableContact> map) {
        Set<String> keys = map.keySet();
        Set<NameComparableContact> setToRead = new HashSet<>();
        setToRead.clear();
        boolean containsKey = false;
        for (String key : keys) {
            List<String> keyStringMore = new ArrayList<>();
            keyStringMore.addAll(Arrays.asList(key.split(" ")));
            if (keyStringMore.size() > 1) {
                Map<String, Contact> mapKeyStringMore = new TreeMap<>();
                for (String mapKey : keyStringMore) {
                    mapKeyStringMore.put(mapKey, map.get(key).extract());
                }
                containsKey = mapKeyStringMore.containsKey(name);
            } else {
                containsKey = map.containsKey(name);
            }
            if (containsKey) setToRead.add(map.get(key));
        }
        return setToRead.size() == 0 ? Collections.emptyList() : setToRead.stream().map(NameComparableContact::extract).collect(Collectors.toList());
    }

    @Override
    public void delete(Map<String, NameComparableContact> map) {
        Set<String> keys = map.keySet();
        boolean containsKey = false;
        for (String key : keys) {
            List<String> keyStringMore = new ArrayList<>();
            keyStringMore.addAll(Arrays.asList(key.split(" ")));
            if (keyStringMore.size() > 1) {
                Map<String, Contact> mapKeyStringMore = new TreeMap<>();
                for (String mapKey : keyStringMore) {
                    mapKeyStringMore.put(mapKey, map.get(key).extract());
                }
                containsKey = mapKeyStringMore.containsKey(name);
            } else {
                containsKey = map.containsKey(name);
            }
            if (containsKey) map.remove(map.get(key));
        }
    }

    @Override
    public boolean isSatisfying(Contact c) {
        return Stream.of(c.getName().split(" ")).anyMatch(name::equals);
    }
}
