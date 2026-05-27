package ru.itmo.lab6.server.commands;

import ru.itmo.lab6.common.data.MusicBand;
import ru.itmo.lab6.common.util.CommandRequirement;
import ru.itmo.lab6.common.util.Response;

/**
 * Команда для завершения программы
 */
public class Exit extends Command {

    public Exit() {
        super("exit", "завершить программу (без сохранения в файл)", CommandRequirement.EXIT);
    }

    /**
     * @param argument пустой
     */
    @Override
    public Response execute(String argument, MusicBand element) {
        if (!argument.isEmpty() || element != null) {
            throw new IllegalArgumentException("Команда не принимает аргументы");
        }
        return new Response(true, getRequirement().name());
    }
}