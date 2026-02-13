package move;
import ru.ifmo.se.pokemon.*;



public class WildCharge extends PhysicalMove {
    public WildCharge() {
        super(Type.ELECTRIC, 90, 100);
    }
    @Override
    protected void applySelfDamage(Pokemon p, double d) {
        p.setMod(Stat.HP,(int) Math.round(d/4));
    }
    @Override
    protected String describe() {
        return "применяет физическую электрическую атаку";
    }
}