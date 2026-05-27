package ru.itmo.lab6.server.commands;

import ru.itmo.lab6.common.data.MusicBand;
import ru.itmo.lab6.common.exceptions.IllegalValueException;
import ru.itmo.lab6.common.util.CommandRequirement;
import ru.itmo.lab6.common.util.Response;
import ru.itmo.lab6.server.util.CollectionManager;

/**
 * Команда обновления значения элемента коллекции, id которого равен заданному
 */
public class Update extends Command {
    private final CollectionManager collectionManager;

    public Update(CollectionManager collectionManager) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному", CommandRequirement.ELEMENT);
        this.collectionManager = collectionManager;
    }

    /**
     * @param argument целое число
     */
    @Override
    public Response execute(String argument, MusicBand element) throws NumberFormatException {
        if (argument.trim().isEmpty()) {
            throw new IllegalArgumentException("Команде нужен аргумент");
        }

        Integer id = Integer.valueOf(argument);

        if (!collectionManager.containsId(id)) {
            throw new IllegalValueException("Элемента с id = " + id + " не существует");
        }

        if (element == null) {
            return new Response(true, getRequirement().name());
        }

        MusicBand oldElement = collectionManager.getById(id);
        setMusicBandFields(oldElement, element);
        return new Response(true, "Элемент коллекции успешно обновлен");
    }

    public void setMusicBandFields(MusicBand oldElement, MusicBand newElement) {
        oldElement.setName(newElement.getName());
        oldElement.setNumberOfParticipants(newElement.getNumberOfParticipants());
        oldElement.setCoordinates(newElement.getCoordinates());
        oldElement.setLabel(newElement.getLabel());
        oldElement.setGenre(newElement.getGenre());
    }
}
