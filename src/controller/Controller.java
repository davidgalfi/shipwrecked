package controller;

import model.*;
import model.Island.Island;
import model.Player.Player;
import view.*;
import java.lang.Math;
import java.sql.SQLOutput;
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
                // Checking if it's dark
                if(!time.isNight() || player.getTorch().isHave_torch()){
                    // Checking if the player knows which tree to cut.
                    if(island.getMaterial().getKnow_tree_number() > 0){
                        // Checking if there is no tree left on the island --> not necessary by the way...
                        if(island.getMaterial().getTree_number() != 0){
                            // Sending the number of hours that the action takes
                            doneSomething(8);
                            // Changing the number of trees on the island and give woods to the player
                            int wood = player.getWood();
                            player.setWood(wood + 8);
                            int tree = island.getMaterial().getKnow_tree_number();
                            island.getMaterial().setKnow_tree_number(tree - 1);
                            tree = island.getMaterial().getTree_number();
                            island.getMaterial().setTree_number(tree - 1);
                            System.out.println("\nYou cut down a tree! You collected 8 wood...");
                        } else {
                            System.out.println("There is no tree left on the island!");
                        }
                    } else {
                        System.out.println("\nYou did not discover trees!");
                    }
                } else {
                    System.out.println("It's too dark! At times like this, I can only eat" +
                            ", drink or sleep.");
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
                if(!time.isNight() || player.getTorch().isHave_torch()){
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
                } else {
                    System.out.println("It's too dark! At times like this, I can only eat" +
                            ", drink or sleep.");
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
                if(!time.isNight() || player.getTorch().isHave_torch()){
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
                } else {
                    System.out.println("It's too dark! At times like this, I can only eat" +
                            ", drink or sleep.");
                }
                break;
            // Make a hut
            case 9:
                if(!time.isNight() || player.getTorch().isHave_torch()){
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
                } else {
                    System.out.println("It's too dark! At times like this, I can only eat" +
                            ", drink or sleep.");
                }
                break;
            // Make a house
            case 10:
                if(!time.isNight() || player.getTorch().isHave_torch()){
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
                } else {
                    System.out.println("It's too dark! At times like this, I can only eat" +
                            ", drink or sleep.");
                }
                break;
            // Discover
            case 11:
                if(!time.isNight() || player.getTorch().isHave_torch()){
                    Random rand = new Random();
                    double calculate;
                    // Store permanently data for the readability
                    // Materials
                    calculate = (island.getMaterial().getTree_number() - island.getMaterial().getKnow_tree_number()) * 0.4;
                    int tree = (calculate > 0) ? rand.nextInt((int)(Math.ceil(calculate))) : 0; // tree: 50% max
                    calculate = (island.getMaterial().getFruit_tree_number()-island.getMaterial().getKnow_fruit_tree_number())*0.2;
                    int fruit_tree = (calculate > 0) ? rand.nextInt((int)Math.ceil(calculate)) : 0; // fruit tree: 30% max
                    int water;
                    if(island.getMaterial().getKnow_water_number() != island.getMaterial().getWater_number()){
                        water = (rand.nextInt(5) < 1) ? 1 : 0; // water 25%
                    } else {
                        water = 0;
                    }
                    // Animals
                    calculate = (island.getAnimal().getRabbit_number()-island.getAnimal().getKnow_rabbit())*0.2;
                    int rabbit = (calculate > 0) ? rand.nextInt((int)Math.ceil(calculate)) : 0; // rabbit 20% max
                    calculate = (island.getAnimal().getBear_number()-island.getAnimal().getKnow_bear())*0.1;
                    int bear;
                    if(island.getAnimal().getKnow_bear() != island.getAnimal().getBear_number()){
                        bear = (rand.nextInt(5) < 1) ? 1 : 0; // bear 25%
                    } else {
                        bear = 0;
                    }

                    // Discover resources for the player
                    island.getMaterial().setKnow_tree_number(island.getMaterial().getKnow_tree_number() + tree);
                    System.out.println("You found " + tree + " tree!");
                    island.getMaterial().setKnow_fruit_tree_number(island.getMaterial().getKnow_fruit_tree_number() + fruit_tree);
                    System.out.println("You found " + fruit_tree + " fruit tree!");
                    island.getMaterial().setKnow_water_number(island.getMaterial().getKnow_water_number() + water);
                    System.out.println("You found " + water + " water source!");
                    island.getAnimal().setKnow_rabbit(island.getAnimal().getKnow_rabbit() + rabbit);
                    System.out.println("You found " + rabbit + " rabbit!");
                    island.getAnimal().setKnow_bear(island.getAnimal().getKnow_bear() + bear);
                    System.out.println("You found " + bear + " bear!");

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
                if(!time.isNight() || player.getTorch().isHave_torch()){
                    // Checking if the player discovered animal
                    if(island.getAnimal().getKnow_rabbit() > 0 || island.getAnimal().getKnow_bear() > 0){
                        int rabbit = island.getAnimal().getKnow_rabbit();
                        int bear = island.getAnimal().getKnow_bear();
                        Random rand = new Random();
                        String bear_or_rabbit;
                        if(bear != 0){
                            bear_or_rabbit = (rand.nextInt(rabbit + bear) <= bear) ? "bear" : "rabbit";
                        } else {
                            bear_or_rabbit = "rabbit";
                        }
                        if(bear_or_rabbit.equals("rabbit")){
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
                            if(!player.getSpear().isHave_spear()){
                                if(rand.nextInt(100) <= 70){
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
                                if(rand.nextInt(100) <= 35){
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
                                    if(player.getSpear().isSpearBreaking()){
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
                                    if(player.getSpear().isSpearBreaking()){
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
                if(!player.getSpear().isHave_spear()){
                    // Checking if the player have enough wood
                    if(player.getWood() >= 4){
                        System.out.println("I have a spear now!");
                        // Sending the number of hours that the action takes
                        doneSomething(1);
                        // Give a spear to the player
                        player.getSpear().setHave_spear(true);
                        // Decrease the number of woods that the player has by 4
                        int wood = player.getWood() - 4;
                        player.setWood(wood);
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
                if(!player.getRod().isHave_rod()){
                    // Checking if the player has enough wood for the rod
                    if(player.getWood() >= 8){
                        System.out.println("I made a rod! I can go fishing now!");
                        // Sending the number of hours that the action takes
                        doneSomething(8);
                        // Give a rod to the player
                        player.getRod().setHave_rod(true);
                        // Decrease the number of woods that the player has by 8
                        int wood = player.getWood();
                        player.setWood(wood);
                    }
                } else {
                    System.out.println("I already have a rod..");
                }
                break;
            // Fishing
            case 15:
                // Checking if it's dark
                if(!time.isNight() || player.getTorch().isHave_torch()){
                    // Checking if the player has rod
                    if(player.getRod().isHave_rod()){
                        System.out.println("I like fishing! But I think I'm not good at it.. Maybe that's because" +
                                " I managed to caught one fish in 8 hour...");
                        // Sending the number of hours that the action takes
                        doneSomething(8);
                        // Give raw meat to the player
                        int meat = player.getRaw_meat() + 1;
                        player.setRaw_meat(meat);
                        // Checking if the rod is broke
                        if(player.getRod().isFishingRodBreaking()){
                            player.getRod().setHave_rod(false);
                            System.out.println("My rod is broke. I have to make another one.");
                        }
                    } else {
                        System.out.println("Alright.. I want to go fishing.. But I have nothing to go fishing with!");
                    }
                } else {
                    System.out.println("It's too dark! At times like this, I can only eat" +
                            ", drink or sleep.");
                }
                break;
            //
            case 16:
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
