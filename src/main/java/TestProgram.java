import java.util.Scanner;
import java.util.concurrent.RecursiveTask;

public class TestProgram {
    public static final int GRID_SIZE = 10;
    public static char[][] gameGrid = new char[GRID_SIZE][GRID_SIZE];
    public static int playerAX = 9;
    public static int playerAY = 1;
    public static int playerBX = 9;
    public static int playerBY = 8;
    public static Scanner scanner = new Scanner(System.in);
    public static boolean isPlayerATurn = true;


    public static void initializeGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                gameGrid[i][j] = '.';


                gameGrid[2][3] = 'O';
                gameGrid[2][8] = 'O';
                gameGrid[4][5] = 'O';
                gameGrid[4][8] = 'O';
                gameGrid[7][4] = 'O';
                gameGrid[3][0] = 'O';
                gameGrid[6][1] = 'O';
                gameGrid[7][8] = 'O';
                gameGrid[8][1] = 'O';
                gameGrid[9][2] = 'O';


                gameGrid[0][5] = 'T';
            }
        }

        gameGrid[playerAX][playerAY] = 'A';
        gameGrid[playerBX][playerBY] = 'B';
    }

    public static void printGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {

                System.out.print(gameGrid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void movePlayer(char move, int numOfSteps) {

        int currentX;
        int currentY;

        char playerSymbol;

        if (isPlayerATurn) {
            currentX = playerAX;
            currentY = playerAY;
            playerSymbol = 'A';
        } else {
            currentX = playerBX;
            currentY = playerBY;
            playerSymbol = 'B';
        }

        gameGrid[currentX][currentY] = '.';

        for (int i = 0; i < numOfSteps; i ++) {
            switch (move) {
                case 'W':
                    if ((currentX > 0 && currentX < GRID_SIZE)) {

                        if (gameGrid[currentX - 1][currentY] == 'T') {
                            currentX--;
                            System.out.println("Player " + playerSymbol + " won!!!");
                            return;
                        } else if (checkAvailableCell(gameGrid, currentX - 1, currentY)) {
                            if (gameGrid[currentX - 1][currentY] == 'O') {
                                moveObstacle();
                            }
                            currentX--;
                        }
                    } else {
                        System.out.println("Input incorrect");
                    }
                    break;


                case 'S':
                    if (currentX < GRID_SIZE - 1 && currentX > - 1) {

                        if (gameGrid[currentX + 1][currentY] == 'T') {
                            currentX++;
                            System.out.println("Player " + playerSymbol + " won!!!");
                            return;
                        } else if (checkAvailableCell(gameGrid, currentX + 1, currentY)) {
                            if (gameGrid[currentX + 1][currentY] == 'O') {
                                moveObstacle();
                            }
                            currentX++;
                        }
                    } else {
                        System.out.println("Incorrect direction");
                    }
                    break;


                case 'A':
                    if ((currentY > 0 && currentY < GRID_SIZE)) {

                        if (gameGrid[currentX][currentY - 1] == 'T') {
                            currentY--;
                            System.out.println("Player " + playerSymbol + " won!!!");
                            return;
                        } else if (checkAvailableCell(gameGrid, currentX, currentY - 1)) {
                            if (gameGrid[currentX][currentY - 1] == 'O') {
                                moveObstacle();
                            }
                            currentY--;
                        }
                    } else {
                        System.out.println("Input incorrect");
                    }
                    break;


                case 'D':
                    if ((currentY > - 1 && currentY < GRID_SIZE - 1)) {

                        if (gameGrid[currentX][currentY + 1] == 'T') {
                            currentY++;
                            System.out.println("Player " + playerSymbol + " won!!!");
                            return;
                        } else if (checkAvailableCell(gameGrid, currentX, currentY + 1)) {
                            if (gameGrid[currentX][currentY + 1] == 'O') {
                                moveObstacle();
                            }
                            currentY++;
                        }
                    } else {
                        System.out.println("Input incorrect");
                    }
                    break;


                default:
                    System.out.println("Incorrect input");
                    return;
            }

        }

        gameGrid[currentX][currentY] = playerSymbol;

        if (isPlayerATurn) {
            playerAX = currentX;
            playerAY = currentY;
        } else {
            playerBX = currentX;
            playerBY = currentY;
        }

        isPlayerATurn = ! isPlayerATurn;

    }

    public static void moveObstacle() {

        System.out.println("Introduce coordinates : ");
        String input = scanner.nextLine();
        String[] numbers = input.split(",");

        int x = Integer.parseInt(numbers[0].trim());
        int y = Integer.parseInt(numbers[1].trim());

        if ((x > - 1 && x < GRID_SIZE) && (y > - 1 && y < GRID_SIZE) && (checkAvailableCellForObstacles(gameGrid, x, y))) {
                gameGrid[x][y] = 'O';

        } else {
            System.out.println("Invalid obstacles coordinates");
            moveObstacle();
        }
    }

    public static boolean checkAvailableCell(char[][] field, int i, int j) {
        return field[i][j] == '.' || field[i][j] == 'T' || field[i][j] == 'O';
    }


    public static boolean checkAvailableCellForObstacles(char[][] field, int i, int j) {
        return field[i][j] == '.';

    }


    public static boolean checkAvailableDotCell(char[][] field, int i, int j) {
        return field[i][j] == '.';

    }


    public static boolean checkAvailableDirection(char input) {
        return input == 'W' || input == 'S' || input == 'D' || input == 'A';
    }

    public static void startGame() {
        initializeGrid();
        printGrid();

        while (true) {
            System.out.println("Player " + (TestProgram.isPlayerATurn ? 'A' : 'B') + " 's turn");

            String inputSentence = scanner.nextLine();

            if (inputSentence.equals("quit")) {
                System.out.println("Game ended");
                break;
            }

            try {
                movePlayerWithNumber(inputSentence);
            } catch (Exception e) {
                System.out.println("Invalid input format.");
            }

            printGrid();
        }
    }

    public static boolean checkAvailableNumber(int num) {
        return num >= 1 && num <= 6;
    }

    public static void movePlayerWithNumber(String input) {

        while (true) {
            String[] parts = input.split(" ");

            if (parts.length != 2) {
                System.out.println("Error: Invalit format of input");
                input = scanner.nextLine();
                return;
            }

            int steps = 0;

            try {
                steps = Integer.parseInt(parts[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number");
                input = scanner.nextLine();
                return;
            }

            String direction = parts[1];
            char originalDirection = direction.charAt(0);

            if (!checkAvailableNumber(steps) || !checkAvailableDirection(originalDirection)) {
                System.out.println("Error: Invalid input. Please enter valid Input");
                input = scanner.nextLine();
                return;
            }

            movePlayer(originalDirection, steps);

        }
    }
}
