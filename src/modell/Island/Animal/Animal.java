package modell.Island.Animal;

public class Animal {
    // How many are of them in the island
    private int rabbit_number;
    private int bear_number;
    // How many animals the player know:
    private int know_bear;
    private int know_rabbit;

    // getters and setters:
    // bear number
    public int getBear_number() {
        return bear_number;
    }
    public void setBear_number(int bear_number) {
        this.bear_number = bear_number;
    }
    // rabbit number
    public int getRabbit_number() {
        return rabbit_number;
    }
    public void setRabbit_number(int rabbit_number) {
        this.rabbit_number = rabbit_number;
    }
    // know bear:
    public int getKnow_bear() {
        return know_bear;
    }
    public void setKnow_bear(int know_bear) {
        this.know_bear = know_bear;
    }
    // know rabbit
    public int getKnow_rabbit() {
        return know_rabbit;
    }
    public void setKnow_rabbit(int know_rabbit) {
        this.know_rabbit = know_rabbit;
    }
}
