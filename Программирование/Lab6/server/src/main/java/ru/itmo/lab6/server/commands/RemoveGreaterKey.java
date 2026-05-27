package ru.itmo.lab6.server.commands;

import ru.itmo.lab6.common.data.MusicBand;
import ru.itmo.lab6.common.util.Response;
import ru.itmo.lab6.server.util.CollectionManager;

/**
 * Команда удаления из коллекции всех элементов, ключ которых превышает заданный
 */
public class RemoveGreaterKey extends Command {
    private final CollectionManager collectionManager;

    public RemoveGreaterKey(CollectionManager collectionManager) {
        super("remove_greater_key", "удалить из коллекции все элементы, ключ которых превышает заданный", null);
        this.collectionManager = collectionManager;
    }


    /**
     * @param argument целое число
     */
    @Override
    public Response execute(String argument, MusicBand element) throws NumberFormatException {
            if (argument.trim().isEmpty()) throw new IllegalArgumentException("Команде нужен аргумент");
            Integer key = Integer.valueOf(argument);
            String removedCount = String.valueOf(collectionManager.removeGreaterKey(key));
            return new Response(true, removedCount + " элементов удалено");
        }
    }

