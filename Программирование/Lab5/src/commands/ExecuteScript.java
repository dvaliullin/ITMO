package commands;

import managers.*;

/**
 * Команда выполнения скрипта
 */
public class ExecuteScript extends Command {
    private final Console console;
    private final OutputManager outputManager;

    public ExecuteScript(Console console, OutputManager outputManager) {
        super("execute_script", "считать и исполнить скрипт из указанного файла");
        this.console = console;
        this.outputManager = outputManager;
    }

    /**
     * @param argument имя файла
     */
    @Override
    public void execute(String argument) {
        try {
            if (argument.isEmpty()) throw new IllegalArgumentException("Команде нужен аргумент");
            console.runScript(argument);
        } catch (IllegalArgumentException e) {
            outputManager.printError(e.getMessage());
        }
    }
}

