import managers.*;

public class Main {
    public static void main(String[] args) {
        OutputManager outputManager = new OutputManager();
        InputManager inputManager = new InputManager();
        CommandManager commandManager = new CommandManager();
        Console console = new Console(commandManager, inputManager, outputManager);
        CollectionManager collectionManager = new CollectionManager(outputManager, new MusicBandParser(outputManager));
        MusicBandCreator musicBandCreator = new MusicBandCreator(new MusicBandReader(inputManager), new Creator(outputManager, console), outputManager);
        commandManager.initializeCommands(collectionManager, musicBandCreator, outputManager, console);

        String fileName = System.getenv("LAB5_FILE");
        if (fileName == null || fileName.isEmpty()) {
            outputManager.printError("Не задана переменная окружения LAB5_FILE");
            System.exit(1);
        }

        try {
            collectionManager.createCollection(fileName);
        } catch (Exception e) {
            outputManager.printError("Не удалось инициализировать коллекцию: " + e.getMessage());
            System.exit(1);
        }

        outputManager.println("Программа запущена. Введите 'help' для списка команд");
        console.runInteractive();
    }
}