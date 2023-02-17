package stripcommands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StripCommentsTest {


    @Test
    @DisplayName("contains no comment")
    void containsNoComment() {

        // Arrange
        String code = "Einfach irgendein Text mit einem Leerzeichen dran   ";

        // Act
        String result = StripComments.stripComments(code, new String[]{"!"});

        // Assert
        assertThat(result).isEqualTo("Einfach irgendein Text mit einem Leerzeichen dran");
    }
    
    @Test
    @DisplayName("strips comment from single line")
    void stripsCommentFromSingleLine() {

        // Arrange
        String code = "Einfach irgendein Text mit einem Leerzeichen dran !Kommentartext oder sowas  ";

        // Act
        String result = StripComments.stripComments(code, new String[]{"!"});
    
        // Assert
        assertThat(result).isEqualTo("Einfach irgendein Text mit einem Leerzeichen dran");
    }

    @Test
    @DisplayName("strips comment from single line with another symbol")
    void stripsCommentFromSingleWithAnotherSymbol() {

        // Arrange
        String code = "Einfach irgendein Text mit einem Leerzeichen dran #Kommentartext oder sowas  ";

        // Act
        String result = StripComments.stripComments(code, new String[]{"#"});

        // Assert
        assertThat(result).isEqualTo("Einfach irgendein Text mit einem Leerzeichen dran");
    }

    @Test
    @DisplayName("strips comment from multiline")
    void stripsCommentFromMultiline() {

        // Arrange
        String code = "Einfach irgendein Text mit einem # Kommentartext oder sowas \n" +
                "Leerzeichen dran   ";

        // Act
        String result = StripComments.stripComments(code, new String[]{"#"});

        // Assert
        assertThat(result).isEqualTo("Einfach irgendein Text mit einem\nLeerzeichen dran");
    }

    @Test
    @DisplayName("comment symbol with regex symbol")
    void commentSymbolWithRegexSymbol() {

        // Arrange
        String code = "Einfach irgendein Text mit einem ] Kommentartext oder sowas \n" +
                "Leerzeichen dran   ";

        // Act
        String result = StripComments.stripComments(code, new String[]{"]"});

        // Assert
        assertThat(result).isEqualTo("Einfach irgendein Text mit einem\nLeerzeichen dran");
    }




    @Test
    public void stripComments() {
        assertEquals(
                "apples, pears\ngrapes\nbananas",
                StripComments.stripComments( "apples, pears # and bananas\ngrapes\nbananas !apples", new String[] { "#", "!" } )
        );

        assertEquals(
                "a\nc\nd",
                StripComments.stripComments( "a #b\nc\nd $e f g", new String[] { "#", "$" } )
        );

    }
    
    @Test @DisplayName("bug")
    void bug() {
        assertThat(StripComments.stripComments("one/comment", new String[]{"//"})).isEqualTo("one/comment");
    }
}