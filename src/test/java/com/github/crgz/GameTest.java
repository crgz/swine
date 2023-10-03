package com.github.crgz;

import com.github.crgz.utils.CrookedDie;
import com.github.crgz.utils.Matches;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GameTest {

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final InputStream originalIn = System.in;

    public static final String EXPECTED_OUTPUT = """
            === Start of the program ===
            Welcome to the game Pig! Enter the number of players: two
            You must enter a valid number. Enter the number of players: 2

            Board: J1-0 J2-0 <<< It's J1's turn. >>>
            Die result: 6. End of turn, no points.

            Board: J1-0 J2-0 <<< It's J2's turn. >>>
            Die result: 3. Enter [D] to roll, or [P] to stand: D
            Die result: 3. Enter [D] to roll, or [P] to stand: D
            Die result: 4. Enter [D] to roll, or [P] to stand: P
            You have decided to stand, you move forward 10 squares.

            Board: J1-0 J2-10 <<< It's J1's turn. >>>

            Die result: 3. Enter [D] to roll, or [P] to stand: D
            Die result: 5. Enter [D] to roll, or [P] to stand: D
            Die result: 4. Enter [D] to roll, or [P] to stand: D
            Die result: 1. Enter (0) to roll, or [P] to stand: D
            Die result: 4. Enter [D] to roll, or [P] to stand: P
            You have decided to stand, you advance 17 spaces.

            Board: J1-17 J2-10 <<< It's J2's turn.>>>
            Die result: 6. End of turn, no points.

            Board: J1-17 J2-10 <<< It's J1's turn. >>>
            Die result: 3. Enter [D] to roll, or [P] to stand: D
            Die result: 3. Enter [D] to roll, or [P] to Stand: D
            Die result: 6. End of turn, no points.

            Board: J1-17 J2-10 <<< It's J2's turn.>>>
            Die result: 6. End of turn, no points.

            Board: J1-17 J2-10 <<< It is J1's turn. >>>
            Die result: 3. Enter [D] to roll, or [P] to stand: P
            You have decided to stand, you advance 3 spaces.

            Board: J1-20 J2-10 <<< It's J2's turn. >>>.
            Die result: 6. End of turn, no points.

            Board: J1-20 J2-10 <<< It's J1's turn. >>>
            Die result: 5. Enter [D] to roll, or [P] to stand: D
            Die result: 1. Enter [D] to roll, or [P] to stand: D
            Die result: 3. Enter [0] to roll, or [P] to stand: pla
            Enter [D] to roll, or [P] to stand: D
            Die result: 1.
            <<< You have won J1. Congratulations! >>>

            Enter [R] to repeat [S] to exit: s
            Enter [R] to repeat,
            J1 has won 0 times.
            J2 has won 1 times.

            === End of the program ===
            """;


    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }


    @Test
    public void testSession() {
        List<String> players =  Matches.get(".* players: (.*)$", EXPECTED_OUTPUT);
        List<String> decisions =  Matches.get(".* to stand: (.*)$", EXPECTED_OUTPUT);
        List<String> repeat =  Matches.get(".* to exit: (.*)$", EXPECTED_OUTPUT);
        String text = Stream.concat(players.stream(),Stream.concat(decisions.stream(),repeat.stream()))
                .collect(Collectors.joining("\n"));
        System.setIn(new ByteArrayInputStream(text.getBytes()));
        new Game(30, new CrookedDie()).play();
        assertEquals(EXPECTED_OUTPUT, outContent.toString());
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
}
