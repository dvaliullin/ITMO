package pokemons;
import ru.ifmo.se.pokemon.*;
import moves.*;




public class Zygarde extends Pokemon {
    public Zygarde(String name, int level) {
        super(name,level);
        setType(Type.DRAGON,Type.GROUND);
        setStats(108,100,121,81,95,95);
        this.setMove(new FocusBlast(),new DracoMeteor(),new RockSlide(),new Confide());
    }
}
