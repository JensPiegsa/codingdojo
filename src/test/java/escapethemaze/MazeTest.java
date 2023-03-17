package escapethemaze;

import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("A Maze")
class MazeTest {

    private static final List<char[][]> basicMazes = new ArrayList<>();


    @Test @DisplayName("find player 1.")
    void findPlayer1() {
        final char[][] maze = basicMazes.get(0);
        final Pose pose = Maze.findPlayer(maze);
        final Position position = pose.position();
        final Direction direction = pose.direction();
        then(position).isEqualTo(new Position(1, 1));
        then(direction).isEqualTo(Direction.east);
    }
    @Test @DisplayName("find player 2.")
    void findPlayer2() {
        final char[][] maze = basicMazes.get(4);
        final Pose pose = Maze.findPlayer(maze);
        final Position position = pose.position();
        final Direction direction = pose.direction();
        then(position).isEqualTo(new Position(4, 4));
        then(direction).isEqualTo(Direction.north);
    }

    @Test @DisplayName("test")
    void test() {
        final char[][] maze = basicMazes.get(0);
        final List<Character> steps = Maze.escape(maze);
        then(steps).containsExactly('F');
    }

    @BeforeAll
    private static void buildTests() {

        basicMazes.add(new char[][] {
                "# #".toCharArray(),
                " > ".toCharArray(),
                "# #".toCharArray()
        });
        basicMazes.add(new char[][] {
                "###########".toCharArray(),
                "#>        #".toCharArray(),
                "######### #".toCharArray()
        });
        basicMazes.add(new char[][] {
                "# #########".toCharArray(),
                "#        >#".toCharArray(),
                "###########".toCharArray()
        });
        basicMazes.add(new char[][] {
                "####### #".toCharArray(),
                "#>#   # #".toCharArray(),
                "#   #   #".toCharArray(),
                "#########".toCharArray()
        });
        basicMazes.add(new char[][] {
                "##########".toCharArray(),
                "#        #".toCharArray(),
                "#  ##### #".toCharArray(),
                "#  #   # #".toCharArray(),
                "#  #^# # #".toCharArray(),
                "#  ### # #".toCharArray(),
                "#      # #".toCharArray(),
                "######## #".toCharArray()
        });
        basicMazes.add(new char[][] {
                "#########################################".toCharArray(),
                "#<    #       #     #         # #   #   #".toCharArray(),
                "##### # ##### # ### # # ##### # # # ### #".toCharArray(),
                "# #   #   #   #   #   # #     #   #   # #".toCharArray(),
                "# # # ### # ########### # ####### # # # #".toCharArray(),
                "#   #   # # #       #   # #   #   # #   #".toCharArray(),
                "####### # # # ##### # ### # # # #########".toCharArray(),
                "#   #     # #     # #   #   # # #       #".toCharArray(),
                "# # ####### ### ### ##### ### # ####### #".toCharArray(),
                "# #             #   #     #   #   #   # #".toCharArray(),
                "# ############### ### ##### ##### # # # #".toCharArray(),
                "#               #     #   #   #   # #   #".toCharArray(),
                "##### ####### # ######### # # # ### #####".toCharArray(),
                "#   # #   #   # #         # # # #       #".toCharArray(),
                "# # # # # # ### # # ####### # # ### ### #".toCharArray(),
                "# # #   # # #     #   #     # #     #   #".toCharArray(),
                "# # ##### # # ####### # ##### ####### # #".toCharArray(),
                "# #     # # # #   # # #     # #       # #".toCharArray(),
                "# ##### ### # ### # # ##### # # ### ### #".toCharArray(),
                "#     #     #     #   #     #   #   #    ".toCharArray(),
                "#########################################".toCharArray()
        });
    }
}