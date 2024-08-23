package trivia.runner;
import trivia.uglytrivia.Game;

import java.util.Random;

public class GameRunner {


    public static void main(final String[] args) {

        final Random rand;
        if (args.length >= 1) {
            final int seed = Integer.parseInt(args[0]);
            rand = new Random(seed);
        } else {
            rand = new Random();
        }
        final Game aGame = new Game(rand);

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        aGame.play();
    }
}