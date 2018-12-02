package org.itstep.msk;

import java.util.Map;

public interface TreeMapSpecification extends ContactSpecification{
    Iterable<Contact> read(Map<String,NameComparableContact> map);

    void delete(Map<String, NameComparableContact> map);
}
