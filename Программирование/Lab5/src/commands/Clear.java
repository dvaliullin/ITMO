package commands;

import managers.*;

/**
 * Команда очищения коллекции
 */
public class Clear extends Command {
    private final CollectionManager collectionManager;
    private final OutputManager outputManager;

    public Clear(CollectionManager collectionManager, OutputManager outputManager) {
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
        this.outputManager = outputManager;
    }

    /**
     * Очистить коллекцию
     * @param argument пустой
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new IllegalArgumentException("Команда не принимает аргументы");
            collectionManager.clear();
            outputManager.println("Коллекция очищена");
        } catch (IllegalArgumentException e) {
            outputManager.printError(e.getMessage());
        }
    }
}

