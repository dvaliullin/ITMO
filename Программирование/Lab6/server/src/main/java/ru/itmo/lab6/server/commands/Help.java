package ru.itmo.lab6.server.commands;

import ru.itmo.lab6.common.data.MusicBand;
import ru.itmo.lab6.common.util.Response;
import ru.itmo.lab6.server.util.CommandManager;

/**
 * Команда для вывода списка команд
 */
public class Help extends Command {
    private final CommandManager commandManager;

    public Help(CommandManager commandManager) {
        super("help", "вывести справку по доступным командам", null);
        this.commandManager = commandManager;
    }

    /**
     * @param argument пустой
     */
    @Override
    public Response execute(String argument, MusicBand element) {
        if (!argument.isEmpty() || element != null) throw new IllegalArgumentException("Команда не принимает аргументы");
        StringBuilder sb = new StringBuilder();
        commandManager.getCommands().forEach(command -> sb.append(command.getName())
                .append(": ").append(command.getDescription()).append("\n"));
        return new Response(true, sb.toString());
    }
}

