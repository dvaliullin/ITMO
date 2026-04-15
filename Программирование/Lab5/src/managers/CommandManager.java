package managers;

import commands.*;

import java.util.ArrayList;

/**
 * Класс управления командами
 */
public class CommandManager {
    private final ArrayList<Command> commands = new ArrayList<>();

    /**
     * Инициализация команд
     * @param collectionManager
     * @param musicBandCreator
     * @param outputManager
     * @param console
     */
    public void initializeCommands(CollectionManager collectionManager, MusicBandCreator musicBandCreator,
                                   OutputManager outputManager, Console console) {
        addCommand(new Help(this,outputManager));
        addCommand(new Info(outputManager, collectionManager));
        addCommand(new Show(collectionManager, outputManager));
        addCommand(new Insert(collectionManager, outputManager, musicBandCreator));
        addCommand(new Update(collectionManager, outputManager, musicBandCreator));
        addCommand(new RemoveKey(collectionManager, outputManager));
        addCommand(new Clear(collectionManager, outputManager));
        addCommand(new Save(collectionManager, outputManager));
        addCommand(new ExecuteScript(console, outputManager));
        addCommand(new Exit(outputManager));
        addCommand(new RemoveGreater(musicBandCreator, collectionManager, outputManager));
        addCommand(new ReplaceIfGreater(collectionManager, musicBandCreator, outputManager));
        addCommand(new RemoveGreaterKey(collectionManager, outputManager));
        addCommand(new CountGreaterThanNumberOfParticipants(collectionManager, outputManager));
        addCommand(new PrintAscending(collectionManager, outputManager));
        addCommand(new PrintFieldDescendingNumberOfParticipants(collectionManager, outputManager));
    }


    /**
     * @return список команд
     */
    public ArrayList<Command> getCommands() {
        return commands;
    }

    /**
     * Добавить команду в список
     * @param command
     */
    public void addCommand(Command command) {
        commands.add(command);
    }

    /**
     * @param name
     * @return команду по ее имени
     */
    public Command getCommandByName(String name) {
        return commands.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
    }
}

