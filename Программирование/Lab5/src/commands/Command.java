package commands;

import exceptions.*;

/**
 * Класс-предок всех классов-команд
 */
public abstract class Command {
    private final String name;
    private final String description;

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Выполнение команды
     * @param argument
     * @throws IllegalArgumentException
     * @throws IllegalValueException
     * @throws KeyException
     * @throws NumberFormatException
     */
    public abstract void execute(String argument) throws IllegalArgumentException, IllegalValueException, KeyException, NumberFormatException;


    /**
     * @return имя команды
     */
    public String getName() {
        return name;
    }

    /**
     * @return описание команды
     */
    public String getDescription() {
        return description;
    }
}

