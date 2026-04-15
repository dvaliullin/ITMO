package managers;

import exceptions.IllegalValueException;

/**
 * Класс создания объектов
 */
public class Creator {
    private final OutputManager outputManager;
    private final Console console;

    public Creator(OutputManager outputManager, Console console) {
        this.outputManager = outputManager;
        this.console = console;
    }

    /**
     * @param message
     * @param reader
     * @param <T>
     * @return созданный объект
     */
    public <T> T create(String message, Reader<T> reader) {
        T result;
        while (true) {
            try {
                outputManager.print(message);
                result = reader.read();
                break;
            } catch (NumberFormatException e) {
                outputManager.printError("Данные должны быть целым числом");
            } catch (IllegalArgumentException e) {
                outputManager.printError("Выберете значение из списка");
                if (console.isScriptMode()) throw new IllegalValueException("Данные должны быть значением из списка:\n PSYCHEDELIC_CLOUD_RAP\n POP\n POST_ROCK\n");
            } catch (IllegalValueException e) {
                outputManager.println(e.getMessage());
            }
        }
        return result;
    }
}

