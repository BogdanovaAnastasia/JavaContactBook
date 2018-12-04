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

    public void addContactToMapSet(TreeMap<String,ArrayList<Contact>> mapSet, String uniqueName, Contact contact){
        if(!mapSet.containsKey(uniqueName)){
            mapSet.put(uniqueName,new ArrayList<>());
        }
        mapSet.get(uniqueName).add(contact);
    }

    @Override
    public Iterable<Contact> read(Map<String, Contact> map) {
        TreeMap<String,ArrayList<Contact>> mapSet = new TreeMap<>();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            String strArr[] = key.split(" ");
            for(String str: strArr){
                addContactToMapSet(mapSet,str,map.get(key));
            }
        }
        ArrayList<Contact> listToRead = mapSet.get(name);
        return listToRead.size() == 0 ? Collections.emptyList() : listToRead;
    }

    @Override
    public void delete(Map<String, Contact> map) {
        TreeMap<String,ArrayList<Contact>> mapSet = new TreeMap<>();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            String strArr[] = key.split(" ");
            for(String str: strArr){
                addContactToMapSet(mapSet,str,map.get(key));
            }
        }
        ArrayList<Contact> listToDelete = mapSet.get(name);
        for(Contact contact:listToDelete){
            map.remove(contact.getName());
        }
    }

    @Override
    public boolean isSatisfying(Contact c) {
        return Stream.of(c.getName().split(" ")).anyMatch(name::equals);
    }
}
