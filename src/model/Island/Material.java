package model.Island;

public class Material{
    // How many are of them on the island
    private int tree_number;
    private int fruit_tree_number;
    private int water_number;
    private int clay_number;

    // How many of them known by the player
    private int know_tree_number;
    private int know_fruit_tree_number;
    private int know_water_number;

    // getters and setters:
    // wood number
    public int getTree_number() {
        return tree_number;
    }
    public void setTree_number(int tree_number) {
        this.tree_number = tree_number;
    }
    // fruit wood number
    public int getFruit_tree_number() {
        return fruit_tree_number;
    }
    public void setFruit_tree_number(int fruit_tree_number) {
        this.fruit_tree_number = fruit_tree_number;
    }
    // water number
    public int getWater_number() {
        return water_number;
    }
    public void setWater_number(int water_number) {
        this.water_number = water_number;
    }
    // clay number
    public int getClay_number() {
        return clay_number;
    }
    public void setClay_number(int clay_number) {
        this.clay_number = clay_number;
    }
    // know wood number
    public int getKnow_tree_number() {
        return know_tree_number;
    }
    public void setKnow_tree_number(int know_tree_number) {
        this.know_tree_number = know_tree_number;
    }
    // know fruit wood number
    public int getKnow_fruit_tree_number() {
        return know_fruit_tree_number;
    }
    public void setKnow_fruit_tree_number(int know_fruit_tree_number) {
        this.know_fruit_tree_number = know_fruit_tree_number;
    }
    // know water number
    public int getKnow_water_number() {
        return know_water_number;
    }
    public void setKnow_water_number(int know_water_number) {
        this.know_water_number = know_water_number;
    }
}
