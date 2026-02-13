package move;
import ru.ifmo.se.pokemon.*;


public class WorkUp extends StatusMove {
    public WorkUp() {
        super(Type.NORMAL,0,0);
    }
    @Override
    protected void applySelfEffects(Pokemon p) {
        Effect e = new Effect().stat(Stat.ATTACK,1).stat(Stat.SPECIAL_ATTACK,1);
    }
    @Override
    protected String describe() {
        return "применяет статусную нормальную атаку";
    }
}
