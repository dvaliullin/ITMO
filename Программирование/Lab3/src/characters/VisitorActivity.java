package characters;

public enum VisitorActivity {
    HUNTING("охота"),
    RESEARCH("исследование"),
    LONG_STAY("длительное пребывание"),
    ISLAND_ABSENCE("отсутствие на острове");

    private final String description;

    VisitorActivity(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
