package org.itstep.msk;

/**
 * Constructs an new ContactSpecification object for the repository TreeMapContactBook
 *
 * @author BogdanovaAnastasia
 * @version 1.0
 * */
public class TreeMapBackedFindByNameFactory implements FindByNameSpecificationFactory {

    /**
     * Returns a FindByNameTreeMapSpecification constructed by the concrete factory
     *
     * @param name a string to be searched for exact match with any word of the Contact name
     * @return     ContactSpecification implementing searching algorithm for the concrete repository
     * */
    @Override
    public ContactSpecification create(String name) {
        return new FindByNameTreeMapSpecification(name);
    }
}
