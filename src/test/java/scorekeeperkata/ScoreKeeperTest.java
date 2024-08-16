package scorekeeperkata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

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

        Pattern pattern = Pattern.compile("[0-9]{3}:[0-9]{3}");
        then(pattern.matcher(score).find()).isTrue();
    }

}