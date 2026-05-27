package ru.itmo.lab6.server.commands;

import ru.itmo.lab6.common.data.MusicBand;
import ru.itmo.lab6.common.util.CommandRequirement;
import ru.itmo.lab6.common.util.Response;
import ru.itmo.lab6.common.exceptions.*;

/**
 * Класс-предок всех классов-команд
 */
public abstract class Command {
    private final String name;
    private final String description;
    private final CommandRequirement requirement;

    public Command(String name, String description, CommandRequirement requirement) {
        this.name = name;
        this.description = description;
        this.requirement = requirement;
    }

    /**
     * Выполнение команды
     * @param argument
     * @throws IllegalArgumentException
     * @throws IllegalValueException
     * @throws KeyException
     * @throws NumberFormatException
     */
        public abstract Response execute(String argument, MusicBand element) throws IllegalArgumentException, IllegalValueException, KeyException, NumberFormatException;


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

    public CommandRequirement getRequirement() {
        return requirement;
    }
}

