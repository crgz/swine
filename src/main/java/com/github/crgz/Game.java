package com.github.crgz;

import com.github.crgz.dice.Die;
import com.github.crgz.dice.FairDie;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game {

    public static final int WON = -1;
    private final int cells;
    private final Die die;

    public Game() {
        this(50, new FairDie());
    }

    public Game(int cells, Die die) {
        this.cells = cells;
        this.die = die;
    }

    public void play() {
        System.out.println("=== Start of the program ===");
        System.out.print("Welcome to the game Pig! ");
        Scanner in = new Scanner(System.in);
        int nPlayers = nPlayers(in);
        int[] games = new int[nPlayers];
        do {
            int[] scores = new int[nPlayers];
            boolean done = false;
            for (int turn = 0; !done; ++turn) {
                int player = turn % nPlayers;
                int progress = play(player, scores, in);
                if (progress == WON) {
                    ++games[player];
                    System.out.printf("\n<<< You have won J%d. Congratulations! >>>\n\n", player + 1);
                    done = true;
                } else {
                    scores[player] += progress;
                }
            }
        } while (repeat(in));
        for (int i = 0; i < games.length; ++i) {
            System.out.printf("J%d has won %d times.\n", i + 1, games[i]);
        }
        System.out.print("\n=== End of the program ===\n");
    }

    private int play(int player, int[] scores, Scanner in) {
        printBoard(scores, player);
        int progress = 0;
        do {
            int result = this.die.roll();
            System.out.printf("Die result: %s. ", result);
            if (result != 6) {
                progress += result;
                if (scores[player] + progress >= this.cells) {
                    return WON;
                }
            } else {
                System.out.println("End of turn, no points.");
                return 0;
            }
        } while (stay(in, progress));
        return progress;
    }

    private static int nPlayers(Scanner in) {
        while (true) {
            System.out.print("Enter the number of players: ");
            try {
                String line = nextLine(in);
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("You must enter a valid number. ");
            }
        }
    }

    private static void printBoard(int[] scores, int player) {
        String board = IntStream.range(0, scores.length)
                .mapToObj(i -> String.format("J%s-%s", i + 1, scores[i]))
                .collect(Collectors.joining(" "));
        System.out.printf("\nBoard: %s <<< It's J%s's turn. >>>\n", board, player + 1);
    }

    private static boolean stay(Scanner in, int progress) {
        while (true) {
            System.out.print("Enter [D] to roll, or [P] to stand: ");
            String line = nextLine(in);
            switch (line.charAt(0)) {
                case 'D':
                    return true;
                case 'P':
                    System.out.printf("You have decided to stand, you advanced %s positions.\n", progress);
                    return false;
            }
        }
    }

    private static boolean repeat(Scanner in) {
        while (true) {
            System.out.print("Enter [R] to repeat [S] to exit: ");
            String line = nextLine(in);
            switch (line.charAt(0)) {
                case 'R':
                    return true;
                case 'S':
                    return false;
            }
        }
    }

    private static String nextLine(Scanner in) {
        String line = in.nextLine();
        if (System.console() == null) {
            System.out.println(line);
        }
        return line;
    }

    public static void main(String[] args) {
        new Game().play();
    }
}
