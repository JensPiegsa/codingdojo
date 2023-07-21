package xogame;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import static org.assertj.core.api.BDDAssertions.then;

/**
 * https://kata-log.rocks/tic-tac-toe-kata
 *
 * 🟪❌⭕
 *
 * Rules
 * In random order
 *
 * [ ] a game is over when all fields in a row are taken by a player
 * [x] players take turns taking fields until the game is over
 * [ ] a game is over when all fields in a diagonal are taken by a player
 * [ ] a game is over when all fields are taken
 * [x] there are two players in the game (X and O)
 * [x] a game has nine fields in a 3x3 grid
 * [ ] a game is over when all fields in a column are taken by a player
 * [ ] a player can take a field if not already taken
 */

public class XOGameTest {

    @Disabled
    @Test @DisplayName("xo game has a 3x3 field.")
    void xoGameHasA3X3Field() {

    }

    @Test @DisplayName("first player can add an x.")
    void firstPlayerCanAddAnX() {
        
        XoGame xoGame = new XoGame();

        xoGame.playerPlace(XoPosition.upperLeft);

        then(xoGame).hasToString("""
                ❌🟪🟪
                🟪🟪🟪
                🟪🟪🟪
                """);
    }
    
    @Test @DisplayName("second player can add an O.")
    void secondPlayerCanAddAnO() {
        
        XoGame xoGame = new XoGame();

        xoGame.playerPlace(XoPosition.upperLeft);
        xoGame.playerPlace(XoPosition.upperMiddle);

        then(xoGame).hasToString("""
                ❌⭕🟪
                🟪🟪🟪
                🟪🟪🟪
                """);
    }

}
