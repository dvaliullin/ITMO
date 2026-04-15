package managers;

import exceptions.IllegalValueException;

/**
 * Функциональный интерфейс чтения объектов
 * @param <T>
 */
public interface Reader<T> {
    /**
     * @return прочитанный объект
     * @throws IllegalValueException
     * @throws IllegalArgumentException
     */
    T read() throws IllegalValueException, IllegalArgumentException;
}
