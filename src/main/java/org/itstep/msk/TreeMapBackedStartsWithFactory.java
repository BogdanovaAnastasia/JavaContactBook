package org.itstep.msk;

/**
 * Constructs an new ContactSpecification object for the repository TreeMapContactBook
 *
 * @author BogdanovaAnastasia
 * @version 1.0
 * */
public class TreeMapBackedStartsWithFactory implements FindByNameSpecificationFactory{

    /**
     * Returns a NameStartsWithTreeMapSpecification constructed by the concrete factory
     *
     * @param prefix a string to be searched for at the beginning of any word of the Contact name
     * @return     ContactSpecification implementing searching algorithm for the concrete repository
     * */
    @Override
    public ContactSpecification create(String prefix) {
        return new NameStartsWithTreeMapSpecification(prefix);
    }
}
