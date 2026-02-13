package characters;
import actions.Attackable;
import exceptions.NameException;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

public class Savages extends IslandVisitor implements Attackable {
    public LocalDate arrivalDate;
    public LocalDate departureDate;
    private int stayDuration;
    private CharacterState state = CharacterState.CALM;
    private int aggressionLevel = 0;
    public int savagesNumber;
    private VisitorActivity activity = VisitorActivity.ISLAND_ABSENCE;
    public boolean isOnIsland = false;

    public Savages(String name, LocalDate arrivalDate, int stayDuration) throws NameException {
        super(name, arrivalDate, stayDuration);
    }

    @Override
    public void act(LocalDate date, Character c) {
        Survivor survivor = (Survivor) c;

        if (this.getActivity() != VisitorActivity.ISLAND_ABSENCE && date.equals(getDepartureDate())) {
            leave(date);
        }

        if (Math.random() < 0.5 && date.isAfter(getDepartureDate())) {
            visit(date);

            if (survivor.isOnIsland && Math.random() < 0.1) {
                seeVisitor(survivor);
            }

            if (date.equals(survivor.getArrivalDate().plusYears(23)) && date.getMonth().equals(Month.MAY)) {
                meetVisitor(survivor);
            }
        }
    }

    @Override
    public void visit(LocalDate date) {
        this.isOnIsland = true;
        setArrivalDate(date);
        setStayDuration((int) (Math.random() * 3 + 1));
        setSavagesNumber((int) (Math.random() * 5 + 1));

        if (this.getStayDuration() == 1) {
            this.setActivity(VisitorActivity.HUNTING);
            increaseAggression(10);
        }

        if (this.getStayDuration() == 2) {
            this.setActivity(VisitorActivity.RESEARCH);
        }

        if (this.getStayDuration() == 3) {
            this.setActivity(VisitorActivity.LONG_STAY);
        }

        setDepartureDate(arrivalDate.plusDays(getStayDuration()));
    }


    public void leave(LocalDate date) {
        this.isOnIsland = false;
        setDepartureDate(date);
        setState(CharacterState.CALM);
        setActivity(VisitorActivity.ISLAND_ABSENCE);
        setAggressionLevel(0);
    }

    @Override
    public void seeVisitor(IslandVisitor visitor) {
        Survivor survivor = (Survivor) visitor;
        increaseAggression(30);
        setActivity(VisitorActivity.HUNTING);
        if (getState().equals(CharacterState.AGGRESSIVE)) {
            scareSurvivor(survivor);
        }
    }

    @Override
    public void meetVisitor(IslandVisitor visitor) {
        Survivor survivor = (Survivor) visitor;
        increaseAggression(50);
        if (getState().equals(CharacterState.AGGRESSIVE)) {
            attack(survivor);
        }
    }

    public void scareSurvivor(Survivor survivor) {
        survivor.increaseAnxiety(getAggressionLevel() * getSavagesNumber());
    }

    @Override
    public void attack(Character c) {
        Survivor survivor = (Survivor) c;
        survivor.increaseHealth(-getAggressionLevel() * getSavagesNumber());
    }

    public void increaseAggression(int amount) {
        this.aggressionLevel += amount;

        if (getAggressionLevel() > 50) {
            setState(CharacterState.AGGRESSIVE);
        }
    }

    @Override
    public void setActivity(VisitorActivity activity) { this.activity = activity; }

    @Override
    public VisitorActivity getActivity() { return activity; }

    @Override
    public void setStayDuration(int stayDuration) { this.stayDuration = stayDuration; }

    @Override
    public int getStayDuration() { return stayDuration; }

    @Override
    public void setArrivalDate(LocalDate date) { this.arrivalDate = date; }

    @Override
    public void setDepartureDate(LocalDate date) { this.departureDate = date; }

    public void setSavagesNumber(int savagesNumber) { this.savagesNumber = savagesNumber; }

    public int getSavagesNumber() { return savagesNumber; }

    public void setAggressionLevel(int level) { this.aggressionLevel = level; }

    public int getAggressionLevel() { return this.aggressionLevel; }

    @Override
    public void setState(CharacterState state) {
        this.state = state;
    }

    @Override
    public CharacterState getState() {
        return state;
    }

    @Override
    public int hashCode() { return Objects.hash(getName(), getSavagesNumber());
    }

    @Override
    public String toString() { return "дикари " + this.getName(); }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Savages) || this.hashCode() != o.hashCode()) { return false; }
        Savages other = (Savages) o;
        return this.getName().equals(other.getName()) && this.getSavagesNumber() == other.getSavagesNumber();
    }

    @Override
    public String describe(LocalDate date, Character c) {
        if (this.getActivity() != VisitorActivity.ISLAND_ABSENCE &&
                date.equals(departureDate.minusDays(1))) {
            return this.getName() + " были на острове с " + date + " по " + date.plusDays(this.getStayDuration())
                    + " для " + getActivity().getDescription() + ", их было " + getSavagesNumber();
        }

        return "";
    }
}
