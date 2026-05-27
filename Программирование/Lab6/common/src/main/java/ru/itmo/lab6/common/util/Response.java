package ru.itmo.lab6.common.util;

import java.io.Serializable;

/**
 * Класс для ответов от сервера
 */
public class Response implements Serializable {
    private final String message;
    private final boolean success;

    public Response(boolean success, String message) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
