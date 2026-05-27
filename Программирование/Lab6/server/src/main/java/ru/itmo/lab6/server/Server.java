package ru.itmo.lab6.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itmo.lab6.server.network.Handler;
import ru.itmo.lab6.server.network.ServerManager;
import ru.itmo.lab6.server.util.CollectionManager;
import ru.itmo.lab6.server.util.CommandManager;
import ru.itmo.lab6.server.util.MusicBandParser;

/**
 * Главный класс сервера
 */
public class Server {
    public static final Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {
        String host = System.getenv("HOST");
        String port = System.getenv("PORT");

        CollectionManager collectionManager = new CollectionManager(new MusicBandParser());
        CommandManager commandManager = new CommandManager();
        commandManager.initializeCommands(collectionManager);
        Handler handler = new Handler(commandManager);
        ServerManager serverManager = new ServerManager(host, port, collectionManager, handler);

        serverManager.start();
    }
}
