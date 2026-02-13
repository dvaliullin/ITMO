package places;
import exceptions.*;
import events.*;
import characters.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Island extends Place {
    public ArrayList<IslandVisitor> visitors = new ArrayList<>();
    public ArrayList<Event> events = new ArrayList<>();

    public Island(String name) throws NameException {
        super(name);
    }

    @Override
    public void startEvents(LocalDate startDate) {
        for (int days = 1; days < 8403; days++) {
            LocalDate currentDate = startDate.plusDays(days);
            for (IslandVisitor visitor : visitors) {
                for (IslandVisitor other : visitors) {
                    if (!visitor.equals(other)) {
                        try {
                            visitor.act(currentDate, other);
                            addEvent(visitor.describe(currentDate, other), currentDate);
                        } catch (ScenarioException e) {
                            e.getMessage();
                        }
                    }
                }
            }
            System.out.println(describe(currentDate));
        }
    }

    @Override
    public void addEvent(String description, LocalDate date) {
        events.add(new Event(description, date));
    }

    public void addVisitor(IslandVisitor visitor) {
        visitors.add(visitor);
    }

    @Override
    public ArrayList<Event> getEventsByDate(LocalDate date) {
        ArrayList<Event> eventsByDate = new ArrayList<>();
        for (Event event : events) {
            if (event.getDate().equals(date) && !event.toString().isEmpty()) {
                eventsByDate.add(event);
            }
        }
        return eventsByDate;
    }

    @Override
    public String toString() {
        return "остров " + this.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Island) || this.hashCode() != o.hashCode()) { return false; }
        Island other = (Island) o;
        return this.getName().equals(other.getName());
    }

    @Override
    public String describe(LocalDate date) {
        return getEventsByDate(date).toString().replace("[","").replace("]","");
    }
}
