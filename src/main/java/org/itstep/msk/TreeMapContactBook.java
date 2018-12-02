package org.itstep.msk;

import java.util.TreeMap;

public class TreeMapContactBook implements SpecificationContactBook {
    private final TreeMap<String, Contact> nameKey;

    public TreeMapContactBook(TreeMap<String, Contact> nameKey) {
        this.nameKey = nameKey;
    }

    @Override
    public Iterable<Contact> read(ContactSpecification spec) {
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
        nameKey.put(res.getName(), res);
        return res;
    }

    @Override
    public SimpleContactBook delete(Contact c) {
        nameKey.remove(c.getName());
        return this;
    }

    @Override
    public Iterable<Contact> read() {
        return nameKey.values();
    }

    @Override
    public SimpleContactBook commit() {
        return null;
    }
}
