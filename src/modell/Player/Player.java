package modell.Player;

import modell.Player.Building.Building;
import modell.Player.Tool.Tool;

public class Player {
    // All objects:
    // Tools:
    private Tool tools;
    // Buildings:
    private Building buildings;
    // Health:
    private int health;
    private int fatigue;
    private int thirst;
    private int hunger;

    private boolean starving;

    // constructor:
    public Player(){
        setHealth(100);
        setFatigue(0);
        setThirst(0);
        setHunger(0);
        setStarving(false);
    }

    // getters and setters
    // tools:
    public Tool getTools() {
        return tools;
    }
    public void setTools(Tool tools) {
        this.tools = tools;
    }
    // buildings:
    public Building getBuildings() {
        return buildings;
    }

    public void setBuildings(Building buildings) {
        this.buildings = buildings;
    }
    // fatigue:
    public int getFatigue() {
        return fatigue;
    }
    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }
    // health:
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    // hunger:
    public int getHunger() {
        return hunger;
    }
    public void setHunger(int hunger) {
        this.hunger = hunger;
    }
    // thirst:
    public int getThirst() {
        return thirst;
    }
    public void setThirst(int thirst) {
        this.thirst = thirst;
    }
    // starving:
    public boolean isStarving() {
        return starving;
    }
    public void setStarving(boolean starving) {
        this.starving = starving;
    }
}
