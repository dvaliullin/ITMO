package pokemons;
import ru.ifmo.se.pokemon.*;
import moves.*;



public class Jigglypuff extends Igglybuff {
    public Jigglypuff(String name,int level) {
        super(name,level);
        setStats(115,45,20,45,25,20);
        this.addMove(new DisarmingVoice());
    }
}
