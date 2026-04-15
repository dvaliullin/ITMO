package commands;

import managers.*;
import data.MusicBand;
import exceptions.IllegalValueException;

/**
 * Команда обновления значения элемента коллекции, id которого равен заданному
 */
public class Update extends Command {
    private final CollectionManager collectionManager;
    private final OutputManager outputManager;
    private final MusicBandCreator creator;

    public Update(CollectionManager collectionManager, OutputManager outputManager, MusicBandCreator creator) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager =collectionManager;
        this.outputManager = outputManager;
        this.creator = creator;
    }

    /**
     * @param argument целое число
     */
    @Override
    public void execute(String argument) {
        try {
            if (argument.trim().isEmpty()) throw new IllegalArgumentException("Команде нужен аргумент");
            Integer id = Integer.valueOf(argument);
            if (!collectionManager.containsId(id))
                throw new IllegalValueException("Элемента с id = " + id + " не существует");
            MusicBand element = collectionManager.getById(id);
            element.setName(creator.createMusicBandName());
            element.setNumberOfParticipants(creator.createNumberOfParticipants());
            element.setCoordinates(creator.createCoordinates());
            element.setLabel(creator.createLabel());
            element.setGenre(creator.createMusicGenre());
            outputManager.println("Элемент коллекции успешно обновлен");
        } catch (NumberFormatException e) {
            outputManager.printError("Неправильный аргумент, введите целое число");
        } catch (IllegalArgumentException | IllegalValueException e) {
            outputManager.printError(e.getMessage());
        }
    }
}
