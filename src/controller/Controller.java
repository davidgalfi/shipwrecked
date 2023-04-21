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
    public void detailsWrite(){viewer.detailsWrite(player.getHealth(), player.getFatigue(), player.getThirst(), player.getHunger(), player.isStarving(),
            island.getMaterial().getKnow_tree_number(), island.getMaterial().getKnow_fruit_tree_number(), island.getMaterial().getKnow_water_number(),
            island.getAnimal().getKnow_bear(), island.getAnimal().getKnow_rabbit(), player.getWood(), player.getClay(), player.getFruit(), player.getRaw_meat(),
            player.getRoast_meat(), time.getTime_now());}
    public void whatPlayerCanDo(){viewer.whatPlayerCanDo();}

    // controlling the island:

    // controlling the Player:
    public boolean isPlayerDead(){return player.getHealth() <= 0;}
    public void setTimeTo10(){time.setTime_now(10);}
    public boolean isPlayerSaved(){
        if(time.getTime_now() == 10 || (time.isNew_day() && time.getTime_now() >= 10)){
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
        if(player.getFatigue() >= 100){
            player.setFatigue(100);
            // Itt kéne még valami!
        }
        if(player.getHunger() >= 100 || player.getThirst() >= 100){
            killPlayer();
        }
    }
    public void playerDoSomething(String number){
        System.out.println("Your chose: " + number + ".");
        switch (Integer.parseInt(number)){
            case 0:
                viewer.whatPlayerCanDo();
                break;
            case 1:
                if(island.getMaterial().getKnow_tree_number() > 0){
                    if(island.getMaterial().getWood_number() != 0){
                        doneSomething(8);
                        int wood = player.getWood();
                        player.setWood(wood + 8);
                        int tree = island.getMaterial().getKnow_tree_number();
                        island.getMaterial().setKnow_tree_number(tree - 1);
                        tree = island.getMaterial().getWood_number();
                        island.getMaterial().setWood_number(tree - 1);
                        System.out.println("\nYou cut down a tree! You collected 8 wood...");
                    } else {
                        System.out.println("There is no tree left on the island!");
                    }
                } else {
                    System.out.println("\nYou did not discover trees!");
                }
                break;
            case 2:
                doneSomething(14);
                System.out.println("\nYou slept well! Fatigue: 0");
                player.setFatigue(0);
                int health = player.getHealth();
                player.setHealth((int)(health * 1.35));
                break;
            case 3:
                if(island.getMaterial().getClay_number() != 0){
                    doneSomething(2);
                    System.out.println("\nYou mined! You collected 5 clay");
                    int clay = player.getClay();
                    player.setClay(clay + 5);
                    clay = island.getMaterial().getClay_number();
                    island.getMaterial().setClay_number(clay - 1);
                } else {
                    System.out.println("There is no clay left on the island!");
                }
                break;
            case 4:
                if(!player.getTorch().isHave_torch()){
                    if(player.getWood() >= 1){
                        doneSomething(1);
                        System.out.println("You have a torch now!");
                        player.getTorch().setHave_torch(true);
                        int wood = player.getWood();
                        player.setWood(--wood);
                    } else {
                        System.out.println("You have not enough wood!");
                    }
                } else{
                    System.out.println("You already have a torch!");
                }
                break;
            case 5:
                if(!player.getFire().isFire_is_alive()){
                    if(player.getWood() >= 2){
                        doneSomething(1);
                        System.out.println("You have fire now!");
                        player.getFire().setFire_is_alive(true);
                        int wood = player.getWood();
                        player.setWood(--wood);
                    } else {
                        System.out.println("You have not enough wood!");
                    }
                } else {
                    System.out.println("You already made a fire!");
                }
                break;
            case 6:

        }
    }
    public void conditionIncreasing(int hour){
        if(time.getElapsed_time() != 0){
            int fatigue = player.getFatigue();
            int hunger = player.getHunger();
            int thirst = player.getThirst();
            player.setAllcondition(fatigue+hour,hunger+hour,thirst+hour);
        }
    }
    public void torchBroke(){
        if(player.getTorch().isHave_torch()){
            if(time.isNew_day()){
                player.getTorch().setHave_torch(false);
                System.out.println("Your torch is broke!");
            }
        }
    }
    public void fireStillAlive(int hour){
        if(player.getFire().isFire_is_alive()){
            int time = player.getFire().getFire_alive_time();
            player.getFire().setFire_alive_time(time + hour);
            time = player.getFire().getFire_alive_time();
            if(time >= 24){
                player.getFire().setFire_alive_time(0);
                player.getFire().setFire_is_alive(false);
                System.out.println("You don't have fire anymore!");
            }
        }
    }
    // controlling the Time:
    public void timeElapse(int hour){
        int elapsed_times = time.getElapsed_time();
        int time_now = time.getTime_now() + hour;
        int elapsed_days = time.getElapsed_days();
        fireStillAlive(hour);
        time.setElapsed_time(elapsed_times + hour);
        if( time_now > 23){
            time.setTime_now(time_now - 24);
            elapsed_days++;
            time.setElapsed_days(elapsed_days);
            time.setNew_day(true);
            torchBroke();
        } else {
            time.setTime_now(time_now);
        }
    }

    public void doneSomething(int hour){
        timeElapse(hour);
        conditionIncreasing(hour);
        conditionCheck();
        playerStarving(hour);
    }

    // cheats:
    public void killPlayer(){player.setHealth(0);}
}
