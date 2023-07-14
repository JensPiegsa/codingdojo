package song;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.BDDAssertions.*;

class SongTest {

    public static final String ORIGINAL_SONG = "There was an old lady who swallowed a fly.\n" +
            "I don't know why she swallowed a fly - perhaps she'll die!\n" +
            "\n" +
            "There was an old lady who swallowed a spider;\n" +
            "That wriggled and wiggled and tickled inside her.\n" +
            "She swallowed the spider to catch the fly;\n" +
            "I don't know why she swallowed a fly - perhaps she'll die!\n" +
            "\n" +
            "There was an old lady who swallowed a bird;\n" +
            "How absurd to swallow a bird.\n" +
            "She swallowed the bird to catch the spider,\n" +
            "She swallowed the spider to catch the fly;\n" +
            "I don't know why she swallowed a fly - perhaps she'll die!\n" +
            "\n" +
            "There was an old lady who swallowed a cat;\n" +
            "Fancy that to swallow a cat!\n" +
            "She swallowed the cat to catch the bird,\n" +
            "She swallowed the bird to catch the spider,\n" +
            "She swallowed the spider to catch the fly;\n" +
            "I don't know why she swallowed a fly - perhaps she'll die!\n" +
            "\n" +
            "There was an old lady who swallowed a dog;\n" +
            "What a hog, to swallow a dog!\n" +
            "She swallowed the dog to catch the cat,\n" +
            "She swallowed the cat to catch the bird,\n" +
            "She swallowed the bird to catch the spider,\n" +
            "She swallowed the spider to catch the fly;\n" +
            "I don't know why she swallowed a fly - perhaps she'll die!\n" +
            "\n" +
            "There was an old lady who swallowed a cow;\n" +
            "I don't know how she swallowed a cow!\n" +
            "She swallowed the cow to catch the dog,\n" +
            "She swallowed the dog to catch the cat,\n" +
            "She swallowed the cat to catch the bird,\n" +
            "She swallowed the bird to catch the spider,\n" +
            "She swallowed the spider to catch the fly;\n" +
            "I don't know why she swallowed a fly - perhaps she'll die!\n" +
            "\n" +
            "There was an old lady who swallowed a horse...\n" +
            "...She's dead, of course!\n";

    @Test @DisplayName("returns default song without arguments.")
    void returnsDefaultSongWithoutArguments() {

        final String expected = ORIGINAL_SONG;

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Song.main(null);
        final String actual = out.toString(StandardCharsets.UTF_8);
        
        then(actual).isEqualToIgnoringNewLines(expected);
    }
    
    @Test @DisplayName("returns expected song for original arguments.")
    void returnsExpectedSongForOriginalArguments() {
        
        final String expected = ORIGINAL_SONG;
        final String[] arguments = {
                "fly", "",
                "spider", "That wriggled and wiggled and tickled inside her.",
                "bird", "How absurd to swallow a bird.",
                "cat", "Fancy that to swallow a cat!",
                "dog", "What a hog, to swallow a dog!",
                "cow", "I don't know how she swallowed a cow!"};

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Song.main(arguments);
        final String actual = out.toString(StandardCharsets.UTF_8);

        then(actual).isEqualToIgnoringNewLines(expected);
    }

    @Test @DisplayName("Test with wrong arg.")
    void returnsExpectedVersWithOneArgument() {
        String introVerse = Song.buildIntroVerse("mosquitto");

        then(introVerse).isEqualToIgnoringNewLines("There was an old lady who swallowed a mosquitto.\n" +
                "I don't know why she swallowed a mosquitto - perhaps she'll die!\n");

    }
    
}