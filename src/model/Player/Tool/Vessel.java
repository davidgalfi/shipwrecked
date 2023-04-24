package model.Player.Tool;

public class Vessel extends Tool{

    // Properties:
    private boolean have_vessel = false; // false
    private int water_filled = 0; // 0, max 4

    // Getters and setters:
    // have a vessel:
    public boolean isHave_vessel() {
        return have_vessel;
    }
    public void setHave_vessel(boolean have_vessel) {
        this.have_vessel = have_vessel;
    }
    // water filled:
    public int getWater_filled() {
        return water_filled;
    }
    public void setWater_filled(int water_filled) {
        this.water_filled = water_filled;
    }
}
