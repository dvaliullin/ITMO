package ru.itmo.lab6.server.commands;

import ru.itmo.lab6.common.data.MusicBand;
import ru.itmo.lab6.common.util.CommandRequirement;
import ru.itmo.lab6.common.util.Response;

/**
 * Команда выполнения скрипта
 */
public class ExecuteScript extends Command {

    public ExecuteScript() {
        super("execute_script", "считать и исполнить скрипт из указанного файла", CommandRequirement.RUN_SCRIPT);
    }

    /**
     * @param argument имя файла
     */
    @Override
    public Response execute(String argument, MusicBand element) {
            if (argument.isEmpty()) {
                throw new IllegalArgumentException("Команде нужен аргумент");
            }
            return new Response(true, getRequirement().name());
        }
    }

