package moves;
import ru.ifmo.se.pokemon.*;


public class FocusBlast extends SpecialMove {
    public FocusBlast() {
        super(Type.FIGHTING, 120, 70);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() < 0.1) {
            Effect e = new Effect().stat(Stat.SPECIAL_DEFENSE,-1);
            p.addEffect(e);
        }
    }

    @Override
    public String describe() {
        return "применяет специальную боевую атаку";
    }
}

