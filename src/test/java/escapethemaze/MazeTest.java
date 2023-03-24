package escapethemaze;

import static org.assertj.core.api.BDDAssertions.*;

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
        final Pose pose = new Maze(basicMazes.get(0)).findPlayer();
        final Position position = pose.position();
        final Direction direction = pose.direction();
        then(position).isEqualTo(new Position(1, 1));
        then(direction).isEqualTo(Direction.east);
    }
    
    @Test @DisplayName("find player 2.")
    void findPlayer2() {
        final Pose pose = new Maze(basicMazes.get(4)).findPlayer();
        final Position position = pose.position();
        final Direction direction = pose.direction();
        then(position).isEqualTo(new Position(4, 4));
        then(direction).isEqualTo(Direction.north);
    }
    
    @Test @DisplayName("find player 3.")
    void findPlayer3() {
        final Pose pose = new Maze(basicMazes.get(2)).findPlayer();
        final Position position = pose.position();
        final Direction direction = pose.direction();
        then(position).isEqualTo(new Position(9, 1));
        then(direction).isEqualTo(Direction.east);
    }

    @Test @DisplayName("test")
    void test() {
        final char[][] maze = basicMazes.get(0);
        final List<Character> steps = Maze.escape(maze);
        then(steps).containsExactly('F');
    }

    @Test @DisplayName("can find all neighbours positions.")
    void canFindAllNeighboursPositions() {
        final char[][] maze = basicMazes.get(0);
        final List<Position> neighbours = new Maze(maze).findAllValidPositions();
        then(neighbours).containsExactlyInAnyOrder(
            Position.of(1, 0),
            Position.of(2, 1),
            Position.of(1, 2),
            Position.of(0, 1)
        );
    }
    
    @Test @DisplayName("can find all valid neighbour positions.")
    void canFindAllValidNeighbourPositions() {
        final char[][] maze = basicMazes.get(1);
        final List<Position> neighbours = new Maze(maze).findAllValidPositions();
        then(neighbours).containsExactlyInAnyOrder(
                Position.of(2, 1));
    }
    
    @Test @DisplayName("can move forward.")
    void canMoveForward() {
        final Maze maze = new Maze(basicMazes.get(1));
        final List<Character> moves = maze.escape();
        then(moves).containsExactly('F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'R', 'F');
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
                "#>12345678#".toCharArray(),
                "#########9#".toCharArray()
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