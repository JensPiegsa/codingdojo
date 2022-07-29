package imagehost;

import java.util.Random;

public class Solution {

    static NameGenerator instance = new NameGenerator();

    static class NameGenerator {
        Random random;

        public NameGenerator() {
            this(new Random());
        }

        public NameGenerator(final Random random) {
            this.random = random;
        }

        public String generateName(PhotoManager photoManager) {
            return "      ";
        }
    }

    public static String generateName(PhotoManager photoManager) {
        return instance.generateName(photoManager);
    }
}