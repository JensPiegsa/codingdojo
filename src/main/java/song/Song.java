package song;

class Song {

    public static void main(final String[] args) {

        final StringBuilder result = new StringBuilder();
        
 /*       int count = 0;
        while (count * 2 <= args.length) {
            if (count % 2 == 0) {
                result.append("There was an old lady who swallowed a " + args[count] + "\n");
            } else {
                result.append(args[count]);
            }
            for (int i = 0; i < count; i++) {
                result.append("She swallowed the spider to catch the " + args[i*2] + "\n");
            }
            result.append("I don't know why she swallowed a " +
                    args[0] +
                    " - perhaps she'll die!\n");
            count++;
            
        }*/

        final String[] configuration = args == null ? new String[]{
                "fly", "",
                "spider", "That wriggled and wiggled and tickled inside her.",
                "bird", "How absurd to swallow a bird.",
                "cat", "Fancy that to swallow a cat!",
                "dog", "What a hog, to swallow a dog!",
                "cow", "I don't know how she swallowed a cow!"} : args;

        final String introVerse = buildIntroVerse(configuration[0]);
        final int count = 2;

        String middleVerses = "\n";
        for (int i = 2; i < configuration.length; i += 2) {
            middleVerses += buildVerse(configuration, i) + "\n";
        }
        final String song =
                introVerse +
                        middleVerses +
                        buildOutroVerse("horse", "...She's dead, of course!");
        result.append(song);

        System.out.println(result);
    }

    private static String buildVerse(final String[] configuration, final int count) {
        final StringBuilder verse = new StringBuilder();
        final String start = "There was an old lady who swallowed a " + configuration[count] + ";\n" +
                configuration[count + 1] + "\n";
        verse.append(start);
        for (int i = count; i >= 2; i -= 2) {
            final String rhyme = "She swallowed the " + configuration[i] +
                    " to catch the " + configuration[i - 2] + ((i == 2) ? ";" : ",") + "\n";
            verse.append(rhyme);
        }
        final String lastRhyme = "I don't know why she swallowed a " + configuration[0] + " - perhaps she'll die!\n";
        verse.append(lastRhyme);
        return verse.toString();
    }

    private static String buildOutroVerse(final String animal, final String rhyme) {
        return "There was an old lady who swallowed a " + animal + "...\n" +
                rhyme;
    }

    static String buildIntroVerse(final String animal) {
        return "There was an old lady who swallowed a " + animal + ".\n"
                + "I don't know why she swallowed a " + animal + " - perhaps she'll die!\n";
    }
}