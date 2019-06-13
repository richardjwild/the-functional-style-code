package common;

public enum ContentType {

    TEXT_PLAIN("text/plain"), APPLICATION_JSON("application/json");

    public final String mimeType;

    ContentType(String mimeType) {
        this.mimeType = mimeType;
    }
}
