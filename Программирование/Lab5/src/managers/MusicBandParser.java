package managers;

import java.util.*;
import data.*;
import exceptions.*;

/**
 * Класс для парсинга объектов MusicBand
 */
public class MusicBandParser {
    private final OutputManager outputManager;
    private final Set<Integer> ids = new HashSet<>();

    public MusicBandParser(OutputManager outputManager) {
        this.outputManager = outputManager;
    }

    /**
     * @param data
     * @return коллекцию TreeMap<Integer, MusicBand>
     */
    public TreeMap<Integer, MusicBand> parseToTreeMap(List<List<String>> data) {
        TreeMap<Integer, MusicBand> musicBands = new TreeMap<>();

        for (List<String> fields : data) {
            try {
                if (fields.get(0).trim().isEmpty()) {
                    throw new KeyException("Отсутствует ключ");
                }
                Integer key = Integer.parseInt(fields.get(0));
                if (key <= 0) {
                    throw new KeyException("Ключ должен быть больше 0");
                }
                MusicBand band = deserializeMusicBand(fields);
                musicBands.put(key, band);
            } catch (Exception e) {
                outputManager.printError("Парсинг строки: " + fields + " - " + e.getMessage());
            }
        }
        return musicBands;
    }

    /**
     * @param collection
     * @return список списков полей элементов коллекции
     */
    public List<List<String>> parseToList(TreeMap<Integer, MusicBand> collection) {
        List<List<String>> result = new ArrayList<>();

        for (Map.Entry<Integer, MusicBand> entry : collection.entrySet()) {
            Integer key = entry.getKey();
            MusicBand band = entry.getValue();
            List<String> row = serializeMusicBand(band);
            //row.addFirst(String.valueOf(key));
            row.add(0, String.valueOf(key));
            result.add(row);
        }

        return result;
    }

    /**
     * @param fields
     * @return объект MusicBand
     */
    public MusicBand deserializeMusicBand(List<String> fields) {
        ids.clear();

        if (fields == null || fields.size() < 9) {
            throw new IllegalArgumentException("Недостаточно полей для создания MusicBand");
        }

        String strId = fields.get(1);
        if (strId.isEmpty()) throw new IllegalArgumentException("id не может быть пустым");
        Integer id = Integer.parseInt(strId);
        if (id <= 0) throw new IllegalValueException("id должен быть больше 0");
        if (ids.contains(id)) throw new IllegalArgumentException("id должен быть уникальным");
        ids.add(id);

        String name = fields.get(2);
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name не может быть пустым");
        }

        String strX = fields.get(3);
        if (strX.trim().isEmpty()) {
            throw new NumberFormatException("X должен быть int");
        }
        int x = Integer.parseInt(strX);

        String strY = fields.get(4);
        if (strY.trim().isEmpty()) {
            throw new NullPointerException("Y не может быть null");
        }
        Long y = Long.parseLong(strY);

        Coordinates coordinates = new Coordinates(x, y);

        String participantsStr = fields.get(5);
        Integer participants = null;
        if (!participantsStr.trim().isEmpty()) {
            participants = Integer.parseInt(participantsStr);
            if (participants <= 0) {
                throw new NumberFormatException("NumberOfParticipants должно быть больше 0");
            }
        }

        String genreStr = fields.get(6).toUpperCase();
        MusicGenre genre = null;
        if (!genreStr.trim().isEmpty()) {
            try {
                genre = MusicGenre.valueOf(genreStr);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Жанр музыки " + fields.get(6) + " недоступен");
            }
        }

        long bands = Long.parseLong(fields.get(8));
        if (bands < 0) {
            throw new IllegalValueException("число групп не должно быть меньше 0");

        }

        Label label = new Label(fields.get(7), Long.parseLong(fields.get(8)));

        return new MusicBand(id, name, coordinates, participants, genre, label);
    }

    /**
     * @param band
     * @return список полей объекта MusicBand
     */
    public List<String> serializeMusicBand(MusicBand band) {
        List<String> fields = new ArrayList<>();

        fields.add(String.valueOf(band.getId()));

        fields.add(band.getName() != null ? band.getName() : "");

        Coordinates coordinates = band.getCoordinates();
        if (coordinates != null) {
            fields.add(String.valueOf(coordinates.getX()));
            fields.add(coordinates.getY() != null ? coordinates.getY().toString() : "");
        } else {
            fields.add("");
            fields.add("");
        }

        fields.add(band.getNumberOfParticipants() != null ?
                band.getNumberOfParticipants().toString() : "");

        fields.add(band.getGenre() != null ? band.getGenre().name() : "");

        Label label = band.getLabel();
        if (label != null) {
            fields.add(label.getName() != null ? label.getName() : "");
            fields.add(String.valueOf(label.getBands()));
        } else {
            fields.add("");
            fields.add("0");
        }

        return fields;
    }
}

