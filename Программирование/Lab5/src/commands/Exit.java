package commands;

import managers.OutputManager;

/**
 * Команда для завершения программы
 */
public class Exit extends Command {
    private final OutputManager outputManager;

    public Exit(OutputManager outputManager) {
        super("exit", "завершить программу (без сохранения в файл)");
        this.outputManager = outputManager;
    }

    /**
     * @param argument пустой
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new IllegalArgumentException("Команда не принимает аргументы");
            System.exit(0);
        } catch (IllegalArgumentException e) {
            outputManager.printError(e.getMessage());
        }
    }
}