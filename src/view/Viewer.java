package view;

public class Viewer {
    // Constructor:
    // Functions:
    // StartMenu:
    public void startMenu_starter(){
        System.out.println("\n\n\nWelcome to the game of shipwrecked!");
        System.out.print("\nDo you want to play?(yes, no): ");
    }
    // begin the game:
    public void beginTheGame(){
        // game description:
        System.out.println("\n\nThis is a survival console game. Things you should know before playing:\n" +
                "- Every action you make cost you hours. You will be more thirstier, hungrier and so on by time.\n" +
                "- Watch carefully to your health bar: If your health bar reaches the 0%, then you will lose the game.\n" +
                "- You also can lose by letting your character's hunger or thirst reach 100%\n" +
                "- If any condition reaches 40%, than you lose 1% health in every hour\n" +
                "- You can do many things but some conditions has some price or condition, for example:\n" +
                "You have to discover materials and animals to make action with them\n" +
                "- You can win the game if a saver ship save you. The change by that is 0.5% in every day at 10 hour.\n" +
                "- In the end of the game you will see your elapsed time of your survival on the island");
        // set the character's name:
        System.out.print("\nSet your character's name: ");
    }
    // the story of the game:
    public void storyTelling(String name){
        System.out.println("\n\nYou, " + name + " and your friends decided to buy a sailboat to celebrate your fresh diploma...\n" +
                "... You recognise yourself in an island in the middle of nowhere...");
    }
    // printing details:
    public void detailsWrite(int health, int fatigue, int thirst, int hunger, boolean starving, int wood, int fruit, int water, int bear, int rabbit,
                             int kwood, int kclay, int kfruit, int kraw_meat, int kroast_meat, int time){
        System.out.println("\n\n--------------------------------------------------------------------------------------------------------");
        System.out.println("Time: " + time
                + "\n* Condition:" + "\t\tHealth: " + health + "\t\tHunger: " + hunger + "\t\tThirst: " + thirst
                + "\t\tFatigue: " + fatigue + "\t\tStarving: " + ((starving) ? "True" : "False")
                + "\n* Sources:  " + "\t\tWoods: " + kwood + "\t\tClays: " + kclay + "\t\tFruits: " + kfruit + "\t\tRaw meats: " + kraw_meat + "\tRoast meat: " + kroast_meat
                + "\n| Known materials: " + "\t\tTree: " + wood + "\t\tFruit tree: " + fruit + "\t\tWater sources: " + water
                + "\n| Known animals: " + "\t\tBears: " + bear +"\tRabbits: " + rabbit);
        System.out.println("--------------------------------------------------------------------------------------------------------");
    }
    // Printing actions that the player can make
    public void whatPlayerCanDo(){
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Write this again(0)");
        System.out.println("Cut tree(1): 8 hour - need known tree - get 10 wood");
        System.out.println("Sleeping(2): 14 hour - fatigue: 0 and gain 35% health");
        System.out.println("Mining(3): 2 hour - get 5 clay from one");
        System.out.println("Torch making(4): 1 hour - need 1 wood - night has no effect on you");
        System.out.println("Make fire(5): 1 hour - need 2 wood - lasts for 24 hour");
        System.out.println("Drink(6): if you have vessel 0 hour, otherwise 4 hour -- thirst gets down by 10");
        System.out.println("Make vessel(7): 1 hour - need 2 clay and fire - you can drink without time cost");
        System.out.println("Make shelter(8): 12 hour - need 14 wood - sleeping cost 12 hour");
        System.out.println("Make hut(9): 26 hour - need 32 wood - sleeping cost 10 hour");
        System.out.println("Make house(10): 34 hour - need 32 wood and 12 clay - sleeping cost 8 hour");
        System.out.println("Discover(11): 10 hour - Chance to find materials and resources");
        System.out.println("Hunting(12): 8 hour - get raw meat");
        System.out.println("Make spear(13): 1 hour - need 4 wood - less harder to kill bears");
        System.out.println("Make rod(14): 1 hour - need 8 wood");
        System.out.println("Fishing(15): 8 hour - need a rod - get 1 raw meat");
        System.out.println("Plant tree(16): 4 hour - need 1 wood - in 8 days you can cut that tree");
        System.out.println("Rabbit breeding(17): 6 hour - need known rabbit - in 6 days there be a new rabbit");
        System.out.println("Cook(18): 1 hour - need 1 raw meat and fire - transform a raw meat into a roast meat");
        System.out.println("Eating(19): 0 hour - need food - raw meat: 8, roast meat: 16, fruit: 5");
        System.out.println("Picking fruit(20): 10 hour - need known fruit tree - get 4 fruit");
        System.out.println("Plant fruit tree(21): 8 hour - need 1 fruit - in 8 days there be a new fruit tree");
        System.out.println("Cut fruit tree(22): 8 hour - get 7 wood");
        System.out.println("..........................................................................................");
        System.out.println("Enter the number of the action you want to perform... the action number is between:'()'...");
        System.out.println("..........................................................................................");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    public void endOfTheGame(int end_number, int day, int hour){
        switch(end_number){
            // Saved
            case 1:
                System.out.println("You saved by a ship!");
                break;
            // Killed by hunger
            case 2:
                System.out.println("You starved to death!");
                break;
            // Killed by thirst
            case 3:
                System.out.println("You died of thirst!");
                break;
            // Killed by the lack of immune system
            case 4:
                System.out.println("You died from a weak immune system!");
                break;
        }
        System.out.println("The number of time you survived on the island: " + day + " days and " + hour + " hours.");
    }

}
