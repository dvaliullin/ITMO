package ru.itmo.lab6.server.network;



import ru.itmo.lab6.common.util.Request;
import ru.itmo.lab6.common.util.Response;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Класс для получения запросов
 */
public class Receiver {
    private final SocketChannel socketChannel;
    private static final int BUFFER_SIZE = 65536;
    private ByteArrayOutputStream pendingData = new ByteArrayOutputStream();
    private boolean eof = false;

    public Receiver(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    /**
     * @return полученный запрос
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Request receiveRequest() throws IOException, ClassNotFoundException {
        byte[] data = readBuffer();

        try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            Request request = (Request) ois.readObject();
            pendingData.reset();
            return request;
        } catch (EOFException | StreamCorruptedException | OptionalDataException e) {
            return null;
        }
    }

    /**
     * @return прочитанный массив байтов
     * @throws IOException
     */
    public byte[] readBuffer() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        int bytesRead = socketChannel.read(buffer);

        if (bytesRead == -1) {
            eof = true;
            return null;
        }

        if (bytesRead > 0) {
            buffer.flip();
            byte[] data = new byte[buffer.remaining()];
            buffer.get(data);
            pendingData.write(data);
        }

        byte[] allData = pendingData.toByteArray();
        if (allData.length == 0) {
            return null;
        }
        return allData;
    }

    /**
     * Отправить ответ по запросу
     * @param response
     * @throws IOException
     */
    public void sendResponse(Response response) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(response);
        oos.flush();

        byte[] data = bos.toByteArray();
        ByteBuffer buffer = ByteBuffer.wrap(data);

        while (buffer.hasRemaining()) {
            socketChannel.write(buffer);
        }
    }

    public boolean isEof() {
        return eof;
    }
}