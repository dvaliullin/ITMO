package ru.itmo.lab6.server.commands;


import ru.itmo.lab6.common.data.MusicBand;
import ru.itmo.lab6.common.exceptions.KeyException;
import ru.itmo.lab6.common.util.CommandRequirement;
import ru.itmo.lab6.common.util.Response;
import ru.itmo.lab6.server.util.CollectionManager;

/**
 * Команда замены значения по ключу, если новое значение больше старого
 */
public class ReplaceIfGreater extends Command {
    private final CollectionManager collectionManager;

    public ReplaceIfGreater(CollectionManager collectionManager) {
        super("replace_if_greater", "заменить значение по ключу, если новое значение больше старого", CommandRequirement.ELEMENT);
        this.collectionManager = collectionManager;
    }

    /**
     * @param argument целое число
     */
    @Override
    public Response execute(String argument, MusicBand element) throws KeyException {
            if (argument.trim().isEmpty()) {
                throw new IllegalArgumentException("Команде нужен аргумент");
            }

            Integer key = Integer.valueOf(argument);

            if (!collectionManager.containsKey(key)) {
                throw new KeyException("Ключа с этим значением не существует");
            }

            if (element == null) {
                return new Response(true, getRequirement().name());
            }

            MusicBand oldElement = collectionManager.getByKey(key);
            if (oldElement.compareTo(element) < 0) {
                collectionManager.addElement(key, element);
                return new Response(true, "значение заменено");
            } else {
                return new Response(true, "значение не заменено (меньше старого)");
            }
        }
}

