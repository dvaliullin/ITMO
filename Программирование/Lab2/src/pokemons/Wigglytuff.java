package pokemons;
import ru.ifmo.se.pokemon.*;
import moves.*;


public class Wigglytuff extends Jigglypuff {
    public Wigglytuff(String name,int level) {
        super(name,level);
        setStats(140,70,45,85,50,45);
        this.addMove(new ChargeBeam());
    }
}
