package managers;

import data.*;

/**
 * Класс для создания объектов MusicBand
 */
public class MusicBandCreator {
    private final MusicBandReader reader;
    private final Creator creator;
    private final OutputManager outputManager;

    public MusicBandCreator(MusicBandReader reader, Creator creator, OutputManager outputManager) {
        this.reader = reader;
        this.creator = creator;
        this.outputManager = outputManager;
    }

    /**
     * @return название музыкальной группы
     */
    public String createMusicBandName() {
        return creator.create("Введите название группы: ", reader::readMusicBandName);
    }

    /**
     * @return число участников группы
     */
    public Integer createNumberOfParticipants() {
        return creator.create("Введите число участников группы: ", reader::readNumberOfParticipants);
    }

    /**
     * @return координату x
     */
    public int createX() {
        return creator.create("Введите x: ", reader::readX);
    }

    /**
     * @return координату y
     */
    public Long createY() {
        return creator.create("Введите y: ", reader::readY);
    }

    /**
     * @return объект координаты
     */
    public Coordinates createCoordinates() {
        outputManager.println("Введите координаты: ");
        return new Coordinates(this.createX(), this.createY());
    }

    /**
     * @return название лейбла
     */
    public String createLabelName() {
        return creator.create("Введите название лейбла: ", reader::readLabelName);
    }

    /**
     * @return число групп на лейбле
     */
    public long createBands() {
        return creator.create("Введите число групп на лейбле: ", reader::readBands);
    }

    /**
     * @return объект лейбл
     */
    public Label createLabel() {
        outputManager.println("Введите информацию о лейбле: ");
        return new Label(createLabelName(), createBands());
    }

    /**
     * @return объект жанр музыки
     */
    public MusicGenre createMusicGenre() {
        return creator.create("Выберите музыкальный жанр:\n PSYCHEDELIC_CLOUD_RAP\n POP\n POST_ROCK\n",
                reader::readMusicGenre);
    }

    /**
     * @return объект MusicBand
     */
    public MusicBand createMusicBand() {
        return new MusicBand(this.createMusicBandName(), this.createCoordinates(), this.createNumberOfParticipants(),
                this.createMusicGenre(), this.createLabel());
    }
}

