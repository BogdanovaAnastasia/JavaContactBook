package org.itstep.msk;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Simple repository for in-memory collection of contacts implementing SpecificationContactBook backed by ArrayContactBook
 * */
public class ArraySpecificationContactBook implements SpecificationContactBook {
    /**
     * Original ContactBook backed by ArrayList
     * */
    private final ArrayContactBook arrayBook;

    public ArraySpecificationContactBook(ArrayContactBook arrayBook) {
        this.arrayBook = arrayBook;
    }

    @Override
    public Iterable<Contact> read(ContactSpecification spec) {
        return StreamSupport.stream(arrayBook.read().spliterator(),false)
                .filter(spec::isSatisfying)
                .collect(Collectors.toList());
    }

    @Override
    public SpecificationContactBook delete(ContactSpecification spec) {
        List<Contact> toDelete = new ArrayList<>();
        for (Contact c : arrayBook.read())
            if (spec.isSatisfying(c)) toDelete.add(c);
        for (Contact c : toDelete)
            arrayBook.delete(c);
        return this;
    }

    @Override
    public Contact create(String name, String phoneNumber) {
        return arrayBook.create(name,phoneNumber);
    }

    @Override
    public SimpleContactBook delete(Contact c) {
        return arrayBook.delete(c);
    }

    @Override
    public Iterable<Contact> read() {
        return arrayBook.read();
    }

    @Override
    public SimpleContactBook commit() {
        return this;
    }
}
