package v2;

public class RequestError {

    public final int status;
    public final String message;

    public RequestError(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
