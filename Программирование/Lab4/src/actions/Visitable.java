package actions;
import java.time.LocalDate;

public interface Visitable {
    void visit(LocalDate date);
    void leave(LocalDate date);
}
