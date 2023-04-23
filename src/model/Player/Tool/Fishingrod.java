package model.Player.Tool;

import java.lang.Math;
public class Fishingrod extends Tool{

    // Properties:
    private boolean have_rod = false; // false

    // Getters and setters:
    public boolean isHave_rod() {
        return have_rod;
    }
    public void setHave_rod(boolean have_rod) {
        this.have_rod = have_rod;
    }
    // Methods:
    public boolean isFishingRodBreaking(){
        return Math.random() * 100 <= 6;
    }
}
