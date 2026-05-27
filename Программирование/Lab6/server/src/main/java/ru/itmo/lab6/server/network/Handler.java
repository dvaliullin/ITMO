package ru.itmo.lab6.server.network;

import ru.itmo.lab6.common.data.MusicBand;
import ru.itmo.lab6.common.util.Request;
import ru.itmo.lab6.common.util.Response;
import ru.itmo.lab6.server.Server;
import ru.itmo.lab6.server.commands.Command;
import ru.itmo.lab6.server.util.CommandManager;

import java.io.IOException;
import java.io.Serializable;
import java.net.SocketException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


/**
 * Класс для обработки запросов
 */
public class Handler {
    private final CommandManager commandManager;

    public Handler(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    /**
     * Обработка подключений
     * @param selector
     * @param key
     * @throws IOException
     */
    public void handleAccept(Selector selector, SelectionKey key) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverChannel.accept();
        socketChannel.configureBlocking(false);
        Receiver receiver = new Receiver(socketChannel);
        SelectionKey clientKey = socketChannel.register(selector, SelectionKey.OP_READ);
        clientKey.attach(receiver);
        Server.logger.info("Новый запрос от {}", socketChannel.getRemoteAddress());
    }

    /**
     * Обработка чтения данных
     * @param key
     * @throws IOException
     */
    public void handleRead(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        Receiver receiver = (Receiver) key.attachment();
        if (receiver == null) {
            closeConnection(key, socketChannel);
            return;
        }

        try {
            Request request = receiver.receiveRequest();

            if (request == null) {
                if (receiver.isEof()) {
                    closeConnection(key, socketChannel);
                }
                return;
            }

            Server.logger.info("Получена команда {}", request.getCommandName());
            Response response = handleRequest(request);
            socketChannel.register(key.selector(), SelectionKey.OP_WRITE);
            receiver.sendResponse(response);
            Server.logger.info("Отправлен ответ для команды {}", request.getCommandName());

        } catch (ClassNotFoundException e) {
            Server.logger.error("Ошибка десериализации: {}", e.getMessage());
            receiver.sendResponse(new Response(false, "Ошибка формата запроса"));
        } catch (SocketException e) {
            Server.logger.error("Соединение прервано");
            closeConnection(key, socketChannel);
        } catch (IOException e) {
            Server.logger.error("Ошибка чтения: {}", e.getMessage());
            closeConnection(key, socketChannel);
        }
    }

    /**
     * @param request
     * @return ответ по обработанному запросу
     */
    public Response handleRequest(Request request) {
        String commandName = request.getCommandName();
        String argument = request.getCommandArgument();
        Serializable data = request.getElement();

        Server.logger.info("Обработка команды: {}", commandName);

        try {
            if (!commandManager.containsCommand(commandName)) {
                Server.logger.error("Неизвестная команда: {}", commandName);
                return new Response(false, "Неизвестная команда. Введите 'help' для списка команд");
            }

            Command command = commandManager.getCommandByName(commandName);

            Response response = command.execute(argument, (MusicBand) data);

            if (response.isSuccess()) {
                Server.logger.info("Команда {} выполнена успешно", commandName);
            } else {
                Server.logger.error("Команда {} завершилась с ошибкой: {}", commandName, response.getMessage());
            }

            return response;

        } catch (NumberFormatException e) {
            Server.logger.error("Ошибка при выполнении команды {}: Неправильный аргумент, введите целое число", commandName);
            return new Response(false, "Ошибка при выполнении команды: Неправильный аргумент, введите целое число");
        }
        catch (Exception e) {
            Server.logger.error("Ошибка при выполнении команды {}: {}", commandName, e.getMessage());
            return new Response(false, "Ошибка при выполнении команды: " + e.getMessage());
        }
    }

    /**
     * Закрыть сокет
     * @param key
     * @param socketChannel
     */
    private void closeConnection(SelectionKey key, SocketChannel socketChannel) {
        try {
            if (key != null) {
                key.cancel();
            }
            if (socketChannel != null && socketChannel.isOpen()) {
                socketChannel.close();
            }
        } catch (IOException e) {
            Server.logger.error("Ошибка при закрытии соединения: {}", e.getMessage());
        }
    }
}
