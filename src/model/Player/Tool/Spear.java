package model.Player.Tool;

import java.lang.Math;
public class Spear extends Tool{

    // Properties:
    private boolean have_spear = false; // false

    // Getters and setters:
    public boolean isHave_spear() {
        return have_spear;
    }
    public void setHave_spear(boolean have_spear) {
        this.have_spear = have_spear;
    }

    // Methods
    public boolean isSpearBreaking(){
        return Math.random() * 100 <= 10;
    }
}
