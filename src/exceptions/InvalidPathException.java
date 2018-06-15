package exceptions;

public class InvalidPathException extends RuntimeException {

    private static final String INVALID_DESTINATION = "Cannot go higher in folder hierarchy.";

    public InvalidPathException() {
        super(INVALID_DESTINATION);
    }

    public InvalidPathException(String message) {
        super(message);
    }
}
