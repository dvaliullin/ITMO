package events;
import java.time.LocalDate;
import java.util.Objects;

public record Event(String description, LocalDate date) {

    public LocalDate getDate() { return date; }

    @Override
    public int hashCode() { return Objects.hash(description, date); }

    @Override
    public String toString() {
        return (!description.isEmpty()) ? description + " в день " + date.getDayOfMonth() + " "
                + date.getMonth() + " " + date.getYear() : "";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Event) || this.hashCode() != o.hashCode()) { return false; }
        Event other = (Event) o;
        return this.description.equals(other.description) && this.date.equals(other.date);
    }
}