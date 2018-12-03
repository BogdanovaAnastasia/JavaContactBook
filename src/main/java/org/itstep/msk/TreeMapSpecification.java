package org.itstep.msk;

import java.util.Map;

        /**
        * Interface of the Specification to describe conditions for TreeMapContactBook
        *
        * @author BogdanovaAnastasia
        * @version 1.0
        * */
public interface TreeMapSpecification extends ContactSpecification{

    Iterable<Contact> read(Map<String,Contact> map);

    void delete(Map<String, Contact> map);
}
