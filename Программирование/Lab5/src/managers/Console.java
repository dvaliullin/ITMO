package managers;

import commands.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Класс управления взаимодействием пользователя с консолью
 */
public class Console {
    private final CommandManager commandManager;
    private final InputManager inputManager;
    private final OutputManager outputManager;
    private boolean scriptMode = false;
    private final ArrayList<String> scriptFiles = new ArrayList<>();

    public Console(CommandManager commandManager, InputManager inputManager, OutputManager outputManager) {
        this.commandManager = commandManager;
        this.inputManager = inputManager;
        this.outputManager = outputManager;
    }

    /**
     * Взаимодействие в интерактивном режиме
     */
    public void runInteractive() {
        setShutdownHook();
        while (true) {
            try {
                if (!isScriptMode()) {
                    outputManager.print("> ");
                }
                String input = inputManager.readln().trim();
                if (input.isEmpty()) continue;

                String[] parts = input.split("\\s+", 2);
                String commandName = parts[0].toLowerCase();
                String argument = parts.length > 1 ? parts[1] : "";

                Command command = commandManager.getCommandByName(commandName);
                if (command != null) {
                    command.execute(argument);
                } else {
                    outputManager.println("Неизвестная команда. Введите 'help' для списка команд.");
                }
            } catch (NoSuchElementException e) {
                break;
            } catch (Exception e) {
                outputManager.printError(e.getMessage());
            }
        }
    }

    /**
     * Взаимодействие в режиме скрипта
     * @param fileName
     */
    public void runScript(String fileName) {

        File scriptFile = new File(fileName);

        if (scriptFiles.contains(fileName)) {
            outputManager.printError("Обнаружен рекурсивный вызов скрипта");
            return;
        }

        if (!scriptFile.exists()) {
            outputManager.printError("Файл не существует");
            return;
        }
        if (!scriptFile.canRead())  {
            outputManager.printError("Нет прав на чтение файла");
            return;
        }

        scriptFiles.add(fileName);

        try (Scanner scriptScanner = new Scanner(scriptFile) ) {
            inputManager.push(inputManager.getScanner());
            inputManager.setFileScanner(scriptScanner);
            setScriptMode(true);

            outputManager.println("Выполнение скрипта " + fileName + "...");
            runInteractive();

        } catch (FileNotFoundException e) {
            outputManager.printError(e.getMessage());
        } finally {
            scriptFiles.remove(fileName);

            if (!inputManager.getScriptScanners().isEmpty()) {
                inputManager.close();
                inputManager.setFileScanner(inputManager.getScriptScanners().pop());
                setScriptMode(!inputManager.getScriptScanners().isEmpty());
            }

            outputManager.println("Выполнение скрипта завершено");
        }
    }

    /**
     * @return true, если происходит взаимодействие в режиме скрипта
     */
    public boolean isScriptMode() {
        return scriptMode;
    }

    /**
     * Указать, происходит ли взаимодействие в режиме скрипта
     * @param scriptMode
     */
    public void setScriptMode(boolean scriptMode) {
        this.scriptMode = scriptMode;
    }

    /**
     * Установить хук прерывания программы (ctrl+C)
     */
    public void setShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                outputManager.println("\nЗавершение программы...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            outputManager.println("\nПрограмма завершена");
        }));
    }
}

