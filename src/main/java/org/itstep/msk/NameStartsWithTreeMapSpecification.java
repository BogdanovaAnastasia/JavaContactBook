package org.itstep.msk;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NameStartsWithTreeMapSpecification implements TreeMapSpecification {
    private final String prefix;

    public NameStartsWithTreeMapSpecification(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public Iterable<Contact> read(Map<String, NameComparableContact> map) {
        Set<String> keys = map.keySet();
        Set<NameComparableContact> setToRead = new HashSet<>();

        setToRead.clear();

        for (String key : keys) {
            List<String> keyStringMore = new ArrayList<>();
            keyStringMore.addAll(Arrays.asList(key.split(" ")));

            if (keyStringMore.size() > 1) {

                for (String mapKey : keyStringMore) {
                    if (mapKey.startsWith(prefix)) {
                        setToRead.add(map.get(key));
                        break;
                    }
                }

                } else{
                    if (key.startsWith(prefix))
                        map.remove(map.get(key));
                }
            }
        return setToRead.size() == 0 ? Collections.emptyList() : setToRead.stream().map(NameComparableContact::extract).collect(Collectors.toList());
    }

    @Override
    public void delete(Map<String, NameComparableContact> map) {
        Set<String> keys = map.keySet();

        for (String key : keys) {
            List<String> keyStringMore = new ArrayList<>();
            keyStringMore.addAll(Arrays.asList(key.split(" ")));
            if (keyStringMore.size() > 1) {
                Map<String, Contact> mapKeyStringMore = new TreeMap<>();
                for (String mapKey : keyStringMore) {
                    if (mapKey.startsWith(prefix)) {
                        map.remove(map.get(key));
                        break;
                    }
                }
            } else {
                if (key.startsWith(prefix))
                    map.remove(map.get(key));
            }
        }
    }

    @Override
    public boolean isSatisfying(Contact c) {
        return Stream.of(c.getName().split(" ")).anyMatch(prefix::startsWith);
    }
}
