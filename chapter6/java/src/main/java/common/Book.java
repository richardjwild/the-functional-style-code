package common;

public class Book {

    public final Isbn isbn;
    public final String name;
    public final Author author;

    public Book(Isbn isbn, String name, Author author) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
    }

    public boolean equals(Object other) {
        return (other instanceof Book)
                && ((Book) other).isbn.equals(this.isbn);
    }
}
