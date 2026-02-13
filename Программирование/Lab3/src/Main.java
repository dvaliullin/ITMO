import characters.*;
import places.Island;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Survivor survivor = new Survivor("Робинзон Крузо");
        Savages savages = new Savages("Дикари");
        Island island = new Island("Остров");
        LocalDate startDate = LocalDate.of(1659, 5, 25);

        island.addVisitor(survivor);
        island.addVisitor(savages);

        island.startEvents(startDate);
        }
    }
