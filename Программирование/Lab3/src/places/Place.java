package places;
import events.Event;
import exceptions.NameException;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Place {
    private final String name;
    public ArrayList<Event> events = new ArrayList<>();

    protected Place(String name) {
        this.name = name;
        if (name.isEmpty())
            throw new NameException("У места должно быть название");
    }

    public abstract void startEvents(LocalDate startDate);

    public void addEvent(String description, LocalDate date) {
        events.add(new Event(description, date));
    }

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
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "место " + this.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Place) || this.hashCode() != o.hashCode()) { return false; }
        Place other = (Place) o;
        return this.getName().equals(other.getName());
    }

    public String getName() {
        return name;
    }

    public String describe(LocalDate date) {
        return getEventsByDate(date).toString().replace("[","").replace("]","");
    }
}
