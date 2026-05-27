package ru.itmo.lab6.server.commands;

import ru.itmo.lab6.common.data.MusicBand;
import ru.itmo.lab6.common.util.CommandRequirement;
import ru.itmo.lab6.common.util.Response;
import ru.itmo.lab6.common.exceptions.KeyException;
import ru.itmo.lab6.server.util.CollectionManager;

/**
 * Команда добавления элемента в коллекцию
 */
public class Insert extends Command {
    private final CollectionManager collectionManager;

    public Insert(CollectionManager collectionManager) {
        super("insert", "добавить новый элемент с заданным ключом", CommandRequirement.ELEMENT);
        this.collectionManager = collectionManager;
    }

    /**
     * @param argument целое число
     */
    @Override
    public Response execute(String argument, MusicBand element) throws KeyException, NumberFormatException {
            if (argument.trim().isEmpty()) {
                throw new IllegalArgumentException("Команде нужен аргумент");
            }
            Integer key = Integer.valueOf(argument);
            if (collectionManager.getCollection().containsKey(Integer.valueOf(argument))) {
                throw new KeyException("Элемент с этим ключом уже существует");
            }
            if (element == null) {
                return new Response(true, getRequirement().name());
            }
            collectionManager.addElement(key, element);
        return new Response(true, "Элемент с ключом " + key + " добавлен в коллекцию");
        }
    }

