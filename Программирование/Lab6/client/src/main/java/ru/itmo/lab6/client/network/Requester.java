package ru.itmo.lab6.client.network;

import ru.itmo.lab6.common.util.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Класс управления запросами клиента
 */
public class Requester {
    private final ConnectionManager connectionManager;

    public Requester(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    /**
     * @param request
     * @return Ответ от сервера по отправленному запросу
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Response sendRequest(Request request) throws IOException, ClassNotFoundException {
        if (!connectionManager.connect()) {
            throw new IOException("Сервер недоступен");
        }

        try {
            writeObject(request);
            Response response = (Response) readObject();
            return response;
        } finally {
            connectionManager.close();
        }
    }

    /**
     * Запись объекта в поток
     * @param obj
     * @throws IOException
     */
    public void writeObject(Object obj) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(connectionManager.getSocket().getOutputStream());
        oos.writeObject(obj);
        oos.flush();
    }

    /**
     * @return Объект, прочитанный из потока
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object readObject() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(connectionManager.getSocket().getInputStream());
        return ois.readObject();
    }

    /**
     * @param commandName
     * @param argument
     * @param element
     * @return Запрос от клиента
     */
    public Request buildRequest(String commandName, String argument, Serializable element) {
        return new Request(commandName, argument, element);
    }
}

