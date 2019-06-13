package common;

public class BookResult {

    private final String isbn;
    private final String name;
    private final String author;

    public BookResult(Book book) {
        isbn = book.isbn.number;
        name = book.name;
        author = book.author.name;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }
}
