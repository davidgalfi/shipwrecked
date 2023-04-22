package controller;

import model.*;
import model.Island.Island;
import model.Player.Player;
import view.*;
import java.lang.Math;

public class Controller {
    // All object:
    // Model instatiations:
    Island island = new Island();
    Player player = new Player();
    Time time = new Time();

    // View instatiation:
    Viewer viewer = new Viewer();

    // Controlling the viewer:
    // Start menu:
    public void startMenu(){
        viewer.startMenu_starter();
    }
    // Details of the game:
    public void startGame(){viewer.beginTheGame();}
    // Story of the game
    public void setName(String name){viewer.storyTelling(name);}
    // Details of the resources
    public void detailsWrite(){viewer.detailsWrite(player.getHealth(), player.getFatigue(), player.getThirst(), player.getHunger(), player.isStarving(),
            island.getMaterial().getKnow_tree_number(), island.getMaterial().getKnow_fruit_tree_number(), island.getMaterial().getKnow_water_number(),
            island.getAnimal().getKnow_bear(), island.getAnimal().getKnow_rabbit(), player.getWood(), player.getClay(), player.getFruit(), player.getRaw_meat(),
            player.getRoast_meat(), time.getTime_now());}
    // Action that player can make:
    public void whatPlayerCanDo(){viewer.whatPlayerCanDo();}

    // Controlling the island:

    // Controlling the Player:
    // Checking the player's health
    public boolean isPlayerDead(){return player.getHealth() <= 0;}
    // Looking for a saving ship
    public boolean isPlayerSaved(){
        if(time.getTime_now() == 10 || (time.isNew_day() && time.getTime_now() >= 10)){
            time.setNew_day(false);
            return Math.random() * 1000 <= 5;
        } else {
            return false;
        }
    }
    // Decreasing player's health if in the case of starving
    public void playerStarving(int hour){
        if(player.isStarving()){
            int health = player.getHealth() - hour;
            player.setHealth(health);
        }
    }
    // Check the player's conditions
    public void conditionCheck(){
        // If the player is starving, then set starving value true
        player.setStarving(player.getFatigue() >= 40 || player.getHunger() >= 40 || player.getThirst() >= 40);
        if(player.getFatigue() >= 100){
            player.setFatigue(100);
            // !!!If Fatigue reaches the max value then player has to sleep for 20 hour maybe...!!!
        }
        if(player.getHunger() >= 100 || player.getThirst() >= 100){
            // If player's hunger level reaches the max level, then set health to zero
            player.setHunger(100);
            killPlayer();
        }
    }
    // One of the most important function in the program:
    // Managing action that player want to do:
    public void playerDoSomething(String number){
        // Just for testing:
        System.out.println("Your chose: " + number + ".");
        // test's end...
        // Checking the action's number
        switch (Integer.parseInt(number)){
            // Print again what actions player can make
            case 0:
                viewer.whatPlayerCanDo();
                System.out.print("");
                break;
            // Cutting a tree
            case 1:
                // Checking if the player knows which tree to cut.
                if(island.getMaterial().getKnow_tree_number() > 0){
                    // Checking if there is no tree left on the island --> not necessary by the way...
                    if(island.getMaterial().getWood_number() != 0){
                        // Sending the number of hours that the action takes
                        doneSomething(8);
                        // Changing the number of trees on the island and give woods to the player
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
            // Sleeping action
            case 2:
                // Checking if player has any place to rest or just the ground
                if(player.getHouse().isHave_house()) {
                    // Sending the number of hours that the action takes
                    doneSomething(8);
                    // Set Fatigue 0, and give 35% health to the player
                    System.out.println("\nYou slept fantastic! It was so comfortable! Can I sleep again? Fatigue: 0");
                    player.setFatigue(0);
                    int health = player.getHealth();
                    player.setHealth((int)(health * 1.35));
                } else if(player.getHut().isHave_hut()){
                    // Sending the number of hours that the action takes
                    doneSomething(10);
                    // Set Fatigue 0, and give 35% health to the player
                    System.out.println("\nYou slept well! It was comfortable already! Let's go to work! Fatigue: 0");
                    player.setFatigue(0);
                    int health = player.getHealth();
                    player.setHealth((int)(health * 1.35));
                } else if(player.getShelter().isHave_shelter()){
                    // Sending the number of hours that the action takes
                    doneSomething(12);
                    // Set Fatigue 0, and give 35% health to the player
                    System.out.println("\nYou slept quite well! " +
                            "That was a bit comfortable than the ground, but not so much... Fatigue: 0");
                    player.setFatigue(0);
                    int health = player.getHealth();
                    player.setHealth((int)(health * 1.35));
                } else {
                    // Sending the number of hours that the action takes
                    doneSomething(14);
                    // Set Fatigue 0, and give 35% health to the player
                    System.out.println("\nYou slept! It was terrible to sleep on the ground... Fatigue: 0");
                    player.setFatigue(0);
                    int health = player.getHealth();
                    player.setHealth((int)(health * 1.35));
                }
                break;
            // Mining clay
            case 3:
                // Checking if there is no clay left on the island
                if(island.getMaterial().getClay_number() != 0){
                    // Sending the number of hours that the action takes
                    doneSomething(2);
                    // Give clays to the player, and decrease the number of clays by one
                    System.out.println("\nYou mined! You collected 5 clay");
                    int clay = player.getClay();
                    player.setClay(clay + 5);
                    clay = island.getMaterial().getClay_number();
                    island.getMaterial().setClay_number(clay - 1);
                } else {
                    System.out.println("There is no clay left on the island!");
                }
                break;
            // Craft a torch
            case 4:
                // Checking if the player has a torch
                if(!player.getTorch().isHave_torch()){
                    // Checking if the player has woods
                    if(player.getWood() >= 1){
                        // Sending the number of hours that the action takes
                        doneSomething(1);
                        // Give torch to the player
                        // and reduce the number of woods that the player has by one
                        System.out.println("You have a torch now!");
                        player.getTorch().setHave_torch(true);
                        int wood = player.getWood() - 1;
                        player.setWood(wood);
                    } else {
                        System.out.println("You have not enough wood!");
                    }
                } else{
                    System.out.println("You already have a torch!");
                }
                break;
            // Make a fire
            case 5:
                // Checking if the player already made a fire
                if(!player.getFire().isFire_is_alive()){
                    // Checking if the player has woods
                    if(player.getWood() >= 2){
                        // Sending the number of hours that the action takes
                        doneSomething(1);
                        // Make a fire and reduce the number of woods that the player has by two
                        System.out.println("You have fire now!");
                        player.getFire().setFire_is_alive(true);
                        int wood = player.getWood() - 2;
                        player.setWood(wood);
                    } else {
                        System.out.println("You have not enough wood!");
                    }
                } else {
                    System.out.println("You already made a fire!");
                }
                break;
            // Drinking
            case 6:
                // Checking if the player thirst level is greater than 0
                if(player.getThirst() > 0){
                    // Checking if the player has vessel and the vessel is filled with water
                    if(player.getVessel().isHave_vessel() && player.getVessel().getWater_filled() > 0){
                        // Reduce the player's thirst to 0
                        player.setThirst(0);
                        // Reduce the amount of water in the vessel
                        int water = player.getVessel().getWater_filled() - 1;
                        player.getVessel().setWater_filled(water);
                        System.out.println("You drunk!");
                        System.out.printf("Vessel: " + water + "/4");
                    } else {
                        // Sending the number of hours that the action takes
                        doneSomething(4);
                        // Reduce the player's thirst to 0
                        player.setThirst(0);
                        // Checking if player have vessel just the vessel is not filled with water
                        if(player.getVessel().isHave_vessel()){
                            // Fill the vessel with water
                            player.getVessel().setWater_filled(4);
                            System.out.println("You drank and even you filled your vessel with water fully!");
                            System.out.println("You filled your vessel! Vessel: 4/4");
                        } else {
                            System.out.println("You drank! Maybe you could make a vessel for next time!");
                        }
                    }
                } else {
                    System.out.println("You don't have to drink!");
                }
                break;
            // Make a vessel
            case 7:
                // Checking if the player already has a wessel
                if(!player.getVessel().isHave_vessel()){
                    // Checking if the player has enough clay
                    if(player.getClay() >= 2){
                        // Checking if the player made a fire
                        if(player.getFire().isFire_is_alive()){
                            // Sending the number of hours that the action takes
                            doneSomething(1);
                            // Give vessel to the player
                            // and reduce the number of clays that the player has by two
                            player.getVessel().setHave_vessel(true);
                            int clay = player.getClay() - 2;
                            player.setClay(clay);
                            System.out.println("Now you have a vessel!");
                            System.out.println("You have to fill that with water before you use!");
                        } else {
                            System.out.println("You have not made a fire yet!");
                        }
                    } else {
                        System.out.println("You don't have enough clay!");
                    }
                } else {
                    System.out.println("You already have a vessel!");
                }
                break;
            // Make a shelter
            case 8:
                // Checking if the player already has a shelter
                if(!player.getShelter().isHave_shelter()){
                    // Checking if player has enough woods
                    if(player.getWood() >= 14){
                        // Sending the number of hours that the action takes
                        doneSomething(12);
                        // Make a shelter and reduce the number of woods that the player has
                        player.getShelter().setHave_shelter(true);
                        int wood = player.getWood() - 12;
                        player.setWood(wood);
                        System.out.println("You have a shelter now! It's gonna be a bit conformable...");
                    } else {
                        System.out.println("You have not enough wood!");
                    }
                } else {
                    System.out.println("You already have a shelter!");
                }
                break;
            // Make a hut
            case 9:
                // Checking if the player already has a hut
                if(!player.getHut().isHave_hut()){
                    // Checking if player has enough woods
                    if(player.getWood() >= 32){
                        // Sending the number of hours that the action takes
                        doneSomething(26);
                        // Make a shut and reduce the number of woods that the player has
                        player.getHut().setHave_hut(true);
                        int wood = player.getWood() - 32;
                        player.setWood(wood);
                        System.out.println("You have a hut now! It's gonna be conformable now!");
                    } else {
                        System.out.println("You have not enough wood!");
                    }
                } else {
                    System.out.println("You already have a hut!");
                }
                break;
            // Make a house
            case 10:
                // Checking if the player already has a house
                if(!player.getHouse().isHave_house()){
                    // Checking if player has enough woods
                    if(player.getWood() >= 32 && player.getClay() >= 12){
                        // Sending the number of hours that the action takes
                        doneSomething(34);
                        // Make a shut and reduce the number of woods that the player has
                        player.getHouse().setHave_house(true);
                        int wood = player.getWood() - 34;
                        player.setWood(wood);
                        System.out.println("You have a hut now! It's gonna be fantastic now!");
                    } else {
                        System.out.println("You have not enough wood or clay!");
                    }
                } else {
                    System.out.println("You already have a house!");
                }
                break;
            // Discover
            case 11:
                System.out.println("Not working yet!");
                break;
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

    public void setTimeTo10(){time.setTime_now(10);}
}
