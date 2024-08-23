package trivia.runner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameRunnerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void run() {
        GameRunner.main(new String[] {"1"});
        assertThat(outContent.toString()).isEqualToIgnoringNewLines("""
                Chet was added
                They are player number 1
                Pat was added
                They are player number 2
                Sue was added
                They are player number 3
                Chet is the current player
                They have rolled a 1
                Chet's new location is 1
                The category is Science
                Science Question 0
                Answer was correct!!!!
                Chet now has 1 Gold Coins.
                Pat is the current player
                They have rolled a 3
                Pat's new location is 3
                The category is Rock
                Rock Question 0
                Answer was correct!!!!
                Pat now has 1 Gold Coins.
                Sue is the current player
                They have rolled a 5
                Sue's new location is 5
                The category is Science
                Science Question 1
                Answer was correct!!!!
                Sue now has 1 Gold Coins.
                Chet is the current player
                They have rolled a 5
                Chet's new location is 6
                The category is Sports
                Sports Question 0
                Answer was correct!!!!
                Chet now has 2 Gold Coins.
                Pat is the current player
                They have rolled a 4
                Pat's new location is 7
                The category is Rock
                Rock Question 1
                Answer was correct!!!!
                Pat now has 2 Gold Coins.
                Sue is the current player
                They have rolled a 5
                Sue's new location is 10
                The category is Sports
                Sports Question 1
                Question was incorrectly answered
                Sue was sent to the penalty box
                Chet is the current player
                They have rolled a 3
                Chet's new location is 9
                The category is Science
                Science Question 2
                Answer was correct!!!!
                Chet now has 3 Gold Coins.
                Pat is the current player
                They have rolled a 3
                Pat's new location is 10
                The category is Sports
                Sports Question 2
                Question was incorrectly answered
                Pat was sent to the penalty box
                Sue is the current player
                They have rolled a 3
                Sue is getting out of the penalty box
                Sue's new location is 1
                The category is Science
                Science Question 3
                Answer was correct!!!!
                Sue now has 2 Gold Coins.
                Chet is the current player
                They have rolled a 2
                Chet's new location is 11
                The category is Rock
                Rock Question 2
                Answer was correct!!!!
                Chet now has 4 Gold Coins.
                Pat is the current player
                They have rolled a 2
                Pat is not getting out of the penalty box
                Sue is the current player
                They have rolled a 1
                Sue is getting out of the penalty box
                Sue's new location is 2
                The category is Sports
                Sports Question 3
                Answer was correct!!!!
                Sue now has 3 Gold Coins.
                Chet is the current player
                They have rolled a 5
                Chet's new location is 4
                The category is Pop
                Pop Question 0
                Answer was correct!!!!
                Chet now has 5 Gold Coins.
                Pat is the current player
                They have rolled a 4
                Pat is not getting out of the penalty box
                Sue is the current player
                They have rolled a 3
                Sue is getting out of the penalty box
                Sue's new location is 5
                The category is Science
                Science Question 4
                Answer was correct!!!!
                Sue now has 4 Gold Coins.
                Chet is the current player
                They have rolled a 1
                Chet's new location is 5
                The category is Science
                Science Question 5
                Answer was correct!!!!
                Chet now has 6 Gold Coins.
                """);
    }
}