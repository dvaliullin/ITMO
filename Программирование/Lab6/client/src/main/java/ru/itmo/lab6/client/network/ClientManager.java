package ru.itmo.lab6.client.network;

import ru.itmo.lab6.client.util.Console;
import ru.itmo.lab6.client.util.OutputManager;

/**
 * Класс управления клиентом
 */
public class ClientManager {
    private final ConnectionManager connectionManager;
    private final OutputManager outputManager;
    private final Console console;

    public ClientManager(ConnectionManager connectionManager, OutputManager outputManager, Console console) {
        this.connectionManager = connectionManager;
        this.outputManager = outputManager;
        this.console = console;
    }

    /**
     * Запуск клиента
     */
    public void start() {
        outputManager.println("Подключение к серверу " + connectionManager.getHost() + ":" + connectionManager.getPort());

        if (!connectionManager.connect()) {
            outputManager.printError("Не удалось подключиться к серверу");
            return;
        }

        outputManager.println("Подключение установлено. Введите 'help' для списка команд");

        console.runInteractive();
    }
}
