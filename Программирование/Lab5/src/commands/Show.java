package commands;

import managers.*;

/**
 * Команда вывода всех элементов коллекции
 */
public class Show extends Command {
    private final CollectionManager collectionManager;
    private final OutputManager outputManager;

    public Show(CollectionManager collectionManager, OutputManager outputManager) {
        super("show", "вывести все элементы коллекции");
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
            outputManager.println(collectionManager.toString());
        } catch (IllegalArgumentException e) {
            outputManager.printError(e.getMessage());
        }
    }
}

