package common;

import static java.lang.Long.parseLong;

public class Isbn {

    public final String number;

    public Isbn(String number) {
        this.number = number;
    }

    public boolean valid() {
        return (number.length() == 10 || number.length() == 13) && numeric(number);
    }

    private boolean numeric(String number) {
        try {
            parseLong(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean equals(Object other) {
        return (other instanceof Isbn)
                && ((Isbn) other).number.equals(this.number);
    }
}
