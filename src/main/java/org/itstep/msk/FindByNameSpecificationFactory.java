package org.itstep.msk;

/**
 * An interface of the factory to create different types of ContactSpecification
 * intended to be utilized with different repositories
 * Each repository needs to provide such a factory if one is expecting to be used in any
 * client algorithm
 *
 * Find by name algorithm should find an exact match of any world in the Contact name
 * with the String name accepted in the create method of the factory
 *
 * @author Марк Михайлович
 * @version 1.0
 * */
public interface FindByNameSpecificationFactory {

    /**
     * Returns a concrete implementation of the ContactSpecification constructed by the concrete factory
     *
     * @param name a string to be searched for exact match with any word of the Contact name
     * @return     ContactSpecification implementing searching algorithm for the concrete repository
     * */
    ContactSpecification create(String name);
}
