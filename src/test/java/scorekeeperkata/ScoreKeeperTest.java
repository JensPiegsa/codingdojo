package scorekeeperkata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

class ScoreKeeperTest {

    // https://kata-log.rocks/score-keeper-kata

    /*
    Problem

We need software to deliver the proper data to the scoreboard for a basketball team. Unfortunately the people using our software are not the brightest lights under the sun, so they need six buttons (each team can score either 1, 2 or 3 points with a single shot).
Your Task

Write a class ScoreKeeper which offers following methods:

void scoreTeamA1()
void scoreTeamA2()
void scoreTeamA3()
void scoreTeamB1()
void scoreTeamB2()
void scoreTeamB3()
String getScore()

     */

    @Test @DisplayName("score keeper can get score.")
    void scoreKeeperCanGetScore() {
        ScoreKeeper scoreKeeper = new ScoreKeeper();

        String score = scoreKeeper.getScore();

//        Pattern pattern = Pattern.compile("[0-9]{3}:[0-9]{3}");
//        then(pattern.matcher(score).find()).isTrue();
        then(score).matches("[0-9]{3}:[0-9]{3}");
    }

    @ParameterizedTest
    @DisplayName("add a point for team A")
    @MethodSource("scoreInput")
    void addPointForTeamA(Consumer<ScoreKeeper> function, String expected) {
        ScoreKeeper scoreKeeper = new ScoreKeeper();

        function.accept(scoreKeeper);

        then(scoreKeeper.getScore()).isEqualTo(expected);
    }

    static Stream<Arguments> scoreInput() {
        return Stream.of(
                Arguments.of((Consumer<ScoreKeeper>) ScoreKeeper::scoreTeamA1, "001:000"),
                Arguments.of((Consumer<ScoreKeeper>) ScoreKeeper::scoreTeamA2, "002:000"),
                Arguments.of((Consumer<ScoreKeeper>) ScoreKeeper::scoreTeamA3, "003:000"),
                Arguments.of((Consumer<ScoreKeeper>) ScoreKeeper::scoreTeamB1, "000:001"),
                Arguments.of((Consumer<ScoreKeeper>) ScoreKeeper::scoreTeamB2, "000:002"),
                Arguments.of((Consumer<ScoreKeeper>) ScoreKeeper::scoreTeamB3, "000:003")
        );
    }
}