package potus;

import static org.assertj.core.api.BDDAssertions.then;
import static potus.Dinglemouse.Position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * https://www.codewars.com/kata/5c230f017f74a2e1c300004f
 */
class DinglemouseTest {

    @Test @DisplayName("test")
    void test() {
        String level = """
                    ###
                    #X#
                    ###
                """;
        final boolean actual = Dinglemouse.allAlone(toChars(level));
        then(actual).isTrue();
    }

    @Test @DisplayName("test2")
    void test2() {
        String level = """
                    ####
                    #Xo#
                    ####
                """;
        final boolean actual = Dinglemouse.allAlone(toChars(level));
        then(actual).isFalse();
    }


    @Test @DisplayName("test3")
    void test3() {
        String level = """
                    #### ####
                    #X ### o#
                    #       #
                    #########
                """;
        final boolean actual = Dinglemouse.allAlone(toChars(level));
        then(actual).isFalse();
    }

    @Test @DisplayName("test find potus 1")
    void testFindPotus1() {
        String level = """
                ####
                # X#
                ####
                """;
        final Position actual = Dinglemouse.findPotus(toChars(level));
        then(actual).isEqualTo(new Position(1, 2));
    }

    @Test @DisplayName("test find potus 2")
    void testFindPotus2() {
        String level = """
                ####
                #X #
                ####
                """;
        final Dinglemouse.Position actual = Dinglemouse.findPotus(toChars(level));
        then(actual).isEqualTo(new Position(1, 1));
    }

    private char[][] toChars(final String level) {
        final String[] split = level.split("\n");
        final int rows = split.length;
        final int columns = 80;
        final char[][] chars = new char[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (column < split[row].length()) {
                    chars[row][column] = split[row].charAt(column);
                }
            }
        }
        return chars;
    }
}