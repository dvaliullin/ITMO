package ru.itmo.lab6.client.util;

/**
 * Класс управления выводом
 */
public class OutputManager {

    /**
     * Вывод с переходом на новую строку
     * @param text
     */
    public void println(String text) {
        System.out.println(text);
    }

    /**
     * Вывод в строке
     * @param text
     */
    public void print(String text) {
        System.out.print(text);
    }

    /**
     * Вывод ошибок
     * @param text
     */
    public void printError(String text) {
        System.err.println("Ошибка: " + text);
    }
}
