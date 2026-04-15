package managers;

import exceptions.*;
import data.*;

/**
 * Класс для чтения полей MusicBand
 */
public class MusicBandReader {
    private final InputManager inputManager;

    public MusicBandReader(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    /**
     * @return название группы
     * @throws IllegalValueException
     */
    public String readMusicBandName() throws IllegalValueException {
        String name = inputManager.readln().trim();
        if (name.isEmpty())
            throw new IllegalValueException("Поле не может быть пустым");
        return name;
    }

    /**
     * @return число участников группы
     * @throws IllegalValueException
     * @throws NumberFormatException
     */
    public Integer readNumberOfParticipants() throws IllegalValueException, NumberFormatException {
        String stringNumber = inputManager.readln().trim();
        Integer numberOfParticipants = Integer.parseInt(stringNumber);
        if (numberOfParticipants <= 0) throw new IllegalValueException("Значение поля должно быть больше 0");
        return numberOfParticipants;
    }

    /**
     * @return координату x
     * @throws IllegalValueException
     * @throws NumberFormatException
     */
    public int readX() throws IllegalValueException, NumberFormatException {
        String x = inputManager.readln().trim();
        return Integer.parseInt(x);
    }

    /**
     * @return координату y
     * @throws NumberFormatException
     */
    public Long readY() throws NumberFormatException {
        String y = inputManager.readln().trim();
        if (y.trim().isEmpty()) throw new IllegalValueException("Y не может быть null");
        return Long.parseLong(y);
    }

    /**
     * @return название лейбла
     * @throws IllegalValueException
     */
    public String readLabelName() throws IllegalValueException {
        return inputManager.readln().trim();
    }

    /**
     * @return число групп на лейбле
     * @throws NumberFormatException
     */
    public long readBands() throws NumberFormatException {
        String strBands = inputManager.readln().trim();
        Long bands = Long.parseLong(strBands);
        if (bands < 0) throw new IllegalValueException("Значение поля не должно быть меньше 0");
        return bands;
    }

    /**
     * @return жанр музыки
     * @throws IllegalArgumentException
     */
    public MusicGenre readMusicGenre() throws IllegalArgumentException {
        String strGenre = inputManager.readln().trim().toUpperCase();
        MusicGenre musicGenre = null;
        if (!strGenre.isEmpty()) {
            musicGenre = MusicGenre.valueOf(strGenre);
        }
        return musicGenre;
    }
}

