package exceptions;

public class KeyException extends Exception {
    private final String message;

    public KeyException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
