import controller.Controller;

import java.util.Scanner;

public class Main {
    // Checking the input that the player given
    public static boolean isGoodNumber(String number, int from, int to){
        int converted_number;
        // If it is not a number, then return false
        try {
            converted_number = Integer.parseInt(number);
            // Checking the number if it is between 0 and 22
            return converted_number <= to && converted_number >= from;
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
            // Begin the game
            // Check the player health and the saving ship
            while(!controller.isPlayerDead() && !controller.isPlayerSaved()){
                // Check the wrong inputs
                do {
                    controller.detailsWrite();
                    System.out.print("\n\nWhat do you do: ");
                    answer = sc.nextLine();
                }while(!isGoodNumber(answer, 0, 22));
                // Checking if player chose to eat
                if(Integer.parseInt(answer) == 19){
                    do {
                        System.out.println("\nFruit(1) -- decrease the hunger by 5");
                        System.out.println("Raw meat(2) -- decrease the hunger by 8");
                        System.out.println("Roast meat(3) -- decrease the hunger by 16");
                        System.out.print("\nWhat do you want to eat: ");
                        answer = sc.nextLine();
                    } while(!isGoodNumber(answer, 1, 3));
                    controller.playerWantToEat(answer);
                } else {
                    // Start an action by the answer given to the controller
                    controller.playerDoSomething(answer);
                }              
                // Press enter to continue
                System.out.print("\n<Enter>");
                sc.nextLine();
            }
            controller.endOfTheGame();
        } else {
            // If the answer is no, then quit
            System.exit(0);
        }
    }
}
