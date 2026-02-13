package moves;
import ru.ifmo.se.pokemon.*;

public class BrutalSwing extends PhysicalMove {
    public BrutalSwing() {
        super(Type.DARK,60,100,0,3);
    }

    @Override
    protected String describe() {
        return "применяет физическую темную атаку";
    }
}

