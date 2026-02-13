package characters;
import exceptions.*;
import java.time.LocalDate;

public abstract class Character {
    private final String name;
    private CharacterState state = CharacterState.CALM;
    private int health = 100;

    protected Character(String name) throws NameException {
        this.name = name;
        if (name.isEmpty())
            throw new NameException("У персонажа должно быть имя");
    }

    public abstract void act(LocalDate date, Character c) throws ScenarioException;

    public void increaseHealth(int amount) {
        health += amount;

        if (health < 50) {
            setState(CharacterState.INJURED);
        }
    }

    public void setHealth(int health) { this.health = health; }

    public int getHealth() { return health; }

    public void setState(CharacterState state) { this.state = state; }

    public CharacterState getState() { return state; }

    public String getName() { return name; }

    @Override
    public int hashCode() { return name.hashCode(); }

    @Override
    public String toString() { return "персонаж " + this.getName(); }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Character) || this.hashCode() != o.hashCode()) { return false; }
        Character other = (Character) o;
        return this.name.equals(other.name);
    }

    public String describe(LocalDate date, Character c) {
        return this.getName() + "действует по сценарию" + date;
    }
}
