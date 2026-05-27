package ru.itmo.lab6.client;

import ru.itmo.lab6.client.network.ClientManager;
import ru.itmo.lab6.client.network.ConnectionManager;
import ru.itmo.lab6.client.network.Requester;
import ru.itmo.lab6.client.util.*;

/**
 * Главный класс клиента
 */
public class Client {

    public static void main(String[] args) {
        String host = System.getenv("HOST");
        String port = System.getenv("PORT");

        ConnectionManager connectionManager = new ConnectionManager(host, port);
        OutputManager outputManager = new OutputManager();
        InputManager inputManager = new InputManager();
        MusicBandCreator musicBandCreator = new MusicBandCreator(new MusicBandReader(inputManager), new Creator(outputManager), outputManager);
        Requester requester = new Requester(connectionManager);
        Console console = new Console(inputManager, outputManager, requester, musicBandCreator);
        ClientManager clientManager = new ClientManager(connectionManager, outputManager, console);

        clientManager.start();
    }
}
