package v1;

import com.google.gson.Gson;
import common.*;
import spark.Request;

import static common.ContentType.APPLICATION_JSON;
import static common.ContentType.TEXT_PLAIN;
import static org.eclipse.jetty.http.HttpStatus.*;

public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public LibraryResponse bookByIsbn(Request req) {
        try {
            Isbn isbn = isbnFromPath(req);
            Book book = findBookBy(isbn);
            BookResult result = new BookResult(book);
            String body = new Gson().toJson(result);
            return new LibraryResponse(
                    OK_200,
                    APPLICATION_JSON,
                    body);
        } catch (IllegalArgumentException e) {
            return new LibraryResponse(
                    BAD_REQUEST_400,
                    TEXT_PLAIN,
                    e.getMessage());
        } catch (BookNotFoundException e) {
            return new LibraryResponse(
                    NOT_FOUND_404,
                    TEXT_PLAIN,
                    "Book not found");
        } catch (Exception e) {
            return new LibraryResponse(
                    INTERNAL_SERVER_ERROR_500,
                    TEXT_PLAIN,
                    "Error on our end, sorry");
        }
    }

    private Isbn isbnFromPath(Request req) {
        var isbn = new Isbn(req.params("isbn"));
        if (isbn.valid())
            return isbn;
        else
            throw new IllegalArgumentException("ISBN is not valid");
    }

    private Book findBookBy(Isbn isbn) {
        return bookRepository.retrieve(isbn)
                .orElseThrow(BookNotFoundException::new);
    }
}
