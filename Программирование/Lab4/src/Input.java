import events.EventDate;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Input {
    private static final Scanner scanner = new Scanner(System.in);

    public static int intInput(String message) {
        System.out.print(message);

        while (!scanner.hasNextInt()) {
            System.out.print("Нужно ввести целое число\n" + message);
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public static String stringInput(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public static LocalDate dateInput(String message) {
        LocalDate date = null;

        while (date == null) {
            System.out.print(message);
            String input = scanner.nextLine();
            try {
                date = EventDate.parseDate(input);
            } catch (DateTimeParseException e) {
                System.out.println("Неподдерживаемый формат даты");
            }
        }
        return date;
    }

    public static void close() {
        scanner.close();
    }
}
