package managers;

import java.util.Scanner;
import java.util.Stack;

/**
 * Класс управления вводом
 */
public class InputManager {
    private Scanner scanner = new Scanner(System.in);
    private final Stack<Scanner> scriptScanners = new Stack<>();

    /**
     * @return чтение строки
     */
    public String readln() {
        return scanner.nextLine();
    }

    /**
     * Установить сканнер
     * @param scanner
     */

    public void setFileScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * @return сканнер
     */
    public Scanner getScanner() {
        return scanner;
    }

    /**
     * Закрыть сканнер
     */
    public void close() {
        this.scanner.close();
    }

    /**
     * Положить сканнер в стек
     * @param scanner
     */
    public void push(Scanner scanner) {
        scriptScanners.push(scanner);
    }

    /**
     * @return стек сканнеров
     */
    public Stack<Scanner> getScriptScanners() {
        return scriptScanners;
    }
}

