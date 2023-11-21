package lastpencil;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final String JOHN = "John";
    private static final String JACK = "Jack";
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many pencils would you like to use:");
        int pencilsNum = ZERO;
        while (pencilsNum == ZERO) {
            pencilsNum = getPencilsNum(scanner.nextLine());
        }

        System.out.println("Who will be the first (".concat(JOHN).concat(", ").concat(JACK).concat("):"));
        String playerName = scanner.next();
        while (!playerName.equals(JOHN) && !playerName.equals(JACK)) {
            System.out.println("Choose between '".concat(JOHN).concat("' and '").concat(JACK).concat("'"));
            playerName = scanner.next();
        }

        while (pencilsNum > ZERO) {
            for (int i = ZERO; i < pencilsNum; i++) {
                System.out.print("|");
            }
            int pencilsToTake = ZERO;
            if (playerName.equals(JACK)) {
                pencilsToTake = getBotMove(pencilsNum, random);
                System.out.println(pencilsToTake);
            } else {
                printPlayerName(playerName);
                while (pencilsToTake == ZERO) {
                    pencilsToTake = getPossibleValue(pencilsNum, scanner.next());
                }
            }
            pencilsNum -= pencilsToTake;
            playerName = playerName.equals(JOHN) ? JACK : JOHN;
        }
        if (pencilsNum == ZERO) {
            System.out.println(playerName.concat(" won!"));
        }
    }

    private static int getPencilsNum(String strNum) {
        int num;
        try {
            num = Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            System.out.println("The number of pencils should be numeric");
            return ZERO;
        }
        if (num < ZERO) {
            System.out.println("The number of pencils should be numeric");
            return ZERO;
        } else if (num == ZERO) {
            System.out.println("The number of pencils should be positive");
            return ZERO;
        }
        return num;
    }

    private static int getPossibleValue(int pencilsNum, String strNum) {
        int num;
        try {
            num = Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            System.out.println("Possible values: '1', '2' or '3'");
            return ZERO;
        }
        if (num < ONE || num > THREE) {
            System.out.println("Possible values: '1', '2' or '3'");
            return ZERO;
        }
        if (pencilsNum < num) {
            System.out.println("Too many pencils were taken");
            return ZERO;
        }
        return num;
    }
    
    private static int getBotMove(int pencilsNum, Random random) {
        printPlayerName(JACK);
        int reminder = pencilsNum % FOUR;
        if (pencilsNum == ONE) {
            return pencilsNum;
        } else if (reminder == ZERO) {
            return THREE;
        } else if (reminder == THREE) {
            return TWO;
        } else if (reminder == TWO) {
            return ONE;
        } else {
            return random.nextInt(ONE, FOUR);
        }
    }

    private static void printPlayerName(String playerName) {
        String lastPunctuationMark = playerName.equals(JOHN) ? "!" : ":";
        System.out.println("\n".concat(playerName).concat("'s turn").concat(lastPunctuationMark));
    }
}
