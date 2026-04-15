package commands;

import managers.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Команда вывода значения поля numberOfParticipants всех элементов в порядке убывания
 */
public class PrintFieldDescendingNumberOfParticipants extends Command {
    private final CollectionManager collectionManager;
    private final OutputManager outputManager;

    public PrintFieldDescendingNumberOfParticipants(CollectionManager collectionManager, OutputManager outputManager) {
        super("print_field_descending_number_of_participants", "вывести значения поля numberOfParticipants всех элементов в порядке убывания");
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
            ArrayList<Integer> descendingNumberOfParticipantsList = new ArrayList<>(collectionManager.getNumberOfParticipantsList());
            descendingNumberOfParticipantsList.sort(Comparator.reverseOrder());
            outputManager.println(descendingNumberOfParticipantsList.toString().replace("[","").replace("]",""));
        } catch (IllegalArgumentException e) {
            outputManager.printError(e.getMessage());
        }
    }
}

