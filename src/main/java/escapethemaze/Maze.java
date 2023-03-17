package escapethemaze;

import java.util.ArrayList;
import java.util.List;

public class Maze {
    
    public static List<Character> escape(char[][] maze) {
        final List<Character> steps = new ArrayList<>();
        steps.add('F');
        return steps;
    }

    public static Pose findPlayer(final char[][] maze) {
        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[y].length; x++) {
                final char cell = maze[y][x];
                Direction direction = Direction.of(cell);
                if (direction != null) {
                    return new Pose(new Position(x,y), direction);
                }
            }
        }
        return null;
    }
}