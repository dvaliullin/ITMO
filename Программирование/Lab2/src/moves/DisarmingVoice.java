package moves;
import ru.ifmo.se.pokemon.*;


public class DisarmingVoice extends SpecialMove {
    public DisarmingVoice() {
        super(Type.FAIRY, 40, Double.POSITIVE_INFINITY);
    }

    @Override
    protected String describe() {
        return "применяет специальную феерическую атаку";
    }
}

