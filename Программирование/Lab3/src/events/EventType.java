package events;

public enum EventType {
    ISLAND_VISIT(" посетил остров"),
    ISLAND_LEAVING(" покинул остров"),
    MEETING(" встретился с"),
    OBSERVATION(" увидел");

    private final String description;

    EventType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
