import ru.ifmo.se.pokemon.*;
import pokemon.*;

public class Main {
    public static void main(String[] args) {
        Battle b = new Battle();
        Pokemon p1 = new Zygarde("Zygarde",1);
        Pokemon p2 = new Helioptile("Helioptile",1);
        Pokemon p3 = new Heliolisk("Heliolisk",1);
        Pokemon p4 = new Igglybuff("Igglybuff",1);
        Pokemon p5 = new Jigglypuff("Jigglypuff",1);
        Pokemon p6 = new Wigglytuff("Wigglytuff",1);

        b.addAlly(p1);
        b.addAlly(p2);
        b.addAlly(p3);
        b.addFoe(p4);
        b.addFoe(p5);
        b.addFoe(p6);
        b.go();
    }
}