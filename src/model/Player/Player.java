package model.Player;

import model.Player.Building.Fire;
import model.Player.Building.House;
import model.Player.Building.Hut;
import model.Player.Building.Shelter;
import model.Player.Tool.*;

public class Player {
    // All objects:
    // Tools:
    private Torch torch;
    private Spear spear;
    private Vessel vessel;
    private Fishingrod rod;
    // Buildings:
    private Fire fire;
    private Shelter shelter;
    private Hut hut;
    private House house;
    // Health:
    private int health;
    private int fatigue;
    private int thirst;
    private int hunger;
    private boolean starving;

    // Player resources:
    private int clay;
    private int wood;
    private int fruit;
    private int raw_meat;
    private int roast_meat;
    // end of the player:
    private boolean player_saved;
    private boolean player_dead;
    private boolean player_hunger_dead;
    private boolean player_thirst_dead;


    // constructor:
    public Player(){
        setTorch(new Torch());
        setSpear(new Spear());
        setVessel(new Vessel());
        setRod(new Fishingrod());
        setFire(new Fire());
        setShelter(new Shelter());
        setHut(new Hut());
        setHouse(new House());
        setHealth(100); // 100
        setFatigue(10); // 10
        setThirst(5); // 5
        setHunger(5); // 5
        setStarving(false); // false
        setClay(0); // 0
        setWood(0); // 0
        setFruit(1); // 1
        setRaw_meat(0); // 0
        setRoast_meat(1); // 1
        setPlayer_saved(false); // false
        setPlayer_dead(false);
        setPlayer_hunger_dead(false);
        setPlayer_thirst_dead(false);
    }

    // getters and setters
    // tools:
    // torch:
    public Torch getTorch() {
        return torch;
    }
    public void setTorch(Torch torch) {
        this.torch = torch;
    }
    // spear:
    public Spear getSpear() {
        return spear;
    }
    public void setSpear(Spear spear) {
        this.spear = spear;
    }
    // vessel:
    public Vessel getVessel() {
        return vessel;
    }
    public void setVessel(Vessel vessel) {
        this.vessel = vessel;
    }
    // rod
    public Fishingrod getRod() {
        return rod;
    }
    public void setRod(Fishingrod rod) {
        this.rod = rod;
    }
    // buildings:
    // fire
    public Fire getFire() {
        return fire;
    }
    public void setFire(Fire fire) {
        this.fire = fire;
    }
    // shelter:
    public Shelter getShelter() {
        return shelter;
    }
    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }
    // hut:
    public Hut getHut() {
        return hut;
    }
    public void setHut(Hut hut) {
        this.hut = hut;
    }
    // house:
    public House getHouse() {
        return house;
    }
    public void setHouse(House house) {
        this.house = house;
    }
    // conditions:
    // fatigue:
    public int getFatigue() {
        return fatigue;
    }
    public void setFatigue(int fatigue) {
        if(fatigue > 100)this.fatigue = 100;
        else this.fatigue = Math.max(fatigue, 0);
    }
    // health:
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = Math.min(health, 100);
    }
    // hunger:
    public int getHunger() {
        return hunger;
    }
    public void setHunger(int hunger) {
        if(hunger > 100)this.hunger = 100;
        else this.hunger = Math.max(hunger, 0);
    }
    // thirst:
    public int getThirst() {
        return thirst;
    }
    public void setThirst(int thirst) {
        if(thirst > 100)this.thirst = 100;
        else this.thirst = Math.max(thirst, 0);
    }
    // starving:
    public boolean isStarving() {
        return starving;
    }
    public void setStarving(boolean starving) {
        this.starving = starving;
    }
    // clay:
    public int getClay() {
        return clay;
    }
    public void setClay(int clay) {
        this.clay = clay;
    }
    // fruit:
    public int getFruit() {
        return fruit;
    }
    public void setFruit(int fruit) {
        this.fruit = fruit;
    }
    // raw meat:
    public int getRaw_meat() {
        return raw_meat;
    }
    public void setRaw_meat(int raw_meat) {
        this.raw_meat = raw_meat;
    }
    // roast meat:
    public int getRoast_meat() {
        return roast_meat;
    }
    public void setRoast_meat(int roast_meat) {
        this.roast_meat = roast_meat;
    }
    // wood:
    public int getWood() {
        return wood;
    }
    public void setWood(int wood) {
        this.wood = wood;
    }
    // allcondition:
    public void setAllcondition(int fatigue, int hunger, int thirst){
        this.fatigue = fatigue;
        this.hunger = hunger;
        this.thirst = thirst;
    }
    // Final output:
    // player saved:
    public boolean isPlayer_saved() {
        return player_saved;
    }
    public void setPlayer_saved(boolean player_saved) {
        this.player_saved = player_saved;
    }
    // player dead:
    public boolean isPlayer_dead() {
        return player_dead;
    }
    public void setPlayer_dead(boolean player_dead) {
        this.player_dead = player_dead;
    }
    // hunger dead:
    public boolean isPlayer_hunger_dead() {
        return player_hunger_dead;
    }
    public void setPlayer_hunger_dead(boolean player_hunger_dead) {
        this.player_hunger_dead = player_hunger_dead;
    }
    // thirst dead:
    public boolean isPlayer_thirst_dead() {
        return player_thirst_dead;
    }
    public void setPlayer_thirst_dead(boolean player_thirst_dead) {
        this.player_thirst_dead = player_thirst_dead;
    }
}
