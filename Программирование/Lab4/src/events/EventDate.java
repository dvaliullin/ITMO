package events;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class EventDate {
    private static final DateTimeFormatter[] formatters = {
            DateTimeFormatter.ofPattern("dd.MM.yyyy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("ru"))
    };

    public static LocalDate parseDate(String input) {
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException ignored) { }
        }
        return null;
    }
}
