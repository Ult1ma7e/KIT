package commands;

import java.util.Random;
import java.util.Random;
import java.util.Scanner;

public class InputOutputHandler {

    public static final Scanner scanner = new Scanner(System.in);
    public static String input = "roll dice";
    public static String inputSentence;
    public static String number;

    public boolean checkNumber() {
        String[] sentence = inputSentence.split(" ");

        number = sentence[sentence.length - 1];

        String[] numbers = {"1", "2", "3", "4", "5", "6"};

        for (String s : numbers) {
            if (s.equals(number)) {
                return true;
            }
        }
        return false;
    }

    public void diceRollWithoutSeed() {
        inputSentence = scanner.nextLine();// roll dice 6

        if (checkNumber() && inputSentence.equals("roll dice " + number)) {
            System.out.println(number);
        } else {
            System.err.println("Bullshit");
        }

    }

    public void diceRollWithSeed() {
        inputSentence = scanner.nextLine();

        if (inputSentence.equals(input)) {
            System.out.println(InputOutputHandler.randomNumber());
        } else {
            System.out.println("Incorrect input");
        }

    }

    public static int randomNumber() {
        Random randomNum = new Random();
        return randomNum.nextInt(6) + 1;
    }

    public void helpCommand() {
        String input = scanner.nextLine();

        if (input.equals("help")) {
            System.out.println("help: " + 2);
        }
    }

}
