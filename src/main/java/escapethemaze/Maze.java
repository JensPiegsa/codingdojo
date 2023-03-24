package escapethemaze;

import java.util.ArrayList;
import java.util.List;

public class Maze {

    private final Pose player;
    private final Dimension dimension;
    private char[][] maze;

    public Maze(final char[][] maze) {
        this.maze = maze;
        this.dimension = new Dimension(maze[0].length, maze.length);
        player = findPlayer();
    }

    public static List<Character> escape(char[][] maze) {
        return new Maze(maze).escape();
    }

    List<Character> escape() {
        // add all valid neighbour positions to visitList (map<Position, cost>)
        // calculate cost per position
        // choose next visit with lowest cost
        // remove Position
        final List<Character> steps = new ArrayList<>();
        steps.add('F');
        return steps;
    }

    Pose findPlayer() {
        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[y].length; x++) {
                final char cell = maze[y][x];
                final Direction direction = Direction.of(cell);
                if (direction != null) {
                    return new Pose(new Position(x,y), direction);
                }
            }
        }
        return null;
    }

    public List<Position> findAllValidPositions() {
        return player.position().neighbours()
                .filter(dimension::isInBounds)
                .filter(this::isValid)
                .toList();
    }

    private boolean isValid(final Position position) {
        return maze[position.y()][position.x()] == ' ';
    }


}