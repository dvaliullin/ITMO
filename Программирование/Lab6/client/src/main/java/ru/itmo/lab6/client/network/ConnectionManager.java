package ru.itmo.lab6.client.network;

import ru.itmo.lab6.common.util.Checker;

import java.io.IOException;
import java.net.Socket;

/**
 * Класс управления соединением
 */
public class ConnectionManager {
    private final String host;
    private final String port;
    private Socket socket;

    public ConnectionManager(String host, String port) {
        this.host = host;
        this.port = port;
    }

    /**
     * @return true, если есть подключение, иначе false
     */
    public boolean connect() {
        try {
            socket = new Socket();
            socket.connect(Checker.checkAddress(host,port), 5000);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public String getHost() {
        return this.host;
    }

    public String getPort() {
        return this.port;
    }

    public Socket getSocket() {
        return socket;
    }

    /**
     * Закрывает сокет
     */
    public void close() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException ignored) {}
    }
}
