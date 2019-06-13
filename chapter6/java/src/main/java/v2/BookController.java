package v2;

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
        return isbnFromPath(req)
                .flatMap(this::findBookBy)
                .map(BookResult::new)
                .map(new Gson()::toJson)
                .map(this::validInputResponse)
                .orElseGet(this::invalidInputResponse);
    }

    private LibraryResponse validInputResponse(String body) {
        return new LibraryResponse(OK_200, APPLICATION_JSON, body);
    }

    private LibraryResponse invalidInputResponse(RequestError error) {
        return new LibraryResponse(error.status, TEXT_PLAIN, error.message);
    }

    private MaybeValid<Isbn> isbnFromPath(Request req) {
        var isbn = new Isbn(req.params("isbn"));
        return isbn.valid()
                ? new Valid<>(isbn)
                : new Invalid<>(new RequestError(BAD_REQUEST_400, "ISBN is not valid"));
    }

    private MaybeValid<Book> findBookBy(Isbn isbn) {
        var book = bookRepository.retrieve(isbn);
        return book.isPresent()
                ? new Valid<>(book.get())
                : new Invalid<>(new RequestError(NOT_FOUND_404, "Book not found"));
    }
}
