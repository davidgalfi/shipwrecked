import controller.Controller;

import java.util.Scanner;

public class Main {
    public static boolean isGoodNumber(String number){
        int converted_number;
        try {
            converted_number = Integer.parseInt(number);
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

        if(answer.equals("yes")){
            controller.startGame();
            answer = sc.next();
            controller.setName(answer);
            System.out.print("\n<Enter>");
            sc.nextLine();
            sc.nextLine();
            controller.whatPlayerCanDo();
            int index = 0;
            while(!controller.isPlayerDead() && !controller.isPlayerSaved()){
                do {
                    controller.detailsWrite();
                    System.out.print("\n\nWhat do you do: ");
                    answer = sc.nextLine();
                }while(!isGoodNumber(answer));
                controller.playerDoSomething(answer);
                System.out.print("\n<Enter>");
                sc.nextLine();
            }
        } else {
            System.exit(0);
        }
    }
}