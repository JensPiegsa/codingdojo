package scorekeeperkata;

public class ScoreKeeper {
    private int scoreA;
    private int scoreB;

    public String getScore() {
        return fillWithZeros(scoreA) + ":" + fillWithZeros(scoreB);
    }

    private String fillWithZeros(int score) {
        return String.format("%03d", score);
    }

    public void scoreTeamA1() {
        scoreA++;
    }

    public void scoreTeamA2() {
        scoreA += 2;
    }

    public void scoreTeamA3() {
        scoreA += 3;
    }

    public void scoreTeamB1() {
        scoreB++;
    }

    public void scoreTeamB2() {
        scoreB += 2;
    }

    public void scoreTeamB3() {
        scoreB += 3;
    }
}
