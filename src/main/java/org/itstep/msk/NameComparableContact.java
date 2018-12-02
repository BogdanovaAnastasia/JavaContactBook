package org.itstep.msk;

public class NameComparableContact implements Comparable<Contact>{
    private final Contact origin;

    public NameComparableContact(Contact origin) {
        this.origin = origin;
    }

    public Contact extract() {
        return origin;
    }

    @Override
    public int compareTo(Contact anotherContact) {
        String anotherString = anotherContact.getName();
        int res = -1;
        for (int k = 0; k != this.extract().getName().length(); k++) {
            res = this.extract().getName().charAt(k) - anotherString.charAt(k);
        }
        return res == 0 ? this.extract().getName().length() - anotherString.length() : res;
    }
}
