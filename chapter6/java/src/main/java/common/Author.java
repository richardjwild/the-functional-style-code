package common;

public class Author {

    public final String name;

    public Author(String name) {
        this.name = name;
    }

    public boolean equals(Object other) {
        return (other instanceof Author)
                && ((Author) other).name.equals(this.name);
    }
}
