package trivia.uglytrivia;

import java.util.*;
import java.util.stream.IntStream;

@SuppressWarnings("UseOfSystemOutOrSystemErr")
public final class Game {

    private final Random rand;
    List<String> players = new ArrayList<>();
    int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

    Map<String, LinkedList<String>> questions = new HashMap<>();

    int currentPlayer;

    public Game(final Random rand) {
        this.rand = rand;
        questions.put("Pop", new LinkedList<>());
        questions.put("Science", new LinkedList<>());
        questions.put("Sports", new LinkedList<>());
        questions.put("Rock", new LinkedList<>());

        IntStream.range(0, 50).forEach(i -> {
            questions.get("Pop").addLast( "Pop Question " + i);
            questions.get("Science").addLast( "Science Question " + i);
            questions.get("Sports").addLast( "Sports Question " + i);
            questions.get("Rock").addLast( "Rock Question " + i);
        });
    }

    public void play() {
        boolean winner = false;

        do {
            boolean isGettingOutOfPenaltyBox;
            int roll = rollDiceWith6Sides();

            isGettingOutOfPenaltyBox = roll % 2 == 1;
            if (isInPenaltyBox() && !isGettingOutOfPenaltyBox) {
                System.out.println(currentPlayer() + " is not getting out of the penalty box");
            }
            if (isInPenaltyBox() && isGettingOutOfPenaltyBox) {
                System.out.println(currentPlayer() + " is getting out of the penalty box");
            }
            if (!isInPenaltyBox() || isGettingOutOfPenaltyBox) {
                incrementPlace(roll);
                System.out.println(currentPlayer() + "'s new location is " + currentPlace());
                System.out.println("The category is " + currentCategory());
                askQuestion();
            }

            if (playerHasIncorrectAnswer()) {
                System.out.println("Question was incorrectly answered");
                System.out.println(currentPlayer() + " was sent to the penalty box");
                putIntoPenaltyBox(currentPlayer);
            } else if (!isInPenaltyBox() || isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                incrementGold();
                System.out.println(currentPlayer() + " now has " + currentGold() + " Gold Coins.");

                winner = !didPlayerWin();
            }
            nextPlayer();

        } while (!winner);

    }

    private boolean playerHasIncorrectAnswer() {
        return rand.nextInt(9) == 7;
    }

    public boolean add(final String playerName) {

        players.add(playerName);
        final int numberOfPlayers = players.size();
        places[numberOfPlayers] = 0;
        purses[numberOfPlayers] = 0;
        inPenaltyBox[numberOfPlayers] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + numberOfPlayers);
        return true;
    }

    private int rollDiceWith6Sides() {
        int roll = rand.nextInt(5) + 1;
        System.out.println(currentPlayer() + " is the current player");
        System.out.println("They have rolled a " + roll);
        return roll;
    }

    private void incrementPlace(final int roll) {
        places[currentPlayer] = (places[currentPlayer] + roll) % 12;
    }

    private int currentPlace() {
        return places[currentPlayer];
    }

    private void askQuestion() {
        final String currentCategory = currentCategory();
        String question = questions.get(currentCategory).removeFirst();
        System.out.println(question);
    }


    private String currentCategory() {
        final int place = currentPlace() % 4;
        return switch (place) {
            case 0 -> "Pop";
            case 1 -> "Science";
            case 2 -> "Sports";
            case 3 -> "Rock";
            default -> throw new IllegalStateException("Unexpected value: " + place);
        };
    }

    private boolean isInPenaltyBox() {
        return inPenaltyBox[currentPlayer];
    }

    private void nextPlayer() {
        currentPlayer = (currentPlayer + 1) % players.size();
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

    private void putIntoPenaltyBox(int player) {
        inPenaltyBox[player] = true;
    }


    private boolean didPlayerWin() {
        return currentGold() != 6;
    }
}