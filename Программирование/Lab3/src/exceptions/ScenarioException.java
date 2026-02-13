package exceptions;
public class ScenarioException extends Exception {
    private String message;

    public ScenarioException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Нарушена логика сценария: " + message;
    }
}
