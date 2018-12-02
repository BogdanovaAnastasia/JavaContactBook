package org.itstep.msk;

public class TreeMapBackedStartsWithFactory implements FindByNameSpecificationFactory{
    @Override
    public ContactSpecification create(String prefix) {
        return new NameStartsWithTreeMapSpecification(prefix);
    }
}
