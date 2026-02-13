package moves;
import ru.ifmo.se.pokemon.*;


public class ChargeBeam extends SpecialMove {
    public ChargeBeam() {
        super(Type.ELECTRIC,50,90);
    }
    @Override
    protected void applySelfEffects(Pokemon p) {
        if (Math.random() < 0.7) {
            Effect e = new Effect().stat(Stat.SPECIAL_ATTACK, 1);
            p.addEffect(e);
        }
    }
    @Override
    protected String describe() {
        return "применяет специальную электрическую атаку";
    }
}

