package data;

/**
 * Лейбл
 */
public class Label {
    private final String name;
    private final long bands;

    public Label(String name, long bands) {
        this.name = name;
        this.bands = bands;
    }

    public String getName() {
        return name;
    }

    public long getBands() {
        return bands;
    }

    @Override
    public String toString() {
        return "{"
                + "name = '" + name + '\''
                + ", bands = '" + bands
                + '\''
                + '}';
    }
}
