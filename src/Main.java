import controller.Controller;

import java.util.Scanner;

public class Main {
    // Checking the input that the player given
    public static boolean isGoodNumber(String number){
        int converted_number;
        // If it is not a number, then return false
        try {
            converted_number = Integer.parseInt(number);
            // Checking the number if it is between 0 and 22
            return converted_number <= 22 && converted_number >= 0;
        } catch (NumberFormatException e){
            return false;
        }
    }
    public static void main(String[] args) {
        // controller instantiation
        Controller controller = new Controller();
        // Creating the answer variable
        String answer;
        // Creating the scanner to be able to ask for input
        Scanner sc = new Scanner(System.in);

        do {
            // ask if you want to play
            controller.startMenu();
            // waiting for the players answer
            // If did not give a correct answer: ask again
            answer = sc.next().toLowerCase();
        } while(!(answer.equals("yes")) && !(answer.equals("no")));

        // If it is a yes, then start the game
        if(answer.equals("yes")){
            // Write game details
            controller.startGame();
            // Asking for a name for the player
            answer = sc.next();
            controller.setName(answer);
            System.out.print("\n<Enter>");
            sc.nextLine();
            sc.nextLine();
            // Writing the action that player can make
            controller.whatPlayerCanDo();
            // int index = 0; -- just for testing
            // Begin the game
            // Check the player health and the saving ship
            while(!controller.isPlayerDead() && !controller.isPlayerSaved()){
                // Check the wrong inputs
                do {
                    controller.detailsWrite();
                    System.out.print("\n\nWhat do you do: ");
                    answer = sc.nextLine();
                }while(!isGoodNumber(answer));
                // Start an action by the answer given to the controller
                controller.playerDoSomething(answer);
                // Press enter to continue
                System.out.print("\n<Enter>");
                sc.nextLine();
            }
            if(controller.isPlayerSaved()){
                System.out.println("You have been saved by a ship!");
            } else if(controller.isPlayerDead()){
                System.out.println("You are dead...");
            }
        } else {
            // If the answer is no, then quit
            System.exit(0);
        }
    }
}