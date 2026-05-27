package ru.itmo.lab6.client.util;

import ru.itmo.lab6.client.network.Requester;
import ru.itmo.lab6.common.util.CommandRequirement;
import ru.itmo.lab6.common.util.Request;
import ru.itmo.lab6.common.util.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс управления взаимодействием пользователя с консолью
 */
public class Console {
    private final InputManager inputManager;
    private final OutputManager outputManager;
    private final Requester requester;
    private final MusicBandCreator musicBandCreator;
    private boolean scriptMode = false;
    private final ArrayList<String> scriptFiles = new ArrayList<>();

    public Console(InputManager inputManager, OutputManager outputManager, Requester requester, MusicBandCreator musicBandCreator) {
        this.inputManager = inputManager;
        this.outputManager = outputManager;
        this.requester = requester;
        this.musicBandCreator = musicBandCreator;
    }

    /**
     * Взаимодействие в интерактивном режиме
     */
    public void runInteractive() {
        setShutdownHook();
        while (true) {
            try {
                String input = readInteractive();
                if (input.isEmpty()) {
                    continue;
                }
                Request request = executeCommand(input);
                Response response = requester.sendRequest(request);
                response = executeRequirement(request, response);
                outputManager.println(response.getMessage());
            } catch (NoSuchElementException e) {
                break;
            } catch (SocketException e) {
                outputManager.printError("Соединение прервано");
                break;
            } catch (Exception e) {
                outputManager.printError(e.getMessage());
            }
        }
    }

    /**
     * @return Прочитанный ввод с консоли
     */
    public String readInteractive() {
        if (!isScriptMode()) {
            outputManager.print("> ");
        }
        return inputManager.readln().trim();
    }

    /**
     * @param input
     * @return Запрос команды
     */
    public Request executeCommand(String input) {
        if (input.isEmpty()) {
            return null;
        }

        CommandParts commandParts = parseCommand(input);
        String commandName = commandParts.name;
        String argument = commandParts.argument;

        return requester.buildRequest(commandName, argument, null);
    }

    /**
     * @param input
     * @return Части команды
     */
    public CommandParts parseCommand(String input) {
        String[] parts = input.split("\\s+", 2);
        String commandName = parts[0].toLowerCase();
        String argument = parts.length > 1 ? parts[1] : "";
        return new CommandParts(commandName, argument);
    }

    /**
     * Запись команды по частям
     * @param name
     * @param argument
     */
    private record CommandParts(String name, String argument) {
    }

    /**
     * @param request
     * @param response
     * @return Переделанный ответ от сервера с учетом требований команд (при необходимости посылается еще один запрос)
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Response executeRequirement(Request request, Response response) throws IOException, ClassNotFoundException {
        String requirement = response.getMessage();
        if (requirement.equals(CommandRequirement.ELEMENT.name())) {
            request.setElement(musicBandCreator.createMusicBand());
            return requester.sendRequest(request);
        }
        if (requirement.equals(CommandRequirement.EXIT.name())) {
            System.exit(0);
        }
        if (requirement.equals(CommandRequirement.RUN_SCRIPT.name())) {
            runScript(request.getCommandArgument());
            return new Response(true, "");
        }
        return response;
    }

    /**
     * Взаимодействие в режиме скрипта
     *
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
        if (!scriptFile.canRead()) {
            outputManager.printError("Нет прав на чтение файла");
            return;
        }

        scriptFiles.add(fileName);

        try (Scanner scriptScanner = new Scanner(scriptFile)) {
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
     *
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
        }));
    }
}

