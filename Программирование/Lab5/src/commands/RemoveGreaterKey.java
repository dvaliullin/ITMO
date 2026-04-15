package commands;

import managers.*;

/**
 * Команда удаления из коллекции всех элементов, ключ которых превышает заданный
 */
public class RemoveGreaterKey extends Command {
    private final CollectionManager collectionManager;
    private final OutputManager outputManager;

    public RemoveGreaterKey(CollectionManager collectionManager, OutputManager outputManager) {
        super("remove_greater_key", "удалить из коллекции все элементы, ключ которых превышает заданный");
        this.collectionManager = collectionManager;
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
            collectionManager.removeGreaterKey(key);
        } catch (NumberFormatException e) {
            outputManager.printError("Неправильный аргумент, введите целое число");
        } catch (IllegalArgumentException e) {
            outputManager.printError(e.getMessage());
        }
    }
}

