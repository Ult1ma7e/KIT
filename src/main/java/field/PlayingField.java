package field;

import commands.FileReader;

import java.util.ArrayList;
import java.util.List;

public class PlayingField {

    public static List<String> board;
    public static List<String> boardFinal = new ArrayList<>();

    private PlayingField() {
    }

    public static void getBoard() {
        board = FileReader.readAllLines("/Projects/ChaosGame/module/Fields/4playersfield.txt");
        for (int j = 0; j < board.size(); j++) {
            System.out.println(board.get(j));
        }


        for (String str : board) {
            for (int i = 0; i < str.length(); i++) {
                boardFinal.add(String.valueOf(str.charAt(i)));
            }
        }

    }


    public static int[] getPosition(List<String> field, char target) {
        for (int x = 0; x < field.size(); x++) {
            String currectString = field.get(x);
            for (int y = 0; y < currectString.length(); y++) {
                if (currectString.charAt(y) == target) {
                    return new int[]{x,y};
                }
            }
        }
        return new int[]{-1,-1};
    }


    public static void printPosition() {
        char target = 'b';
        int[] position = PlayingField.getPosition(board, target);
        if (position[0] != -1)  {
            System.out.println("Character found at : (" + position[0] + "," + position[1] + ")");
        } else {
            System.out.println("Character not found");
        }
    }

}

