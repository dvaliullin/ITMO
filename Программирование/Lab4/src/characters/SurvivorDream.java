package characters;

public enum SurvivorDream {
    NIGHTMARE(" плохо спал, видел страшный сон"),
    KILLING_SAVAGES(" снилось, что убивает дикарей и придумывает оправдания для этой расправы"),
    QUIET_DREAM(" спокойно спал");

    private final String description;

    SurvivorDream(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}


