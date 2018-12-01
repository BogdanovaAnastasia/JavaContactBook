package org.itstep.msk;

/**
 * The base interface of the Specification to describe conditions for the Contacts
 *
 * @author Марк Михайлович
 * @version 1.0
 * */
public interface ContactSpecification {
    /**
     * The main idea is to check any contact to satisfy condition the concrete object represents
     *
     * @param c the contact to be checked
     * @return  true if c satisfies the condition the object represents
     * @see Contact
     * */
    boolean isSatisfying(Contact c);
}
