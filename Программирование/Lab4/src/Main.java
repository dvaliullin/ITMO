import characters.*;
import places.Island;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        String survivorName = Input.stringInput("Имя выжившего: ");
        LocalDate survivorArrivalDate = Input.dateInput("Дата прибытия на остров: ");
        int survivorDaysOnIsland = Input.intInput("Число дней на острове: ");
        Survivor survivor = new Survivor(survivorName, survivorArrivalDate, survivorDaysOnIsland);

        String savagesName = Input.stringInput("Имя дикарей: ");
        LocalDate savagesArrivalDate = Input.dateInput("Дата прибытия на остров: ");
        int savagesDaysOnIsland = Input.intInput("Число дней на острове: ");
        Savages savages = new Savages(savagesName, savagesArrivalDate, savagesDaysOnIsland);

        String islandName = Input.stringInput("Название острова: ");
        Island island = new Island(islandName);

        LocalDate startDate = Input.dateInput("Дата начала событий: ");
        int eventsDuration = Input.intInput("Длительность событий (число дней): ");
        Input.close();

        island.addVisitor(survivor);
        island.addVisitor(savages);

        island.startEvents(startDate, eventsDuration);
    }
}
