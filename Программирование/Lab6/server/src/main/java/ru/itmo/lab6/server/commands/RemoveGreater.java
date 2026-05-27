package ru.itmo.lab6.server.commands;

import ru.itmo.lab6.common.data.MusicBand;
import ru.itmo.lab6.common.util.CommandRequirement;
import ru.itmo.lab6.common.util.Response;
import ru.itmo.lab6.server.util.CollectionManager;

/**
 * Команда удаления из коллекции всех элементов, превышающих заданный
 */
public class RemoveGreater extends Command {
    private final CollectionManager collectionManager;

    public RemoveGreater(CollectionManager collectionManager) {
        super("remove_greater", "удалить из коллекции все элементы, превышающие заданный", CommandRequirement.ELEMENT);
        this.collectionManager = collectionManager;
    }

    /**
     * @param argument пустой
     */
    @Override
    public Response execute(String argument, MusicBand element) {
            if (!argument.isEmpty()) {
                throw new IllegalArgumentException("Команда не принимает аргументы");
            }
            if (element == null) {
                return new Response(true, getRequirement().name());
            }
            String removedCount = String.valueOf(collectionManager.removeGreater(element));
            return new Response(true, removedCount + " элементов удалено");
        }
}

