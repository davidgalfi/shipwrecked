package controller;

import model.*;
import model.Island.Island;
import model.Player.Player;
import view.*;
import java.lang.Math;
import java.util.Random;

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
        // End of the game:
    public void endOfTheGame(){
        int days = time.getElapsed_days();
        int hours = time.getElapsed_time() % 24;
        if(player.isPlayer_saved())viewer.endOfTheGame(1,days,hours);
        else if(player.isPlayer_hunger_dead())viewer.endOfTheGame(2,days,hours);
        else if(player.isPlayer_thirst_dead())viewer.endOfTheGame(3,days,hours);
        else viewer.endOfTheGame(4,days,hours);
    }

    // Controlling the island:
    public void plantedTreeChecking(){
        // Checking if it's a new day
        if(time.isNew_day()){
            // Checking if the player has planted tree
            if(island.getLittle_tree_number() > 0){
                for(int k=0; k<island.getLittle_tree().length;k++){
                    int little_tree = island.getLittle_tree()[k];
                    if(little_tree != -1){
                        // Checking if any little tree is 8 days old
                        if(little_tree == 8){
                            System.out.println("A little tree is now a big adult tree!");
                            // Empty the place
                            island.setLittle_tree(k, -1);
                            // Increasing the number of trees that on the island and that the player know
                            int tree_number = island.getMaterial().getTree_number() + 1;
                            island.getMaterial().setTree_number(tree_number);
                            tree_number = island.getMaterial().getKnow_tree_number() + 1;
                            island.getMaterial().setKnow_tree_number(tree_number);
                            // Decreasing the number of planted tree by one
                            int planted_tree_number = island.getLittle_tree_number() - 1;
                            island.setLittle_tree_number(planted_tree_number);
                            // Checking if there is planted tree left
                            if(island.getLittle_tree_number() == 0)break;
                        } else {
                            // Increasing the number of days of planted trees lived
                            island.setLittle_tree(k,little_tree + 1);
                        }
                        if(island.getLittle_tree_number() == k + 1)break;
                    }
                }
            }
        }
    }
    public void plantedFruitTreeChecking(){
        // Checking if it's a new day
        if(time.isNew_day()){
            // Checking if the player has planted fruit tree
            if(island.getLittle_fruit_tree_number() > 0){
                for(int k=0; k<island.getLittle_fruit_tree().length;k++){
                    int little_fruit_tree = island.getLittle_fruit_tree()[k];
                    if(little_fruit_tree != -1){
                        // Checking if any little fruit tree is 8 days old
                        if(little_fruit_tree == 8){
                            System.out.println("A little fruit tree is now a big adult tree!");
                            // Empty the place
                            island.setLittle_fruit_tree(k, -1);
                            // Increasing the number of fruit trees that on the island and that the player know
                            int fruit_tree_number = island.getMaterial().getFruit_tree_number() + 1;
                            island.getMaterial().setFruit_tree_number(fruit_tree_number);
                            fruit_tree_number = island.getMaterial().getKnow_fruit_tree_number() + 1;
                            island.getMaterial().setKnow_fruit_tree_number(fruit_tree_number);
                            // Decreasing the number of planted fruit tree by one
                            int planted_tree_number = island.getLittle_fruit_tree_number() - 1;
                            island.setLittle_fruit_tree_number(planted_tree_number);
                            // Checking if there is any planted fruit tree left
                            if(island.getLittle_fruit_tree_number() == 0)break;
                        } else {
                            // Increasing the number of days of planted fruit trees lived
                            island.setLittle_fruit_tree(k,little_fruit_tree + 1);
                        }
                        if(island.getLittle_fruit_tree_number() == k + 1)break;
                    }
                }
            }
        }
    }
    public void bredRabbitChecking(){
        // Checking if it's a new day
        if(time.isNew_day()){
            // Checking if the player has bred rabbit
            if(island.getLittle_rabbit_number() > 0){
                for(int k=0; k<island.getLittle_rabbit().length;k++){
                    int little_rabbit = island.getLittle_rabbit()[k];
                    if(little_rabbit != -1){
                        // Checking if any little rabbit is 6 days old
                        if(little_rabbit == 6){
                            System.out.println("A little rabbit is now a big adult rabbit!");
                            // Empty the place
                            island.setLittle_rabbit(k, -1);
                            // Increasing the number of rabbit that on the island and that the player know
                            int rabbit_number = island.getAnimal().getRabbit_number() + 1;
                            island.getAnimal().setRabbit_number(rabbit_number);
                            rabbit_number = island.getAnimal().getKnow_rabbit() + 1;
                            island.getAnimal().setKnow_rabbit(rabbit_number);
                            // Decreasing the number of bred rabbit by one
                            int bred_rabbit_number = island.getLittle_rabbit_number() - 1;
                            island.setLittle_rabbit_number(bred_rabbit_number);
                            // Checking if there is bred rabbit left
                            if(island.getLittle_rabbit_number() == 0)break;
                        } else {
                            // Increasing the number of days of bred rabbit lived
                            island.setLittle_rabbit(k,little_rabbit + 1);
                        }
                        if(island.getLittle_rabbit_number() == k + 1)break;
                    }
                }
            }
        }
    }
    // Controlling the Player:
    // Checking the player's health
    public boolean isPlayerDead(){
         player.setPlayer_dead(player.getHealth() <= 0);
         return player.isPlayer_dead();
    }
    // Looking for a saving ship
    public boolean isPlayerSaved(){
        if((time.getTime_now() == 10 || (time.isNew_day() && time.getTime_now() >= 10)) && !player.isPlayer_saved()){
            time.setNew_day(false);
            player.setPlayer_saved(Math.random() * 1000 <= 5);
        }
        return player.isPlayer_saved();
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
            player.setFatigue(0);
            // Sending the number of hours that the action takes
            doneSomething(20);
            int health = (int)(player.getHealth() * 0.5);
            player.setHealth(health);
            System.out.println("I had to sleep, I was really tired..");
        }
        if(player.getHunger() >= 100){
            // If player's hunger level reaches the max level, then set health to zero
            player.setHunger(100);
            killPlayer();
            player.setPlayer_hunger_dead(true);
        }
        if(player.getThirst() >= 100){
            // If player's hunger level reaches the max level, then set health to zero
            player.setThirst(100);
            killPlayer();
            player.setPlayer_thirst_dead(true);
        }
    }
    // One of the most important function in the program:
    // Managing action that player want to do:
    public void playerDoSomething(String number){
        // Checking the action's number
        switch (Integer.parseInt(number)) {
            // Print again what actions player can make
            case 0:
                viewer.whatPlayerCanDo();
                System.out.println();
                break;
            // Cutting a tree
            case 1:
                // Checking if it's dark
                if (!time.isNight() || player.getTorch().isHave_torch()) {
                    // Checking if the player knows which tree to cut.
                    if (island.getMaterial().getKnow_tree_number() > 0) {
                        // Checking if there is no tree left on the island --> not necessary by the way...
                        if (island.getMaterial().getTree_number() != 0) {
                            // Sending the number of hours that the action takes
                            doneSomething(8);
                            // Changing the number of trees on the island and give woods to the player
                            int wood = player.getWood();
                            player.setWood(wood + 8);
                            int tree = island.getMaterial().getKnow_tree_number();
                            island.getMaterial().setKnow_tree_number(tree - 1);
                            tree = island.getMaterial().getTree_number();
                            island.getMaterial().setTree_number(tree - 1);
                            System.out.println("It was hard.. but I cut down that tree!");
                        } else {
                            System.out.println("I have a problem.. There is no tree left on the island!!!");
                        }
                    } else {
                        System.out.println("\nI have to find a tree first..");
                    }
                } else {
                    System.out.println("It's too dark! At times like this, I can only eat" +
                            ", drink or sleep.");
                }
                break;
            // Sleeping action
            case 2:
                // Checking if player have to sleep
                if(player.getFatigue() >= 15){
                    // Checking if player has any place to rest or just the ground
                    if (player.getHouse().isHave_house()) {
                        // Sending the number of hours that the action takes
                        doneSomething(8);
                        // Set Fatigue 0, and give 35% health to the player
                        System.out.println("\nI slept fantastic! It was so comfortable! I can take a snooze..");
                        player.setFatigue(0);
                        int health = player.getHealth();
                        player.setHealth((int) (health * 1.35));
                    } else if (player.getHut().isHave_hut()) {
                        // Sending the number of hours that the action takes
                        doneSomething(10);
                        // Set Fatigue 0, and give 35% health to the player
                        System.out.println("\nI slept well! It was comfortable already! Let's go to work!");
                        player.setFatigue(0);
                        int health = player.getHealth();
                        player.setHealth((int) (health * 1.35));
                    } else if (player.getShelter().isHave_shelter()) {
                        // Sending the number of hours that the action takes
                        doneSomething(12);
                        // Set Fatigue 0, and give 35% health to the player
                        System.out.println("\nI slept quite well! " +
                                "That was a bit comfortable than the ground, but not so much...");
                        player.setFatigue(0);
                        int health = player.getHealth();
                        player.setHealth((int) (health * 1.35));
                    } else {
                        // Sending the number of hours that the action takes
                        doneSomething(14);
                        // Set Fatigue 0, and give 35% health to the player
                        System.out.println("\nIt was terrible to sleep on the ground...");
                        player.setFatigue(0);
                        int health = player.getHealth();
                        player.setHealth((int) (health * 1.35));
                    }
                } else {
                    System.out.println("I'm not tired yet. Let's go back to work!");
                }
                break;
            // Mining clay
            case 3:
                if (!time.isNight() || player.getTorch().isHave_torch()) {
                    // Checking if there is no clay left on the island
                    if (island.getMaterial().getClay_number() != 0) {
                        // Give clays to the player, and decrease the number of clays by one
                        System.out.println("\nThat was horrible! But at least I found clays..");
                        int clay = player.getClay();
                        player.setClay(clay + 5);
                        clay = island.getMaterial().getClay_number();
                        island.getMaterial().setClay_number(clay - 1);
                        // Sending the number of hours that the action takes
                        doneSomething(2);
                    } else {
                        System.out.println("I have a problem.. There is no clay left on the island!!");
                    }
                } else {
                    System.out.println("It's too dark! At times like this, I can only eat" +
                            ", drink or sleep.");
                }
                break;
            // Craft a torch
            case 4:
                // Checking if the player has a torch
                if (!player.getTorch().isHave_torch()) {
                    // Checking if the player has woods
                    if (player.getWood() >= 1) {
                        // Give torch to the player
                        // and reduce the number of woods that the player has by one
                        System.out.println("Yes! I can see now in the dark!");
                        player.getTorch().setHave_torch(true);
                        int wood = player.getWood() - 1;
                        player.setWood(wood);
                        // Sending the number of hours that the action takes
                        doneSomething(1);
                    } else {
                        System.out.println("I have not enough wood for that..");
                    }
                } else {
                    System.out.println("I don't want another torch, I already have one!");
                }
                break;
            // Make a fire
            case 5:
                // Checking if the player already made a fire
                if (!player.getFire().isFire_is_alive()) {
                    // Checking if the player has woods
                    if (player.getWood() >= 2) {
                        // Make a fire and reduce the number of woods that the player has by two
                        System.out.println("Fire!! I have fire!!");
                        player.getFire().setFire_is_alive(true);
                        int wood = player.getWood() - 2;
                        player.setWood(wood);
                        // Sending the number of hours that the action takes
                        doneSomething(1);
                    } else {
                        System.out.println("I have not enough wood for that..");
                    }
                } else {
                    System.out.println("I already made a fire somewhere.. Maybe I just forgot where..");
                }
                break;
            // Drinking
            case 6:
                // Checking if the player thirst level is greater than 0
                if (player.getThirst() > 0) {
                    // Checking if the player has vessel and the vessel is filled with water
                    if (player.getVessel().isHave_vessel() && player.getVessel().getWater_filled() > 0) {
                        // Sending the number of hours that the action takes
                        doneSomething(0);
                        // Reduce the amount of water in the vessel
                        int water = player.getVessel().getWater_filled() - 1;
                        player.getVessel().setWater_filled(water);
                        System.out.println("It's better now. Thanks for me!");
                        System.out.printf("Vessel: " + water + "/4");
                        // Reduce the player's thirst to 0
                        player.setThirst(0);
                    } else {
                        // Sending the number of hours that the action takes
                        doneSomething(0);
                        // Reduce the player's thirst to 0
                        player.setThirst(0);
                        // Sending the number of hours that the action takes
                        doneSomething(4);
                        // Checking if player have vessel just the vessel is not filled with water
                        if (player.getVessel().isHave_vessel()) {
                            // Fill the vessel with water
                            player.getVessel().setWater_filled(4);
                            System.out.println("I drank and even I fully filled my vessel with water!");
                            System.out.println("You filled your vessel! Vessel: 4/4");
                        } else {
                            System.out.println("It's better now!But maybe I could make a vessel for next time!");
                        }
                    }
                } else {
                    System.out.println("I don't really have to drink, thanks by me.");
                }
                break;
            // Make a vessel
            case 7:
                // Checking if the player already has a wessel
                if (!player.getVessel().isHave_vessel()) {
                    // Checking if the player has enough clay
                    if (player.getClay() >= 2) {
                        // Checking if the player made a fire
                        if (player.getFire().isFire_is_alive()) {
                            // Give vessel to the player
                            // and reduce the number of clays that the player has by two
                            player.getVessel().setHave_vessel(true);
                            int clay = player.getClay() - 2;
                            player.setClay(clay);
                            System.out.println("Now I have a vessel!");
                            System.out.println("I have to fill that with water before I use!");
                            // Sending the number of hours that the action takes
                            doneSomething(1);
                        } else {
                            System.out.println("I need fire for that..");
                        }
                    } else {
                        System.out.println("I don't have enough clay..");
                    }
                } else {
                    System.out.println("I like my vessel, already have one!");
                }
                break;
            // Make a shelter
            case 8:
                if (!time.isNight() || player.getTorch().isHave_torch()) {
                    // Checking if the player already has a shelter
                    if (!player.getShelter().isHave_shelter()) {
                        // Checking if player has enough woods
                        if (player.getWood() >= 14) {
                            // Make a shelter and reduce the number of woods that the player has
                            player.getShelter().setHave_shelter(true);
                            int wood = player.getWood() - 12;
                            player.setWood(wood);
                            System.out.println("I have a shelter now! It's gonna be a bit conformable...");
                            // Sending the number of hours that the action takes
                            doneSomething(12);
                        } else {
                            System.out.println("I have not enough wood for that..");
                        }
                    } else {
                        System.out.println("I already have a shelter.. What could I do with two?");
                    }
                } else {
                    System.out.println("It's too dark! At times like this, I can only eat" +
                            ", drink or sleep.");
                }
                break;
            // Make a hut
            case 9:
                if (!time.isNight() || player.getTorch().isHave_torch()) {
                    // Checking if the player already has a hut
                    if (!player.getHut().isHave_hut()) {
                        // Checking if player has enough woods
                        if (player.getWood() >= 32) {
                            // Make a shut and reduce the number of woods that the player has
                            player.getHut().setHave_hut(true);
                            int wood = player.getWood() - 32;
                            player.setWood(wood);
                            System.out.println("I have a hut now! It's gonna be conformable now!");
                            // Sending the number of hours that the action takes
                            doneSomething(26);
                        } else {
                            System.out.println("I have not enough wood for that.. yet!");
                        }
                    } else {
                        System.out.println("I already have a hut.. But where?");
                    }
                } else {
                    System.out.println("It's too dark! At times like this, I can only eat" +
                            ", drink or sleep.");
                }
                break;
            // Make a house
            case 10:
                if (!time.isNight() || player.getTorch().isHave_torch()) {
                    // Checking if the player already has a house
                    if (!player.getHouse().isHave_house()) {
                        // Checking if player has enough woods
                        if (player.getWood() >= 32 && player.getClay() >= 12) {
                            // Make a house and reduce the number of woods that the player has
                            player.getHouse().setHave_house(true);
                            int wood = player.getWood() - 34;
                            player.setWood(wood);
                            System.out.println("I have a big-big house now! It's gonna be fantastic now!");
                            // Sending the number of hours that the action takes
                            doneSomething(34);
                        } else {
                            System.out.println("Noo.. I have to find more clay!");
                        }
                    } else {
                        System.out.println("No.. I already have a house. I can see that anywhere, because it is so big! Or I think so..");
                    }
                } else {
                    System.out.println("It's too dark! At times like this, I can only eat" +
                            ", drink or sleep.");
                }
                break;
            // Discover
            case 11:
                if (!time.isNight() || player.getTorch().isHave_torch()) {
                    Random rand = new Random();
                    double calculate;
                    // Store permanently data for the readability
                    // Materials
                    calculate = (island.getMaterial().getTree_number() - island.getMaterial().getKnow_tree_number()) * 0.4;
                    int tree = (calculate > 0) ? rand.nextInt((int) (Math.ceil(calculate))) : 0; // tree: 50% max
                    calculate = (island.getMaterial().getFruit_tree_number() - island.getMaterial().getKnow_fruit_tree_number()) * 0.2;
                    int fruit_tree = (calculate > 0) ? rand.nextInt((int) Math.ceil(calculate)) : 0; // fruit tree: 30% max
                    int water;
                    if (island.getMaterial().getKnow_water_number() != island.getMaterial().getWater_number()) {
                        water = (rand.nextInt(5) < 1) ? 1 : 0; // water 25%
                    } else {
                        water = 0;
                    }
                    // Animals
                    calculate = (island.getAnimal().getRabbit_number() - island.getAnimal().getKnow_rabbit()) * 0.2;
                    int rabbit = (calculate > 0) ? rand.nextInt((int) Math.ceil(calculate)) : 0; // rabbit 20% max
                    calculate = (island.getAnimal().getBear_number() - island.getAnimal().getKnow_bear()) * 0.1;
                    int bear;
                    if (island.getAnimal().getKnow_bear() != island.getAnimal().getBear_number()) {
                        bear = (rand.nextInt(5) < 1) ? 1 : 0; // bear 25%
                    } else {
                        bear = 0;
                    }

                    // Discover resources for the player
                    System.out.println("So.. What did I find:");
                    island.getMaterial().setKnow_tree_number(island.getMaterial().getKnow_tree_number() + tree);
                    System.out.println("I found " + tree + " tree!");
                    island.getMaterial().setKnow_fruit_tree_number(island.getMaterial().getKnow_fruit_tree_number() + fruit_tree);
                    System.out.println("I found " + fruit_tree + " fruit tree!");
                    island.getMaterial().setKnow_water_number(island.getMaterial().getKnow_water_number() + water);
                    System.out.println("I found " + water + " water source!");
                    island.getAnimal().setKnow_rabbit(island.getAnimal().getKnow_rabbit() + rabbit);
                    System.out.println("I found " + rabbit + " rabbit!");
                    island.getAnimal().setKnow_bear(island.getAnimal().getKnow_bear() + bear);
                    System.out.println("I found " + bear + " bear!");
                    System.out.println("Hmm.. not much..");

                    // Sending the number of hours that the action takes
                    doneSomething(10);
                } else {
                    System.out.println("It's too dark! At times like this, I can only eat" +
                            ", drink or sleep.");
                }
                break;
            // Hunting
            case 12:
                // Checking if it's dark
                if (!time.isNight() || player.getTorch().isHave_torch()) {
                    // Checking if the player discovered animal
                    if (island.getAnimal().getKnow_rabbit() > 0 || island.getAnimal().getKnow_bear() > 0) {
                        int rabbit = island.getAnimal().getKnow_rabbit();
                        int bear = island.getAnimal().getKnow_bear();
                        Random rand = new Random();
                        String bear_or_rabbit;
                        if (bear != 0) {
                            bear_or_rabbit = (rand.nextInt(rabbit + bear) <= bear) ? "bear" : "rabbit";
                        } else {
                            bear_or_rabbit = "rabbit";
                        }
                        if (bear_or_rabbit.equals("rabbit")) {
                            System.out.println("I hunted a rabbit!");
                            // Increase the number of raw meats that the player has by seven
                            int raw_meat = player.getRaw_meat() + 7;
                            player.setRaw_meat(raw_meat);
                            // Decrease the number of rabbits on the island by one
                            int rabbit_number = island.getAnimal().getRabbit_number() - 1;
                            island.getAnimal().setRabbit_number(rabbit_number);
                            // Also decrease the number of rabbits that the player know by one
                            rabbit_number = island.getAnimal().getKnow_rabbit() - 1;
                            island.getAnimal().setKnow_rabbit(rabbit_number);
                            // Sending the number of hours that the action takes
                            doneSomething(8);
                        } else {
                            System.out.println("It's a bear!!");
                            // There is 70% percent to lose 45% health if the player does not have a spear
                            // Also checking if the player has a spear
                            // If the player has a spear, then there is just 35% chance to lose 45% health
                            if (!player.getSpear().isHave_spear()) {
                                if (rand.nextInt(100) <= 70) {
                                    System.out.println("Aa.. It's so hurt! The bear broke my leg.. It almost killed me!");
                                    System.out.println("I have to make a spear next time! ");
                                    System.out.println("... or I just need to be more careful...");
                                    System.out.println("Anyway, a tree accidentally fell on the bear, so I have meat.");
                                    // Decrease the number of bears on the island by one
                                    int bear_number = island.getAnimal().getBear_number() - 1;
                                    island.getAnimal().setBear_number(bear_number);
                                    // Decrease the number bears that the player know by one
                                    bear_number = island.getAnimal().getKnow_bear() - 1;
                                    island.getAnimal().setKnow_bear(bear_number);
                                    // Increase the number of raw meats that the player has by 16
                                    int raw_meat = player.getRaw_meat() + 16;
                                    player.setRaw_meat(raw_meat);
                                    // Decrease the player's health by 45%
                                    int health = player.getHealth() - 45;
                                    player.setHealth(health);
                                    // Sending the number of hours that the action takes
                                    doneSomething(8);
                                } else {
                                    System.out.println("A tree accidentally fell on the bear, so I have meat.");
                                    System.out.println("What a luck! But have to make a spear next time! ");
                                    System.out.println("... or I just need to be more careful...");
                                    // Decrease the number of bears on the island by one
                                    int bear_number = island.getAnimal().getBear_number() - 1;
                                    island.getAnimal().setBear_number(bear_number);
                                    // Decrease the number bears that the player know by one
                                    bear_number = island.getAnimal().getKnow_bear() - 1;
                                    island.getAnimal().setKnow_bear(bear_number);
                                    // Increase the number of raw meats that the player has by 16
                                    int raw_meat = player.getRaw_meat() + 16;
                                    player.setRaw_meat(raw_meat);
                                    // Sending the number of hours that the action takes
                                    doneSomething(8);
                                }
                            } else {
                                if (rand.nextInt(100) <= 35) {
                                    System.out.println("Aa.. It's so hurt! The bear broke my leg.. It almost killed me!");
                                    System.out.println("But I managed to kill this bear!");
                                    System.out.println("I need to be more careful next time...");
                                    System.out.println("Anyway, I have meat!");
                                    // Decrease the number of bears on the island by one
                                    int bear_number = island.getAnimal().getBear_number() - 1;
                                    island.getAnimal().setBear_number(bear_number);
                                    // Decrease the number bears that the player know by one
                                    bear_number = island.getAnimal().getKnow_bear() - 1;
                                    island.getAnimal().setKnow_bear(bear_number);
                                    // Increase the number of raw meats that the player has by 16
                                    int raw_meat = player.getRaw_meat() + 16;
                                    player.setRaw_meat(raw_meat);
                                    // Decrease the player's health by 45%
                                    int health = player.getHealth() - 45;
                                    player.setHealth(health);
                                    // Checking if the player's spear is broken
                                    if (player.getSpear().isSpearBreaking()) {
                                        player.getSpear().setHave_spear(false);
                                        System.out.println("My spear is broke. That's not good..");
                                    }
                                    // Sending the number of hours that the action takes
                                    doneSomething(8);
                                } else {
                                    System.out.println("I killed the bear! That was easy...");
                                    System.out.println("Maybe it was because a tree fell on it...");
                                    System.out.println("I need to be more careful next time...");
                                    System.out.println("Anyway, I have meat!");
                                    // Decrease the number of bears on the island by one
                                    int bear_number = island.getAnimal().getBear_number() - 1;
                                    island.getAnimal().setBear_number(bear_number);
                                    // Decrease the number bears that the player know by one
                                    bear_number = island.getAnimal().getKnow_bear() - 1;
                                    island.getAnimal().setKnow_bear(bear_number);
                                    // Increase the number of raw meats that the player has by 16
                                    int raw_meat = player.getRaw_meat() + 16;
                                    player.setRaw_meat(raw_meat);
                                    // Checking if the player's spear is broken
                                    if (player.getSpear().isSpearBreaking()) {
                                        player.getSpear().setHave_spear(false);
                                        System.out.println("My spear is broke. That's not good..");
                                    }
                                    // Sending the number of hours that the action takes
                                    doneSomething(8);
                                }
                            }
                        }
                    } else {
                        System.out.println("I have to discover an animal first!");
                    }
                } else {
                    System.out.println("It's too dark! At times like this, I can only eat" +
                            ", drink or sleep.");
                }
                break;
            // Make a spear
            case 13:
                // Checking if the player has a spear
                if (!player.getSpear().isHave_spear()) {
                    // Checking if the player have enough wood
                    if (player.getWood() >= 4) {
                        System.out.println("I have a spear now!");
                        // Give a spear to the player
                        player.getSpear().setHave_spear(true);
                        // Decrease the number of woods that the player has by 4
                        int wood = player.getWood() - 4;
                        player.setWood(wood);
                        // Sending the number of hours that the action takes
                        doneSomething(1);
                    } else {
                        System.out.println("I don't have enough wood!");
                    }
                } else {
                    System.out.println("I already have a spear! I don't want another one...");
                }
                break;
            // Make a rod
            case 14:
                // Checking if the player already has a rod
                if (!player.getRod().isHave_rod()) {
                    // Checking if the player has enough wood for the rod
                    if (player.getWood() >= 8) {
                        System.out.println("I made a rod! I can go fishing now!");
                        // Give a rod to the player
                        player.getRod().setHave_rod(true);
                        // Decrease the number of woods that the player has by 8
                        int wood = player.getWood() - 8;
                        player.setWood(wood);
                        // Sending the number of hours that the action takes
                        doneSomething(8);
                    }
                } else {
                    System.out.println("I already have a rod..");
                }
                break;
            // Fishing
            case 15:
                // Checking if it's dark
                if (!time.isNight() || player.getTorch().isHave_torch()) {
                    // Checking if the player has rod
                    if (player.getRod().isHave_rod()) {
                        System.out.println("I like fishing! But I think I'm not good at it.. Maybe that's because" +
                                " I managed to caught one fish in 8 hour...");
                        // Give raw meat to the player
                        int meat = player.getRaw_meat() + 1;
                        player.setRaw_meat(meat);
                        // Checking if the rod is broke
                        if (player.getRod().isFishingRodBreaking()) {
                            player.getRod().setHave_rod(false);
                            System.out.println("My rod is broke. I have to make another one.");
                        }
                        // Sending the number of hours that the action takes
                        doneSomething(8);
                    } else {
                        System.out.println("Alright.. I want to go fishing.. But I have nothing to go fishing with!");
                    }
                } else {
                    System.out.println("It's too dark! At times like this, I can only eat" +
                            ", drink or sleep.");
                }
                break;
            // Plant a tree
            case 16:
                // Checking if player can plant a tree:
                if (island.getLittle_tree_number() != 20) {
                    // Checking if it's dark
                    if (!time.isNight() || player.getTorch().isHave_torch()) {
                        // Checking if the player has enough wood
                        if (player.getWood() >= 1) {
                            // Plant a tree
                            for (int k = 0; k < island.getLittle_tree().length; k++) {
                                if (island.getLittle_tree()[k] == -1) {
                                    island.setLittle_tree(k, 0);
                                    System.out.println("Done! I planted a tree.");
                                    int tree_number = island.getLittle_tree_number() + 1;
                                    island.setLittle_tree_number(tree_number);
                                    break;
                                }
                            }
                            // Decrease the number of woods that the player has by 1
                            int wood = player.getWood() - 1;
                            player.setWood(wood);
                            // Sending the number of hours that the action takes
                            doneSomething(4);
                        } else {
                            System.out.println("I have to chop a tree first! I don't have wood..");
                        }
                    } else {
                        System.out.println("It's too dark! At times like this, I can only eat" +
                                ", drink or sleep.");
                    }
                } else {
                    System.out.println("I planted enough tree...");
                }
                break;
            // Rabbit breeding:
            case 17:
                // Checking if player can breeding rabbit
                if (island.getLittle_rabbit_number() != 20) {
                    // Checking if it's dark
                    if (!time.isNight() || player.getTorch().isHave_torch()) {
                        // Checking if the player has known rabbits
                        if (island.getAnimal().getKnow_rabbit() > 0) {
                            // Breed a rabbit
                            for (int k = 0; k < island.getLittle_rabbit().length; k++) {
                                if (island.getLittle_rabbit()[k] == -1) {
                                    island.setLittle_rabbit(k, 0);
                                    System.out.println("I think it works. I come back later..");
                                    int rabbit_number = island.getLittle_rabbit_number() + 1;
                                    island.setLittle_tree_number(rabbit_number);
                                    break;
                                }
                            }
                            // Sending the number of hours that the action takes
                            doneSomething(4);
                        } else {
                            System.out.println("Okay, I want to do that, but how? I have to discover two rabbits first!");
                        }
                    } else {
                        System.out.println("It's too dark! At times like this, I can only eat" +
                                ", drink or sleep.");
                    }
                } else {
                    System.out.println("I have bread enough rabbit already..");
                }
                break;
            // Cook
            case 18:
                // Checking if it's dark
                if (!time.isNight() || player.getTorch().isHave_torch()) {
                    // Checking if the player has fire
                    if (player.getFire().isFire_is_alive()) {
                        // Checking if the player has raw meat
                        if (player.getRaw_meat() >= 1) {
                            System.out.println("Ouch! Hot.. But it's done. I have roasted meat!");
                            // Give roast meat to the player
                            int meat = player.getRoast_meat() + 1;
                            player.setRoast_meat(meat);
                            // Decrease the number of raw meat that the player has by 1
                            meat = player.getRaw_meat() - 1;
                            player.setRaw_meat(meat);
                            // Sending the number of hours that the action takes
                            doneSomething(1);
                        }
                    } else {
                        System.out.println("I need fire to cook!");
                    }
                } else {
                    System.out.println("It's too dark! At times like this, I can only eat" +
                            ", drink or sleep.");
                }
                break;
            // Picking fruit
            case 20:
                // Checking if it's dark
                if (!time.isNight() || player.getTorch().isHave_torch()) {
                    // Checking if the player know fruit tree
                    if (island.getMaterial().getKnow_fruit_tree_number() > 0) {
                        System.out.println("Got it! I have fruits now.");
                        // Increasing the number of fruits
                        int fruit = player.getFruit() + 4;
                        player.setFruit(fruit);
                        // Sending the number of hours that the action takes
                        doneSomething(10);
                    } else {
                        System.out.println("I have to find a fruit tree first!");
                    }
                } else {
                    System.out.println("It's too dark! At times like this, I can only eat" +
                            ", drink or sleep.");
                }
                break;
            // Fruit tree planting
            case 21:
                // Checking if player can plant a tree:
                if (island.getLittle_fruit_tree_number() != 20) {
                    // Checking if it's dark
                    if (!time.isNight() || player.getTorch().isHave_torch()) {
                        // Checking if the player has enough fruit
                        if (player.getFruit() >= 1) {
                            // Plant a fruit tree
                            for (int k = 0; k < island.getLittle_fruit_tree().length; k++) {
                                if (island.getLittle_fruit_tree()[k] == -1) {
                                    // Plant
                                    island.setLittle_fruit_tree(k, 0);
                                    System.out.println("Done! I planted a fruit tree.");
                                    // Increase the baby fruit tree number by one
                                    int tree_number = island.getLittle_fruit_tree_number() + 1;
                                    island.setLittle_fruit_tree_number(tree_number);
                                    break;
                                }
                            }
                            // Decrease the number of woods that the player has by 1
                            int wood = player.getWood() - 1;
                            player.setWood(wood);
                            // Sending the number of hours that the action takes
                            doneSomething(8);
                        } else {
                            System.out.println("I have to get a fruit first!");
                        }
                    } else {
                        System.out.println("It's too dark! At times like this, I can only eat" +
                                ", drink or sleep.");
                    }
                } else {
                    System.out.println("I planted enough fruit tree...");
                }
                break;
            // Cut fruit tree
            case 22:
                // Checking if it's dark
                if (!time.isNight() || player.getTorch().isHave_torch()) {
                    // Checking if the player knows which fruit tree to cut.
                    if (island.getMaterial().getKnow_fruit_tree_number() > 0) {
                        // Checking if there is no fruit tree left on the island --> not necessary by the way...
                        if (island.getMaterial().getFruit_tree_number() != 0) {
                            // Sending the number of hours that the action takes
                            doneSomething(8);
                            System.out.println("\nYou cut down a tree! You collected 8 wood...");
                            // Changing the number of trees on the island and give woods to the player
                            int wood = player.getWood();
                            player.setWood(wood + 8);
                            int tree = island.getMaterial().getKnow_fruit_tree_number();
                            island.getMaterial().setKnow_fruit_tree_number(tree - 1);
                            tree = island.getMaterial().getFruit_tree_number();
                            island.getMaterial().setFruit_tree_number(tree - 1);
                            // Sending the number of hours that the action takes
                            doneSomething(8);
                        } else {
                            System.out.println("There is no fruit tree left on the island!");
                        }
                    } else {
                        System.out.println("\nYou did not discover fruit trees!");
                    }
                } else {
                    System.out.println("It's too dark! At times like this, I can only eat" +
                            ", drink or sleep.");
                }
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
            if(player.getTorch().isTorch_new_day() && !time.isNight()){
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
    public void playerWantToEat(String number){
        // Checking if the player has anything to eat
        if(player.getFruit() + player.getRaw_meat() + player.getRoast_meat() > 0){
            switch(Integer.parseInt(number)){
                // fruit
                case 1:
                    // Checking if the player has fruit
                    if(player.getFruit() > 0){
                        System.out.println("That was good! What's now?");
                        // Decrease hunger by 5
                        int decrease = player.getHunger() - 5;
                        player.setHunger(decrease);
                        // Decrease fruit by 1
                        decrease = player.getFruit() - 1;
                        player.setFruit(decrease);
                        // Sending the number of hours that the action takes
                        doneSomething(0);
                    } else {
                        System.out.println("I don't have fruit..");
                    }
                    break;
                // raw meat
                case 2:
                    // Checking if the player has fruit
                    if(player.getRaw_meat() > 0){
                        System.out.println("That was good! What's now?");
                        // Decrease hunger by 8
                        int decrease = player.getHunger() - 8;
                        player.setHunger(decrease);
                        // Decrease fruit by 1
                        decrease = player.getRaw_meat() - 1;
                        player.setRaw_meat(decrease);
                        // Sending the number of hours that the action takes
                        doneSomething(0);
                    } else {
                        System.out.println("I don't have raw meat..");
                    }
                    break;
                // roast meat
                case 3:
                    // Checking if the player has fruit
                    if(player.getRoast_meat() > 0){
                        System.out.println("That was good! What's now?");
                        // Decrease hunger by 16
                        int decrease = player.getHunger() - 16;
                        player.setHunger(decrease);
                        // Decrease fruit by 1
                        decrease = player.getRoast_meat() - 1;
                        player.setRoast_meat(decrease);
                        // Sending the number of hours that the action takes
                        doneSomething(0);
                    } else {
                        System.out.println("I don't have roast meat..");
                    }
                    break;
            }
        } else {
            System.out.println("I have nothing to eat..");
        }
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
            player.getTorch().setTorch_new_day(true);
            plantedTreeChecking();
            plantedFruitTreeChecking();
            bredRabbitChecking();
        } else {
            time.setTime_now(time_now);
        }
    }

    public void doneSomething(int hour){
        timeElapse(hour);
        conditionIncreasing(hour);
        conditionCheck();
        playerStarving(hour);
        torchBroke();
        fireStillAlive(hour);
    }

    // cheats:
    public void killPlayer(){player.setHealth(0);}

    public void setTimeTo10(){time.setTime_now(10);}
    public void setPlayerHungerTo0(){player.setHunger(0);}
}
