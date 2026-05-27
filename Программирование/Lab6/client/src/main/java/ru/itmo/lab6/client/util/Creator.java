package ru.itmo.lab6.client.util;

import ru.itmo.lab6.common.exceptions.IllegalValueException;

/**
 * Класс создания объектов
 */
public class Creator {
    private final OutputManager outputManager;

    public Creator(OutputManager outputManager) {
        this.outputManager = outputManager;
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
            } catch (IllegalValueException e) {
                outputManager.println(e.getMessage());
            }
        }
        return result;
    }
}

