package commands;

import managers.*;

/**
 * Команда сохранения коллекции в файл
 */
public class Save extends Command {
    private final CollectionManager collectionManager;
    private final OutputManager outputManager;

    public Save(CollectionManager collectionManager, OutputManager outputManager) {
        super("save","сохранить коллекцию в файл");
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
            collectionManager.save();
            outputManager.println("Коллекция сохранена в файл");
        } catch (IllegalArgumentException e) {
            outputManager.printError(e.getMessage());
        }
    }
}

