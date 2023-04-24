package model.Player.Building;

public class Fire extends Building{
    private int fire_alive_time;
    private boolean fire_is_alive;

    public Fire(){
        fire_alive_time = 0;
        fire_is_alive = false;
    }

    // getters setters
    // fire is alive
    public boolean isFire_is_alive() {
        return fire_is_alive;
    }
    public void setFire_is_alive(boolean fire_is_alive) {
        this.fire_is_alive = fire_is_alive;
    }

    // fire alive time
    public int getFire_alive_time() {
        return fire_alive_time;
    }
    public void setFire_alive_time(int fire_alive_time) {
        this.fire_alive_time = fire_alive_time;
    }
}
