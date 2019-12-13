package christmastree;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ChristmasTreeTest {

    @Test
    @DisplayName("tree with n=3")
    public void test_n3() {

        // Arrange
        String expected =
                "  *\n" +
                " @ o\n" +
                "* @ o\n" +
                "  |";

        // Act
        ChristmasTree christmasTree = new ChristmasTree("*@o", 3);
        String tree = christmasTree.draw();

        // Assert
        assertEquals(expected, tree);
    }

    @Test
    @DisplayName("tree with n=6")
    public void test_n6() {

        // Arrange
        String expected =
                "     *\n" +
                "    @ o\n" +
                "   * @ o\n" +
                "  * @ o *\n" +
                " @ o * @ o\n" +
                "* @ o * @ o\n" +
                "     |\n" +
                "     |";

        // Act
        ChristmasTree christmasTree = new ChristmasTree("*@o", 6);
        String tree = christmasTree.draw();

        // Assert
        assertEquals(expected, tree);
    }

    @Test
    @DisplayName("tree with n=12")
    public void test_n12() {

        // Arrange
        String expected =
                "           *\n" +
                "          @ o\n" +
                "         * @ o\n" +
                "        * @ o *\n" +
                "       @ o * @ o\n" +
                "      * @ o * @ o\n" +
                "     * @ o * @ o *\n" +
                "    @ o * @ o * @ o\n" +
                "   * @ o * @ o * @ o\n" +
                "  * @ o * @ o * @ o *\n" +
                " @ o * @ o * @ o * @ o\n" +
                "* @ o * @ o * @ o * @ o\n" +
                "           |\n" +
                "           |\n" +
                "           |\n" +
                "           |";

        // Act
        ChristmasTree christmasTree = new ChristmasTree("*@o", 12);
        String tree = christmasTree.draw();

        // Assert
        assertEquals(expected, tree);
    }

    @Test
    @DisplayName("trunk length by n=6")
    public void test_trunkLengthN6() {

        // Act
        ChristmasTree christmasTree = new ChristmasTree("*@o", 6);
        String tree = christmasTree.draw();

        // Assert
        assertThat(tree).endsWith("     |\n     |");
    }

    @Test
    @DisplayName("trunk length by n=3")
    public void test_trunkLengthN3() {

        // Act
        ChristmasTree christmasTree = new ChristmasTree("*@o", 3);
        String tree = christmasTree.draw();

        // Assert
        assertThat(tree).endsWith("  |");
    }

}