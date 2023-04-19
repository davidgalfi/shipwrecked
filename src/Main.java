import controller.Controller;

import java.util.Scanner;

public class Main {
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
            answer = sc.next();
        } while(!(answer.equals("yes")) && !(answer.equals("no")));

        if(answer.equals("yes")){
            controller.startGame();
            answer = sc.next();
            controller.setName(answer);
            System.out.print("\n<Enter>");
            sc.nextLine();
            sc.nextLine();
            int index = 0;
            while(!controller.isPlayerDead() && !controller.isPlayerSaved()){
                controller.conditionCheck();
                controller.detailsWrite();
                controller.killPlayer();
            }
        } else {
            System.exit(0);
        }
    }
}