package org.itstep.msk;

import java.util.stream.Stream;

/**
 * Constructs an new ContactSpecification object for the most simple repository ArraySpecificationContactBook
 *
 * @author Марк Михайлович
 * @version 1.0
 * */
public final class ArrayBackedFindByNameFactory implements FindByNameSpecificationFactory {
    @Override
    public ContactSpecification create(String name) {
        return c->Stream.of(c.getName().split(" ")).anyMatch(name::equals);
    }
}
