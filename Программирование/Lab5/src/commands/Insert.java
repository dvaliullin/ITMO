package commands;

import exceptions.KeyException;
import managers.*;

/**
 * Команда добавления элемента в коллекцию
 */
public class Insert extends Command {
    private final CollectionManager collectionManager;
    private final OutputManager outputManager;
    private final MusicBandCreator creator;

    public Insert(CollectionManager collectionManager, OutputManager outputManager, MusicBandCreator creator) {
        super("insert", "добавить новый элемент с заданным ключом");
        this.collectionManager = collectionManager;
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
            if (collectionManager.getCollection().containsKey(Integer.valueOf(argument))) {
                throw new KeyException("Элемент с этим ключом уже существует");
            }
            Integer key = Integer.valueOf(argument);
            collectionManager.addElement(key, creator.createMusicBand());
            outputManager.println("Элемент с ключом " + key + " добавлен в коллекцию");
        } catch (NumberFormatException e) {
            outputManager.printError("Неправильный аргумент, введите целое число");
        } catch (IllegalArgumentException | KeyException e) {
            outputManager.printError(e.getMessage());
        }
    }
}

