package org.itstep.msk;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Specification to find prefix wich any word of the Contact starts from
 *
 * @author BogdanovaAnastasia
 * @version 1.0
 * */
public class NameStartsWithTreeMapSpecification implements TreeMapSpecification {
    private final String prefix;

    public NameStartsWithTreeMapSpecification(String prefix) {
        this.prefix = prefix;
    }

    public void addContactToMapSet(TreeMap<String, ArrayList<Contact>> mapSet, String uniqueName, Contact contact) {
        if (!mapSet.containsKey(uniqueName)) {
            mapSet.put(uniqueName, new ArrayList<>());
        }
        mapSet.get(uniqueName).add(contact);
    }

    public String getHigherPrefix(String prefix) {
        char higerPrefixCharArr[] = prefix.toCharArray();
        higerPrefixCharArr[prefix.length() - 1] = (char) (prefix.charAt(prefix.length() - 1) + '\u0001');
        return new String(higerPrefixCharArr);
    }

    @Override
    public Iterable<Contact> read(Map<String, Contact> map) {
        TreeMap<String, ArrayList<Contact>> mapSet = new TreeMap<>();
        Set<String> keys = map.keySet();
        keys.forEach(key -> Arrays.stream(key.split(" ")).forEach(string -> addContactToMapSet(mapSet, string, map.get(key))));
        Set<Contact> listToRead = new HashSet<>();
        mapSet.subMap(prefix, true, (getHigherPrefix(prefix)), false).values().forEach(x -> listToRead.addAll(x));
        return listToRead.size() == 0 ? Collections.emptyList() : listToRead;
    }

    @Override
    public void delete(Map<String, Contact> map) {
        TreeMap<String, ArrayList<Contact>> mapSet = new TreeMap<>();
        Set<String> keys = map.keySet();
        keys.forEach(key -> Arrays.stream(key.split(" ")).forEach(string -> addContactToMapSet(mapSet, string, map.get(key))));
        ArrayList<Contact> listToDelete = new ArrayList<>();
        mapSet.subMap(prefix, true, (getHigherPrefix(prefix)), false).values().forEach(x -> listToDelete.addAll(x));
        listToDelete.forEach(c -> map.remove(c.getName()));
    }

    @Override
    public boolean isSatisfying(Contact c) {
        return Stream.of(c.getName().split(" ")).anyMatch(prefix::startsWith);
    }
}
