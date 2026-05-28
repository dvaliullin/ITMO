package ru.itmo.lab6.server.util;

import ru.itmo.lab6.server.commands.*;

import java.util.ArrayList;

/**
 * Класс управления командами
 */
public class CommandManager {
    private final ArrayList<Command> commands = new ArrayList<>();

    /**
     * Инициализация команд
     * @param collectionManager
     */
    public void initializeCommands(CollectionManager collectionManager) {
        addCommand(new Help(this));
        addCommand(new Info(collectionManager));
        addCommand(new Show(collectionManager));
        addCommand(new Insert(collectionManager));
        addCommand(new Update(collectionManager));
        addCommand(new RemoveKey(collectionManager));
        addCommand(new Clear(collectionManager));
        addCommand(new ExecuteScript());
        addCommand(new Exit());
        addCommand(new RemoveGreater(collectionManager));
        addCommand(new ReplaceIfGreater(collectionManager));
        addCommand(new RemoveGreaterKey(collectionManager));
        addCommand(new CountGreaterThanNumberOfParticipants(collectionManager));
        addCommand(new PrintAscending(collectionManager));
        addCommand(new PrintFieldDescendingNumberOfParticipants(collectionManager));
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

    /**
    * @param commandName
    * @return true, если есть команда с таким именем, иначе false
    */
    public boolean containsCommand(String commandName) {
        return commands.contains(getCommandByName(commandName));
    }
}

