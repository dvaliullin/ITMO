package ru.itmo.lab6.server.util;

import ru.itmo.lab6.common.data.MusicBand;
import ru.itmo.lab6.server.Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Класс для управления коллекцией
 */
public class CollectionManager {
    private TreeMap<Integer, MusicBand> collection = new TreeMap<>();
    private final Date initializationDate = new Date();
    private final MusicBandParser musicBandParser;
    private String filepath;

    public CollectionManager(MusicBandParser musicBandParser) {
        this.musicBandParser = musicBandParser;
    }

    /**
     * @return информация о коллекции
     */
    public String collectionInfo() {
        return "Тип: " + collection.getClass().getName() + "\n"
                + "Дата инициализации: " + initializationDate + "\n"
                + "Количество элементов: " + collection.size();
    }

    @Override
    public String toString() {
        if (collection.isEmpty()) {
            return "Коллекция пуста";
        }
        StringBuilder sb = new StringBuilder();
        collection.forEach((key, value) -> {
            sb.append("Ключ: ").append(key).append("\n");
            sb.append(value.toString()).append("\n\n");
        });
        return sb.toString();
    }

    public TreeMap<Integer, MusicBand> getCollection() {
        return collection;
    }

    /**
     * Добавляет элемент в коллекцию
     *
     * @param key
     * @param band
     */
    public void addElement(Integer key, MusicBand band) {
        collection.put(key, band);
    }

    /**
     * @param id
     * @return true, если найден заданный id
     */
    public boolean containsId(Integer id) {
        return collection.values().stream()
                .anyMatch(v -> v.getId().equals(id));
    }

    /**
     * @param key
     * @return элемент коллекции по ключу
     */
    public MusicBand getByKey(Integer key) {
        return collection.get(key);
    }

    /**
     * @param id
     * @return элемент коллекции по id
     */
    public MusicBand getById(Integer id) {
        return collection.values().stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .get();
    }

    /**
     * @param key
     * @return true, если есть элемент с заданным ключом
     */
    public boolean containsKey(Integer key) {
        return collection.containsKey(key);
    }

    /**
     * Удалить элемент коллекции с заданным ключом
     *
     * @param key
     */
    public void remove(Integer key) {
        collection.remove(key);
    }

    /**
     * Очистить коллекцию
     */
    public void clear() {
        collection.clear();
    }

    /**
     * Удалить элементы больше заданного
     *
     * @param musicBand
     */
    public int removeGreater(MusicBand musicBand) {
        collection.entrySet().removeIf(e -> e.getValue().compareTo(musicBand) > 0);
        int removedCount = (int) collection.entrySet().stream()
                .filter(e -> e.getValue().compareTo(musicBand) > 0)
                .count();
        return removedCount;
    }

    /**
     * Удалить элементы с ключами больше заданного
     *
     * @param key
     */
    public int removeGreaterKey(Integer key) {
        collection.entrySet().removeIf(e -> e.getKey() > key);
        int removedCount = (int) collection.entrySet().stream()
                .filter(e -> e.getKey() > key)
                .count();
        return removedCount;
    }

    /**
     * @param number
     * @return число элементов с числом участников группы больше заданного
     */
    public long countGreaterThanNumberOfParticipants(Integer number) {
        return collection.values().stream()
                .filter(v -> v.getNumberOfParticipants() != null)
                .filter(v -> v.getNumberOfParticipants() > number)
                .count();
    }

    /**
     * @return отсортированную коллекцию
     */
    public TreeMap<Integer, MusicBand> getSortedCollection() {
        return collection.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(TreeMap::new,
                        (m, e) -> m.put(e.getKey(), e.getValue()),
                        Map::putAll);
    }

    /**
     * @return список чисел участников группы всех элементов коллекции
     */
    public List<Integer> getNumberOfParticipantsList() {
        return collection.values().stream().map(MusicBand::getNumberOfParticipants)
                .filter(Objects::nonNull).toList();
    }

    /**
     * Создать новую коллекцию
     *
     * @param filepath
     */
    public void createCollection(String filepath) {
        try {
            File file = new File(filepath);
            if (!file.exists()) throw new FileNotFoundException("Не найден файл с таким именем");
            setFilepath(filepath);
            if (file.length() != 0) {
                setCollection(musicBandParser.parseToTreeMap(Parser.readCSV(file)));
                if (!collection.isEmpty()) {
                    MusicBand.nextId = getMaxId() + 1;
                }
            } else {
                setCollection(new TreeMap<>());
            }
        } catch (FileNotFoundException e) {
            Server.logger.error(e.getMessage());
        }
    }

    /**
     * @return максимальный id среди всех элементов коллекции
     */
    public Integer getMaxId() {
        return collection.values().stream()
                .mapToInt(MusicBand::getId)
                .max()
                .orElse(0);
    }

    /**
     * Установить коллекцию
     *
     * @param collection
     */
    public void setCollection(TreeMap<Integer, MusicBand> collection) {
        this.collection = collection;
    }

    /**
     * Сохранить коллекцию в csv файл
     */
    public void save() {
        Parser.writeCsv(musicBandParser.parseToList(collection), filepath);
    }

    /**
     * Установить путь к файлу
     *
     * @param filepath
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}

