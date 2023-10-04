<img src="https://upload.wikimedia.org/wikipedia/commons/b/b8/Pass_the_pigs_dice.jpg" >

# Swine Game Implementation

This is a simple implementation of the classic [Pig (dice game)](https://en.wikipedia.org/wiki/Pig_(dice_game)).

## Build

To build the game, simply run the following command:

```bash
make
```

## Run

You can run the game using the following command:

```bash
~/Public/jdk-21/bin/java -jar ./target/game-pig-1.0-SNAPSHOT.jar
```

## Limitations

- The current implementation has been tested on Linux.
- Contributions to port the build artifacts to other Operating Systems are welcome.

Feel free to contribute and enhance this Swine Game implementation! Enjoy playing!

## Sample Session

```
=== Start of the program ===
Welcome to the game Pig! Enter the number of players: two
You must enter a valid number. Enter the number of players: 2

Board: J1-0 J2-0 <<< It's J1's turn. >>>
Die result: 6. End of turn, no points.

Board: J1-0 J2-0 <<< It's J2's turn. >>>
Die result: 3. Enter [D] to roll, or [P] to stand: D
Die result: 3. Enter [D] to roll, or [P] to stand: D
Die result: 4. Enter [D] to roll, or [P] to stand: P
You have decided to stand, you advanced 10 positions.

Board: J1-0 J2-10 <<< It's J1's turn. >>>
Die result: 3. Enter [D] to roll, or [P] to stand: D
Die result: 5. Enter [D] to roll, or [P] to stand: D
Die result: 4. Enter [D] to roll, or [P] to stand: D
Die result: 1. Enter [D] to roll, or [P] to stand: D
Die result: 4. Enter [D] to roll, or [P] to stand: P
You have decided to stand, you advanced 17 positions.

Board: J1-17 J2-10 <<< It's J2's turn. >>>
Die result: 6. End of turn, no points.

Board: J1-17 J2-10 <<< It's J1's turn. >>>
Die result: 3. Enter [D] to roll, or [P] to stand: D
Die result: 3. Enter [D] to roll, or [P] to stand: D
Die result: 6. End of turn, no points.

Board: J1-17 J2-10 <<< It's J2's turn. >>>
Die result: 6. End of turn, no points.

Board: J1-17 J2-10 <<< It's J1's turn. >>>
Die result: 3. Enter [D] to roll, or [P] to stand: P
You have decided to stand, you advanced 3 positions.

Board: J1-20 J2-10 <<< It's J2's turn. >>>
Die result: 6. End of turn, no points.

Board: J1-20 J2-10 <<< It's J1's turn. >>>
Die result: 5. Enter [D] to roll, or [P] to stand: D
Die result: 1. Enter [D] to roll, or [P] to stand: D
Die result: 3. Enter [D] to roll, or [P] to stand: pla
Enter [D] to roll, or [P] to stand: D
Die result: 1.
<<< You have won J1. Congratulations! >>>

Enter [R] to repeat [S] to exit: S
J1 has won 1 times.
J2 has won 0 times.

=== End of the program ===
```
