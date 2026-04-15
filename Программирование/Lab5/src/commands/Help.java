package commands;

import managers.*;

/**
 * Команда для вывода списка команд
 */
public class Help extends Command {
    private final CommandManager commandManager;
    private final OutputManager outputManager;

    public Help(CommandManager commandManager, OutputManager outputManager) {
        super("help", "вывести справку по доступным командам");
        this.commandManager = commandManager;
        this.outputManager = outputManager;
    }

    /**
     * @param argument пустой
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new IllegalArgumentException("Команда не принимает аргументы");
            for (Command command: commandManager.getCommands()) {
                outputManager.println(command.getName() + ": " + command.getDescription());
            }
        } catch (IllegalArgumentException e) {
            outputManager.printError(e.getMessage());
        }
    }
}

