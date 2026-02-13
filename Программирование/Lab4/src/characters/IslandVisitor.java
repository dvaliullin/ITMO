package characters;
import actions.Visitable;
import exceptions.*;
import java.time.LocalDate;

public abstract class IslandVisitor extends Character implements Visitable {
    public LocalDate arrivalDate;
    public LocalDate departureDate;
    private int stayDuration;
    public boolean isOnIsland = false;
    private VisitorActivity activity;


    protected IslandVisitor(String name, LocalDate arrivalDate, int stayDuration) throws NameException {
        super(name);
        this.arrivalDate = arrivalDate;
        this.stayDuration = stayDuration;
        this.departureDate = arrivalDate.plusDays(stayDuration);
    }

    public abstract void meetVisitor(IslandVisitor visitor);

    public abstract void seeVisitor(IslandVisitor visitor);

    @Override
    public void act(LocalDate date, Character c) throws ScenarioException {
        IslandVisitor visitor = (IslandVisitor) c;

        if (date.equals(arrivalDate)) {
            visit(arrivalDate);
        }

        if (visitor.isOnIsland && Math.random() < 0.5) {
            seeVisitor(visitor);
            meetVisitor(visitor);
        }

        if (date.equals(departureDate)) {
            leave(departureDate);
        }
    }

    @Override
    public void visit(LocalDate date) {
        setArrivalDate(date);
        this.isOnIsland = true;
    }

    @Override
    public void leave(LocalDate date) {
        setDepartureDate(date);
        this.isOnIsland = false;
    }

    public void setArrivalDate(LocalDate date) { this.arrivalDate = date; }

    public LocalDate getArrivalDate() { return arrivalDate; }

    public void setDepartureDate(LocalDate date) { this.departureDate = date; }

    public LocalDate getDepartureDate() { return departureDate; }

    public void setStayDuration(int stayDuration) { this.stayDuration = stayDuration; }

    public int getStayDuration() { return stayDuration; }

    public void setActivity(VisitorActivity activity) { this.activity = activity; }

    public VisitorActivity getActivity() { return activity; }

    @Override
    public int hashCode() { return getName().hashCode(); }

    @Override
    public String toString() { return "посетитель " + this.getName(); }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof IslandVisitor) || this.hashCode() != o.hashCode()) { return false; }
        IslandVisitor other = (IslandVisitor) o;
        return this.getName().equals(other.getName());
    }

    @Override
    public String describe(LocalDate date, Character c) {
        return this.getName() + "действует на острове" + date;
    }
}
