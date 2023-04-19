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
        System.out.println("\n\nThis is a survival console game. Everything what you could do is will be on the screen. \n" +
                "Every action you make cost time. You will be more thirstier, hungrier and so on by time. \nBe carefull, if any condition is reach 40%, " +
                "you lose 5% health in every hour. \nYour condition also increase 1% in every hour. If your health is reached 0%, then you lose. " +
                "\n In the beginning you don't know how many sources really on the Island, you know just a small part of it.");
        // set the character's name:
        System.out.print("\nSet your character's name: ");
    }
    // the story of the game:
    public void storyTelling(String name){
        System.out.println("\n\nYou, " + name + " and your friends decided to buy a sailboat to celebrate your fresh diploma...\n" +
                "... You recognise yourself in an island in the middle of nowhere...");
    }
    // printing details:
    public void detailsWrite(int health, int fatigue, int thirst, int hunger, boolean starving){
        System.out.println("\n\nHealth: " + health + "\t\tHunger: " + hunger + "\t\tThirst: " + thirst + "\t\tFatigue: " + fatigue + "\t\tStarving: " + ((starving) ? "True" : "False"));
    }

}
