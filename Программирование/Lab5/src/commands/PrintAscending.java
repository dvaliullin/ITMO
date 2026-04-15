package commands;

import managers.*;

/**
 * Команда вывода элементов коллекции в порядке возрастания
 */
public class PrintAscending extends Command {
    private final CollectionManager collectionManager;
    private final OutputManager outputManager;

    public PrintAscending(CollectionManager collectionManager, OutputManager outputManager) {
        super("print_ascending", "вывести элементы коллекции в порядке возрастания");
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
            outputManager.println(collectionManager.getSortedCollection().toString());
        } catch (IllegalArgumentException e) {
            outputManager.printError(e.getMessage());
        }
    }
}
