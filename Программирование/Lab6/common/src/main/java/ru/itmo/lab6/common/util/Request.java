package ru.itmo.lab6.common.util;

import java.io.Serializable;

/**
 * Класс для запросов от клиента
 */
public class Request implements Serializable {
    private final String commandName;
    private final String commandArgument;
    private Serializable element;

    public Request(String commandName, String commandArgument, Serializable element) {
        this.commandName = commandName;
        this.commandArgument = commandArgument;
        this.element = element;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getCommandArgument() {
        return commandArgument;
    }

    public Serializable getElement() {
        return element;
    }

    public void setElement(Serializable element) {
        this.element = element;
    }

    @Override
    public String toString() {
        return "запрос команды " + commandName + " с аргументом " + commandArgument;
    }
}
