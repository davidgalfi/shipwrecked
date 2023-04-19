package modell;

public class Time {
    // Time now:
    private int time_now;
    // Elapsed time:
    private int elapsed_time;
    // Elapsed days:
    private int elapsed_days;
    // New day:
    private boolean new_day;

    // constructor:
    public Time(){
        setTime_now(8);
        setElapsed_time(0);
        setNew_day(false);
        setElapsed_days(0);
    }

    // functions:
    public boolean isNight(){
        return time_now >= 20 || time_now < 6;
    }

    // getters and setters:
    // time_now:
    public int getTime_now() {
        return time_now;
    }
    public void setTime_now(int time_now) {
        this.time_now = time_now;
    }
    // elapsed time:
    public int getElapsed_time() {
        return elapsed_time;
    }
    public void setElapsed_time(int elapsed_time) {
        this.elapsed_time = elapsed_time;
    }
    // elapsed days:
    public int getElapsed_days() {
        return elapsed_days;
    }
    public void setElapsed_days(int elapsed_days) {
        this.elapsed_days = elapsed_days;
    }
    // new day:
    public boolean isNew_day() {
        return new_day;
    }
    public void setNew_day(boolean new_day) {
        this.new_day = new_day;
    }
}
