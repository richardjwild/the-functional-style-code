package v1;


import common.*;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import spark.Request;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

class BookControllerShould {

    private static String VALID_ISBN = "1234567890123";

    @Mock BookRepository bookRepository;
    @Mock Request request;

    private BookController bookController;
    private LibraryResponse libraryResponse;

    @BeforeEach
    void setup() {
        initMocks(this);
        bookController = new BookController(bookRepository);
    }

    @Test
    void return_a_book_matching_an_isbn() throws JSONException {
        given(request.params("isbn")).willReturn(VALID_ISBN);
        var validIsbn = new Isbn(VALID_ISBN);
        given(bookRepository.retrieve(validIsbn)).willReturn(
                Optional.of(new Book(validIsbn, "Book name", new Author("The author"))));

        libraryResponse = bookController.bookByIsbn(request);

        assertJsonResponse(
                200,
                "{\"isbn\":\"1234567890123\",\"name\":\"Book name\",\"author\":\"The author\"}");
    }

    @Test
    void return_404_not_found_when_no_book_matches() {
        given(request.params("isbn")).willReturn(VALID_ISBN);
        var validIsbn = new Isbn(VALID_ISBN);
        given(bookRepository.retrieve(validIsbn)).willReturn(Optional.empty());

        libraryResponse = bookController.bookByIsbn(request);

        assertTextResponse(404, "Book not found");
    }

    @ParameterizedTest
    @CsvSource({
            "123456789012",
            "123456789012A"
    })
    void return_400_bad_request_when_isbn_is_not_valid(String invalidIsbn) {
        given(request.params("isbn")).willReturn(invalidIsbn);

        libraryResponse = bookController.bookByIsbn(request);

        assertTextResponse(400, "ISBN is not valid");
    }

    @Test
    void return_500_internal_server_error_when_something_unexpected_goes_wrong() {
        given(request.params("isbn")).willReturn(VALID_ISBN);
        var validIsbn = new Isbn(VALID_ISBN);
        doThrow(new RuntimeException("Something bad")).when(bookRepository).retrieve(validIsbn);

        libraryResponse = bookController.bookByIsbn(request);

        assertTextResponse(500, "Error on our end, sorry");
    }

    private void assertJsonResponse(int status, String body) throws JSONException {
        assertThat(libraryResponse.status, is(status));
        assertThat(libraryResponse.contentType.mimeType, is("application/json"));
        JSONAssert.assertEquals(body, libraryResponse.body, STRICT);
    }

    private void assertTextResponse(int status, String body) {
        assertThat(libraryResponse.status, is(status));
        assertThat(libraryResponse.contentType.mimeType, is("text/plain"));
        assertThat(libraryResponse.body, is(body));
    }
}