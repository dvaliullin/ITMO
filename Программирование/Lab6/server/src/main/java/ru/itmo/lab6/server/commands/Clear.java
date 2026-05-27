package ru.itmo.lab6.server.commands;

import ru.itmo.lab6.common.data.MusicBand;
import ru.itmo.lab6.common.util.Response;
import ru.itmo.lab6.server.util.CollectionManager;

/**
 * Команда очищения коллекции
 */
public class Clear extends Command {
    private final CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию", null);
        this.collectionManager = collectionManager;
    }

    /**
     * Очистить коллекцию
     * @param argument пустой
     */
    @Override
    public Response execute(String argument, MusicBand element) {
            if (!argument.isEmpty() || element != null) {
                throw new IllegalArgumentException("Команда не принимает аргументы");
            }
            collectionManager.clear();
            return new Response(true, "Коллекция очищена");
        }
    }

