package moves;
import ru.ifmo.se.pokemon.*;


public class DoubleTeam extends StatusMove {
    public DoubleTeam() {
        super(Type.NORMAL, 0, 0);
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        Effect e = new Effect().stat(Stat.EVASION, 1);
    }

    @Override
    protected String describe() {
        return "применяет статусную нормальную атаку";
    }
}

