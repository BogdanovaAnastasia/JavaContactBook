package org.itstep.msk;

/**
 * The next step in the repository pattern for accessing the data
 * SpecificationRepository adds special overload methods for read and delete
 * Those methods accept ContactSpecification, an interface need to be implemented for each concrete query
 *
 * @author Марк Михайлович
 * @version 1.0
 * */
public interface SpecificationContactBook extends SimpleContactBook {
    /**
     * Finds all contacts satisfying condition given by the ContactSpecification
     *
     * @param spec the specification of the contact to be find
     * @return     all contacts satisfying the given specification
     * @see ContactSpecification
     * */
    Iterable<Contact> read(ContactSpecification spec);

    /**
     * Removes all contacts satisfying condition given by the ContactSpecification
     *
     * @param spec the specification of the contact to be find
     * @return     modified SpecificationContactBook
     * @see ContactSpecification
     * */
    SpecificationContactBook delete(ContactSpecification spec);
}
