package org.itstep.msk;

import java.util.Collections;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * In memory repository baked by a TreeMap
 *
 * @author BogdanovaAnastasia
 * @version 1.0
 * */
public class TreeMapContactBook implements SpecificationContactBook {
    private final TreeMap<String, NameComparableContact> nameKey;

    public TreeMapContactBook(TreeMap<String, NameComparableContact> nameKey) {
        this.nameKey = nameKey;
    }

    @Override
    public Iterable<Contact> read(ContactSpecification spec) {
        if(((TreeMapSpecification) spec).read(nameKey).equals(Collections.emptyList())){
            System.out.println("Совпадений не найдено");
        }
        return ((TreeMapSpecification) spec).read(nameKey);
    }

    @Override
    public SpecificationContactBook delete(ContactSpecification spec) {
        ((TreeMapSpecification) spec).delete(nameKey);
        return this;
    }

    @Override
    public Contact create(String name, String phoneNumber) {
        Contact res = new Contact(name, phoneNumber);
        nameKey.put(res.getName(), new NameComparableContact(res));
        return res;
    }

    @Override
    public SimpleContactBook delete(Contact c) {
        nameKey.remove(c.getName());
        return this;
    }

    @Override
    public Iterable<Contact> read() {
        return nameKey.values().stream().map(NameComparableContact::extract).collect(Collectors.toList());
    }

    @Override
    public SimpleContactBook commit() {
        return null;
    }
}
