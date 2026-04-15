package commands;

import managers.*;

/**
 * Команда для вывода информации о коллекции
 */
public class Info extends Command {
    private final OutputManager outputManager;
    private final CollectionManager collectionManager;

    public Info(OutputManager outputManager, CollectionManager collectionManager) {
        super("info", "вывести информацию о коллекции");
        this.outputManager = outputManager;
        this.collectionManager = collectionManager;
    }

    /**
     * @param argument пустой
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new IllegalArgumentException("Команда не принимает аргументы");
            outputManager.println(collectionManager.collectionInfo());
        } catch (IllegalArgumentException e) {
            outputManager.printError(e.getMessage());
        }
    }
}
