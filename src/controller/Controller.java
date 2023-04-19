package controller;

import modell.*;
import modell.Island.Island;
import modell.Player.Player;
import view.*;
import java.lang.Math;

public class Controller {
    // All object:
    // modell instatiations:
    Island island = new Island();
    Player player = new Player();
    Time time = new Time();

    // view instatiation:
    Viewer viewer = new Viewer();

    // controlling the viewer:
    public void startMenu(){
        viewer.startMenu_starter();
    }
    public void startGame(){viewer.beginTheGame();}
    public void setName(String name){viewer.storyTelling(name);}
    public void detailsWrite(){viewer.detailsWrite(player.getHealth(), player.getFatigue(), player.getThirst(), player.getHunger(), player.isStarving());}

    // controlling the island:
    // controlling the Player:
    public boolean isPlayerDead(){return player.getHealth() <= 0;}
    public void killPlayer(){player.setHealth(0);}
    public void setTimeTo10(){time.setTime_now(10);}
    public boolean isPlayerSaved(){
        if(time.getTime_now() == 10 || time.isNew_day()){
            time.setNew_day(false);
            return Math.random() * 1000 <= 5;
        } else {
            return false;
        }
    }
    public void playerStarving(int hour){
        if(player.isStarving()){
            int health = player.getHealth() - hour;
            player.setHealth(health);
        }
    }
    public void conditionCheck(){
        player.setStarving(player.getFatigue() >= 40 || player.getHunger() >= 40 || player.getThirst() >= 40);
    }
    // controlling the Time:
    public void timeElapse(int hour){
        int elapsed_times = time.getElapsed_time();
        int time_now = time.getTime_now() + hour;
        int elapsed_days = time.getElapsed_days();
        time.setElapsed_time(elapsed_times + hour);
        if( time_now > 23){
            time.setTime_now(time_now - 24);
            elapsed_days++;
            time.setElapsed_days(elapsed_days);
            time.setNew_day(true);
        } else {
            time.setTime_now(time_now);
        }
    }
}
