package pokemons;
import ru.ifmo.se.pokemon.*;
import moves.*;


public class Helioptile extends Pokemon {
    public Helioptile(String name, int level) {
        super(name,level);
        setType(Type.ELECTRIC, Type.NORMAL);
        setStats(44,38,33,61,43,70);
        this.setMove(new WildCharge(),new Thunder(),new DoubleTeam());
    }
}
