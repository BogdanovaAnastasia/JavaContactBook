package org.itstep.msk;

public class TreeMapBackedFindByNameFactory implements FindByNameSpecificationFactory {
    @Override
    public ContactSpecification create(String name) {
        return new FindByNameTreeMapSpecification(name);
    }
}
