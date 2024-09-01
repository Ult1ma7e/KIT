package commands;

import java.io.IOException;
import java.util.Scanner;

public class InputManager {

    private static final String QUIT = "quit";

    public static void executeProgram() {
        InputOutputHandler obj = new InputOutputHandler();
        Scanner scanner = new Scanner(System.in);
        String input;

        input = scanner.nextLine();
         if (!input.equals(QUIT)) {
             obj.diceRollWithSeed();
         } else {
             System.out.println("You left the game");
         }


    }
}
