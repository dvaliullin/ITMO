package commands;

import managers.*;
import data.MusicBand;
import exceptions.KeyException;

/**
 * Команда замены значения по ключу, если новое значение больше старого
 */
public class ReplaceIfGreater extends Command {
    private final CollectionManager collectionManager;
    private final MusicBandCreator creator;
    private final OutputManager outputManager;

    public ReplaceIfGreater(CollectionManager collectionManager, MusicBandCreator creator, OutputManager outputManager) {
        super("replace_if_greater", "заменить значение по ключу, если новое значение больше старого");
        this.collectionManager = collectionManager;
        this.creator = creator;
        this.outputManager = outputManager;
    }

    /**
     * @param argument целое число
     */
    @Override
    public void execute(String argument) {
        try {
            if (argument.trim().isEmpty()) throw new IllegalArgumentException("Команде нужен аргумент");
            Integer key = Integer.valueOf(argument);
            if (!collectionManager.containsKey(key)) throw new KeyException("Ключа с этим значением не существует");
            MusicBand newElement = creator.createMusicBand();
            MusicBand oldElement = collectionManager.getByKey(key);
            if (oldElement.compareTo(newElement) < 0) {
                collectionManager.addElement(key, newElement);
            }
        } catch (NumberFormatException e) {
            outputManager.printError("Неправильный аргумент, введите целое число");
        } catch (IllegalArgumentException | KeyException e) {
            outputManager.printError(e.getMessage());
        }
    }
}

