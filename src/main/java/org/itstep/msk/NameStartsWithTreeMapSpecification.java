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

    public void addContactToMapSet(TreeMap<String,ArrayList<Contact>> mapSet, String uniqueName, Contact contact){
        if(!mapSet.containsKey(uniqueName)){
            mapSet.put(uniqueName,new ArrayList<>());
        }
        mapSet.get(uniqueName).add(contact);
    }
    public String getHigherPrefix(String prefix){
        char higerPrefixCharArr[] = prefix.toCharArray();
        higerPrefixCharArr [prefix.length()-1] = (char) (prefix.charAt(prefix.length()-1)+'\u0001');
        String higerPrefix = new String (higerPrefixCharArr);
        return higerPrefix;
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
        ArrayList<Contact> listToRead = new ArrayList<>();
             Collection<ArrayList<Contact>> lstColl = mapSet
                     .subMap(prefix,true,(getHigherPrefix(prefix)),false).values();
             for (ArrayList<Contact> arrLst:lstColl){
                     listToRead.addAll(arrLst);
             }
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
        ArrayList<Contact> listToDelete = new ArrayList<>();
        Collection<ArrayList<Contact>> lstColl = mapSet
                .subMap(prefix,true,(getHigherPrefix(prefix)),false).values();
        for (ArrayList<Contact> arrLst:lstColl){
            if(!listToDelete.contains(arrLst)){
                listToDelete.addAll(arrLst);
            }
        }
        for(Contact contact:listToDelete){
            map.remove(contact.getName());
        }
    }

    @Override
    public boolean isSatisfying(Contact c) {
        return Stream.of(c.getName().split(" ")).anyMatch(prefix::startsWith);
    }
}
