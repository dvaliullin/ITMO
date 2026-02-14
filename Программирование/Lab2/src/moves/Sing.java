package moves;
import ru.ifmo.se.pokemon.*;


public class Sing extends StatusMove {
    public Sing() {
        super(Type.NORMAL,0,55);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        Effect e = new Effect().condition(Status.SLEEP).turns(3);
        p.addEffect(e);
    }

    @Override
    protected String describe() {
        return "применяет статусную нормальную атаку";
    }
}

