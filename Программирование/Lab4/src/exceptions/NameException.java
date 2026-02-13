package exceptions;
public class NameException extends RuntimeException {
    private String message;

    public NameException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Объект без имени: " + message;
    }
}
