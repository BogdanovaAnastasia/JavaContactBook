package org.itstep.msk;

import java.util.Map;

public interface TreeMapSpecification extends ContactSpecification{
    Iterable<Contact> read(Map<String,Contact> map);

    void delete(Map<String,Contact> map);
}
