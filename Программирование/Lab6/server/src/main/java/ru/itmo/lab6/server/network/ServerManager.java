package ru.itmo.lab6.server.network;

import ru.itmo.lab6.common.util.Checker;
import ru.itmo.lab6.server.Server;
import ru.itmo.lab6.server.util.CollectionManager;

import java.io.IOException;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * Класс управления сервером
 */
public class ServerManager {
    private final String host;
    private final String port;
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private final CollectionManager collectionManager;
    private final Handler handler;
    private volatile boolean running = true;

    public ServerManager(String host, String port, CollectionManager collectionManager, Handler handler) {
        this.host = host;
        this.port = port;
        this.collectionManager = collectionManager;
        this.handler = handler;
    }

    /**
     * Запуск сервера
     */
    public void start() {
        setShutdownHook();
        try {
            initializeServer();
            loadCollection();
            Server.logger.info("Запуск сервера: хост {}, порт {}", host, port);
            run();
        } catch (UnresolvedAddressException e) {
            Server.logger.error("Ошибка запуска сервера: адрес недоступен");
        }
        catch (IOException e) {
            Server.logger.error("Ошибка запуска сервера: {}", e.getMessage());
        }
    }

    /**
     * Инициализация сервера
     * @throws IOException
     */
    public void initializeServer() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(Checker.checkAddress(host, port));
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    /**
     * Загрузка коллекции из файла
     */
    public void loadCollection() {
        String fileName = System.getenv("LAB6_FILE");
        if (fileName == null || fileName.isEmpty()) {
            Server.logger.error("Не задана переменная окружения LAB6_FILE");
            System.exit(1);
        }
        try {
            collectionManager.createCollection(fileName);
            Server.logger.info("Коллекция загружена из файла: {}", fileName);
        } catch (Exception e) {
            Server.logger.error("Не удалось загрузить коллекцию: {}", e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Начало работы сервера
     */
    public void run() {
        new Thread(this::consoleInput).start();
        while (running) {
            try {
                selector.select();

                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();

                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    iter.remove();

                    if (!key.isValid()) {
                        continue;
                    }

                    if (key.isAcceptable()) {
                        handler.handleAccept(selector, key);
                    } else if (key.isReadable()) {
                        handler.handleRead(key);
                    }
                }
            } catch (ClosedSelectorException e) {
                stop();
            }
            catch (IOException e) {
                Server.logger.error("Ошибка в работе сервера: {}", e.getMessage());
            }
        }
    }

    /**
     * Остановка сервера
     */
    public void stop() {
        running = false;
        try {
            if (selector != null) selector.close();
            if (serverSocketChannel != null) serverSocketChannel.close();
        } catch (IOException e) {
            Server.logger.error("Ошибка остановки сервера: {}", e.getMessage());
        }
    }

    /**
     * Ввод серверных команд
     */
    public void consoleInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "":
                    continue;
                case "save":
                    collectionManager.save();
                    Server.logger.info("Коллекция сохранена");
                    break;
                case "exit":
                    collectionManager.save();
                    System.exit(0);
                    Server.logger.info("Завершение работы сервера, сохранение коллекции");
                default:
                    Server.logger.error("Не удалось распознать команду (save или exit)");
                    break;
            }
        }
    }

    /**
     * Хук прерывания с сохранением коллекции
     */
    public void setShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Server.logger.info("Получен сигнал завершения работы сервера...");
            try {
                collectionManager.save();
                Server.logger.info("Коллекция сохранена");
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                Server.logger.error("Ошибка сохранения коллекции: {}", e.getMessage());
            }
            stop();
            Server.logger.info("Сервер завершил работу");
        }));
    }
}
