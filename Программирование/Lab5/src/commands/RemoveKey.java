package commands;

import managers.*;
import exceptions.KeyException;

/**
 * Команда удаления элемента из коллекции по его ключу
 */
public class RemoveKey extends Command {
    private final CollectionManager collectionManager;
    private final OutputManager outputManager;

    public RemoveKey(CollectionManager collectionManager, OutputManager outputManager) {
        super("remove_key", "удалить элемент из коллекции по его ключу");
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
            if (!collectionManager.containsKey(key)) throw new KeyException("Ключа с этим значением не существует");
            collectionManager.remove(key);
            outputManager.println("Элемент удален");
        } catch (NumberFormatException e) {
            outputManager.printError("Неправильный аргумент, введите целое число");
        } catch (IllegalArgumentException | KeyException e) {
            outputManager.printError(e.getMessage());
        }
    }
}
