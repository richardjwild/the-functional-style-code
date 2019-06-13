package common;

public class LibraryResponse {

    public final int status;
    public final ContentType contentType;
    public final String body;

    public LibraryResponse(int status, ContentType contentType, String body) {
        this.status = status;
        this.contentType = contentType;
        this.body = body;
    }
}
