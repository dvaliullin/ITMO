package characters;
import actions.*;
import exceptions.*;
import events.EventType;
import java.time.LocalDate;
import java.time.Month;

public class Survivor extends IslandVisitor implements Dreamable, Attackable  {
    private CharacterState state = CharacterState.CALM;
    public LocalDate arrivalDate;
    private int health = 100;
    private int anxietyLevel = 0;
    public boolean isOnIsland = false;

    public Survivor(String name) throws NameException {
        super(name);
    }

    @Override
    public void act(LocalDate date, Character c) throws ScenarioException {
        Savages savages = (Savages) c;

        if (!isOnIsland) {
            visit(date);
        }

        if (Math.random() < 0.8) {
            dream(date);
        }

        if (date.isAfter(arrivalDate.plusYears(1).plusMonths(3)) && savages.isOnIsland
        && Math.random() < 0.5) {
            seeVisitor(savages);
        }

        if (date.equals(arrivalDate.plusYears(23)) && date.getMonth().equals(Month.MAY)) {
            if (savages.isOnIsland) {
                meetVisitor(savages);
                leave(date);
            } else {
                throw new ScenarioException(getName() + "не встретился с " + savages.getName());
            }
        }
    }

    @Override
    public void visit(LocalDate date) {
        setArrivalDate(date);
        this.isOnIsland = true;
    }

    @Override
    public void leave(LocalDate date) {
        setAnxietyLevel(0);
        setState(CharacterState.CALM);
        this.isOnIsland = false;
    }

    @Override
    public void seeVisitor(IslandVisitor visitor) {
        Savages savages = (Savages) visitor;
        increaseAnxiety(savages.getSavagesNumber() * savages.getAggressionLevel());
    }

    @Override
    public void meetVisitor(IslandVisitor visitor) {
        Savages savages = (Savages) visitor;
        increaseAnxiety(savages.getSavagesNumber() * savages.getAggressionLevel() * 2);
        if (getState().equals(CharacterState.TERRIFIED)) {
            attack(savages);
        }
    }

    @Override
    public void attack(Character c) {
        Savages savages = (Savages) c;
        savages.increaseHealth(-getAnxietyLevel() / savages.getSavagesNumber());
        if (savages.getHealth() <= 0) {
            killSavages(savages);
        }
    }

    public void killSavages(Savages savages) {
        savages.setSavagesNumber(savages.getSavagesNumber() - (int) (Math.random() + 1));
        savages.increaseAggression(50);
    }

    @Override
    public void dream(LocalDate date) {
        if (Math.random() > 0.2) {
            increaseAnxiety(10);
            if (Math.random() > 0.8 && date.isAfter(arrivalDate.plusYears(1).plusMonths(3))) {
                increaseAnxiety(20);
            }
        } else {
            increaseAnxiety(-20);
        }

        setHealth(100);
    }

    public void increaseAnxiety(int amount) {
        anxietyLevel += amount;

        if (getAnxietyLevel() > 50) {
            setState(CharacterState.ANXIOUS);
        }

        if (getAnxietyLevel() > 80) {
            setState(CharacterState.TERRIFIED);
        }

        if (getAnxietyLevel() <= 50) {
            setState(CharacterState.CALM);
        }
    }

    @Override
    public void setArrivalDate(LocalDate date) { this.arrivalDate = date; }

    public void setAnxietyLevel(int level) { this.anxietyLevel = level; }

    public int getAnxietyLevel() { return this.anxietyLevel; }

    @Override
    public int getHealth() { return health; }

    @Override
    public CharacterState getState() {
        return state;
    }

    @Override
    public void setState(CharacterState state) {
        this.state = state;
    }

    @Override
    public void setHealth(int health) { this.health = health; }

    @Override
    public String toString() { return "выживший " + this.getName(); }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Survivor) || this.hashCode() != o.hashCode()) { return false; }
        IslandVisitor other = (IslandVisitor) o;
        return this.getName().equals(other.getName());
    }

    @Override
    public String describe(LocalDate date, Character c) {
        Savages savages = (Savages) c;

        if (date.equals(arrivalDate)) {
            return getName() + EventType.ISLAND_VISIT.getDescription();
        }

        if (Math.random() < 0.8) {
            if (Math.random() > 0.2) {
                if (Math.random() > 0.8 && date.isAfter(arrivalDate.plusYears(1).plusMonths(3))) {
                    return getName() + SurvivorDream.KILLING_SAVAGES.getDescription();
                }
                return getName() + SurvivorDream.NIGHTMARE.getDescription();
            } else {
                return getName() + SurvivorDream.QUIET_DREAM.getDescription();
            }
        }

        if (date.isAfter(arrivalDate.plusYears(1).plusMonths(3))
                && savages.isOnIsland) {
            return getName() + EventType.OBSERVATION.getDescription() + " " + savages.getName();
        }

        if (date.equals(arrivalDate.plusYears(23)) && date.getMonth().equals(Month.MAY)
        && savages.isOnIsland) {
            return getName() + EventType.MEETING.getDescription() + " " + savages.getName() + "и" +
                    EventType.ISLAND_LEAVING.getDescription();
        }

        return "";
    }
}
