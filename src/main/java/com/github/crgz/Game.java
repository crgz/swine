package com.github.crgz;

import com.github.crgz.dice.Die;
import com.github.crgz.dice.FairDie;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game {

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
        System.out.println("=== Start of the program ===\nWelcome to the game Pig!");

        Scanner in = new Scanner(System.in);

        int nPlayers = nPlayers(in);
        int[] scores = new int[nPlayers];
        int[] games = new int[nPlayers];
        int turn = 0;
        do {
            do {
                int player = turn % nPlayers;
                printBoard(scores, player);
                int counter = 0;
                do {
                    int result = this.die.roll();
                    System.out.printf("Die result: %s.", result);
                    counter = (result != 6) ? result + counter : 0;
                } while (counter != 0 && decision(in).charAt(0) == 'D');
                if (counter > 0) {
                    scores[player] += counter;
                    if (scores[player] > this.cells) {
                        ++games[player];
                        System.out.printf("<<< You have won J%d. Congratulations! >>>", player);
                        break;
                    } else {
                        System.out.printf("You have decided to stand, you move forward %s squares.", counter);
                        ++turn;
                    }
                } else {
                    System.out.println("End of turn, no points.");
                    ++turn;
                }
            } while (scores[turn % nPlayers] < this.cells);
        } while (repeat(in).charAt(0) == 'R');
        for (int i = 0; i < games.length; ++i) {
            System.out.printf("J%d has won %d times.", i, games[i]);
        }
        System.out.println("=== End of the program ===");
    }

    private static void printBoard(int[] scores, int player) {
        String board = IntStream.range(0, scores.length)
                .mapToObj(i -> String.format("J%s-%s", i + 1, scores[i]))
                .collect(Collectors.joining(" "));
        System.out.printf("\nBoard: %s <<< It's J%s's turn. >>>%n", board, player);
    }

    private static int nPlayers(Scanner in) {
        while (true) {
            System.out.print("Enter the number of players: ");
            try {
                return Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("You must enter a valid number");
            }
        }
    }

    private static String decision(Scanner in) {
        while (true) {
            System.out.print("Enter [D] to roll, or [P] to stand: ");
            String s = in.nextLine();
            if (s.charAt(0) == 'D' || s.charAt(0) == 'P') {
                return s;
            }
        }
    }

    private static String repeat(Scanner in) {
        while (true) {
            System.out.print("Enter [R] to repeat [S] to exit: ");
            String s = in.nextLine();
            if (s.charAt(0) == 'R' || s.charAt(0) == 'S') {
                return s;
            }
        }
    }

    public static void main(String[] args) {
        new Game().play();
    }
}
