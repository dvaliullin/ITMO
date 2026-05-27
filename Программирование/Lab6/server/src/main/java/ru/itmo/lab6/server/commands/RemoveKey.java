package ru.itmo.lab6.server.commands;

import ru.itmo.lab6.common.data.MusicBand;
import ru.itmo.lab6.common.exceptions.KeyException;
import ru.itmo.lab6.common.util.Response;
import ru.itmo.lab6.server.util.CollectionManager;

/**
 * Команда удаления элемента из коллекции по его ключу
 */
public class RemoveKey extends Command {
    private final CollectionManager collectionManager;

    public RemoveKey(CollectionManager collectionManager) {
        super("remove_key", "удалить элемент из коллекции по его ключу", null);
        this.collectionManager = collectionManager;
    }

    /**
     * @param argument целое число
     */
    @Override
    public Response execute(String argument, MusicBand element) throws KeyException, NumberFormatException {
            if (argument.trim().isEmpty()) throw new IllegalArgumentException("Команде нужен аргумент");
            Integer key = Integer.valueOf(argument);
            if (!collectionManager.containsKey(key)) throw new KeyException("Ключа с этим значением не существует");
            collectionManager.remove(key);
        return new Response(true, "Элемент удален");
        }
}
