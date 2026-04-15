package commands;

import managers.*;

/**
 * Команда подсчета элементов с числом участников группы больше заданного
 */
public class CountGreaterThanNumberOfParticipants extends Command {
    private final CollectionManager collectionManager;
    private final OutputManager outputManager;

    public CountGreaterThanNumberOfParticipants(CollectionManager collectionManager, OutputManager outputManager) {
        super("count_greater_than_number_of_participants", "вывести количество элементов, значение поля numberOfParticipants которых больше заданного");
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
            Integer number = Integer.valueOf(argument);
            outputManager.println(String.valueOf(collectionManager.countGreaterThanNumberOfParticipants(number)));
        } catch (NumberFormatException e) {
            outputManager.printError("Неправильный аргумент, введите целое число");
        } catch (IllegalArgumentException e) {
            outputManager.printError(e.getMessage());
        }
    }
}

