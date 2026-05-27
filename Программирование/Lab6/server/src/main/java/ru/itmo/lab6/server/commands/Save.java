package ru.itmo.lab6.server.commands;

import ru.itmo.lab6.common.data.MusicBand;
import ru.itmo.lab6.common.util.CommandRequirement;
import ru.itmo.lab6.common.util.Response;
import ru.itmo.lab6.server.util.CollectionManager;

/**
 * Команда сохранения коллекции в файл
 */
public class Save extends Command {
    private final CollectionManager collectionManager;

    public Save(CollectionManager collectionManager) {
        super("save","сохранить коллекцию в файл", null);
        this.collectionManager = collectionManager;
    }

    /**
     * @param argument пустой
     */
    @Override
    public Response execute(String argument, MusicBand element) {
        if (!argument.isEmpty() || element != null ) {
            throw new IllegalArgumentException("Команда не принимает аргументы");
        }
        collectionManager.save();
        return new Response(true, "Коллекция сохранена в файл");
    }
}

