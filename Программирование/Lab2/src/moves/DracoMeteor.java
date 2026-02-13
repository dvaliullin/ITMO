package move;
import ru.ifmo.se.pokemon.*;


public class DracoMeteor extends SpecialMove {
    public DracoMeteor() {
        super(Type.DRAGON,130,90);
    }
    @Override
    protected void applySelfEffects(Pokemon p) {
        Effect e = new Effect().stat(Stat.SPECIAL_ATTACK, -2);
        p.addEffect(e);
    }
    @Override
    protected String describe() {
        return "применяет специальную драконью атаку";
    }
}
