package exceptions;

public class InvalidInputException extends RuntimeException {

    private static final String INVALID_INPUT = "The command '%s' is invalid!";

    public InvalidInputException(String input) {
        super(String.format(INVALID_INPUT, input));
    }
}
