package commands;

import data.MusicBand;
import managers.*;

/**
 * Команда удаления из коллекции всех элементов, превышающих заданный
 */
public class RemoveGreater extends Command {
    private final MusicBandCreator creator;
    private final CollectionManager collectionManager;
    private final OutputManager outputManager;

    public RemoveGreater(MusicBandCreator creator, CollectionManager collectionManager, OutputManager outputManager) {
        super("remove_greater", "удалить из коллекции все элементы, превышающие заданный");
        this.creator = creator;
        this.collectionManager = collectionManager;
        this.outputManager = outputManager;
    }

    /**
     * @param argument пустой
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new IllegalArgumentException("Команда не принимает аргументы");
            MusicBand element = creator.createMusicBand();
            collectionManager.removeGreater(element);
        } catch (IllegalArgumentException e) {
            outputManager.printError(e.getMessage());
        }
    }
}

