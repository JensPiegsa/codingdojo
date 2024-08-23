package trivia.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@SuppressWarnings("UseOfSystemOutOrSystemErr")
public final class Game {

    private final Random rand;
    List<String> players = new ArrayList<>();
    int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

    LinkedList<String> popQuestions = new LinkedList<>();
    LinkedList<String> scienceQuestions = new LinkedList<>();
    LinkedList<String> sportsQuestions = new LinkedList<>();
    LinkedList<String> rockQuestions = new LinkedList<>();

    int currentPlayer;
    boolean isGettingOutOfPenaltyBox;

    public Game(final Random rand) {
        this.rand = rand;
        IntStream.range(0, 50).forEach(i -> {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast("Science Question " + i);
            sportsQuestions.addLast("Sports Question " + i);
            rockQuestions.addLast("Rock Question " + i);
        });
    }

    public void play() {

        boolean winner;
        do {

            roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                wrongAnswer();
                winner = false;
            } else {
                winner = !wasCorrectlyAnswered();
            }

        } while (!winner);

    }

    public boolean add(final String playerName) {

        players.add(playerName);
        places[howManyPlayers()] = 0;
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(final int roll) {
        System.out.println(currentPlayer() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (isInPenaltyBox()) {
            if (roll % 2 == 0) {
                System.out.println(currentPlayer() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
                return;
            } else {
                isGettingOutOfPenaltyBox = true;
                System.out.println(currentPlayer() + " is getting out of the penalty box");
            }
        }
        incrementPlace(roll);
        System.out.println(currentPlayer() + "'s new location is " + currentPlace());
        System.out.println("The category is " + currentCategory());
        askQuestion();
    }

    private void incrementPlace(final int roll) {
        places[currentPlayer] += roll;
        checkOverflow();
    }

    private int currentPlace() {
        return places[currentPlayer];
    }

    private void checkOverflow() {
        if (currentPlace() > 11) {
            places[currentPlayer] -= 12;
        }
    }

    private void askQuestion() {
        final String currentCategory = currentCategory();
        switch (currentCategory) {
            case "Pop" -> System.out.println(popQuestions.removeFirst());
            case "Science" -> System.out.println(scienceQuestions.removeFirst());
            case "Sports" -> System.out.println(sportsQuestions.removeFirst());
            case "Rock" -> System.out.println(rockQuestions.removeFirst());
            default -> throw new IllegalStateException("Unexpected value: " + currentCategory);
        }
    }


    private String currentCategory() {
        final int place = currentPlace();
        return switch (place) {
            case 0, 4, 8 -> "Pop";
            case 1, 5, 9 -> "Science";
            case 2, 6, 10 -> "Sports";
            default -> "Rock";
        };
    }

    public boolean wasCorrectlyAnswered() {
        if (!isInPenaltyBox() || isGettingOutOfPenaltyBox) {
            return correctAnswer();
        } else {
            nextPlayer();
            return true;
        }
    }

    private boolean isInPenaltyBox() {
        return inPenaltyBox[currentPlayer];
    }

    private void nextPlayer() {
        currentPlayer = (currentPlayer + 1) % players.size();
    }

    private boolean correctAnswer() {
        System.out.println("Answer was correct!!!!");
        incrementGold();
        System.out.println(currentPlayer() + " now has " + currentGold() + " Gold Coins.");

        final boolean winner = didPlayerWin();
        nextPlayer();

        return winner;
    }

    private void incrementGold() {
        purses[currentPlayer]++;
    }

    private int currentGold() {
        return purses[currentPlayer];
    }

    private String currentPlayer() {
        return players.get(currentPlayer);
    }

    public void wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer() + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;
        nextPlayer();
    }


    private boolean didPlayerWin() {
        return currentGold() != 6;
    }
}