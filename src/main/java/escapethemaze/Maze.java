package escapethemaze;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
        for (int y = 0; y < dimension.height(); y++) {
            for (int x = 0; x < dimension.width(); x++) {
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
        return player.position().neighboursStream()
                .filter(dimension::isInBounds)
                .filter(this::isValid)
                .toList();
    }

    @SuppressWarnings("ImplicitNumericConversion")
    private boolean isValid(final Position position) {
        return maze[position.y()][position.x()] == ' ';
    }


    public MazeTravelCosts calculateCosts() {

        final Position start = player.position();

        final Costs costs = new Costs(dimension);
        initializeMaximalCostsForWalls(costs);

        costs.setValue(start, 0);

        final Queue<Position> visits = new LinkedList<>();
        visits.offer(start);
        
        Position end = null;
        
        while (!visits.isEmpty() && end == null) {
            final Position current = visits.poll();
            final int currentCosts = costs.getValue(current);
            if (dimension.isAtBorder(current)) {
                end = current;
            }

            final List<Position> neighbours = current.neighbours();
            final List<Position> nextPositions = neighbours.stream()
                    .filter(dimension::isInBounds)
                    .filter(costs::isEmpty)
                    .toList();
            nextPositions.forEach(position -> costs.setValue(position, currentCosts + 1));
            visits.addAll(nextPositions);
        }


        return new MazeTravelCosts(costs, start, end);
    }

    private void initializeMaximalCostsForWalls(final Costs costs) {
        for (int y = 0; y < dimension.height(); y++) {
            for (int x = 0; x < dimension.width(); x++) {
                final Position position = Position.of(x, y);
                if (isWall(position)) {
                    costs.setMaximumValue(position);
                }
            }
        }
    }

    @SuppressWarnings("ImplicitNumericConversion")
    private boolean isWall(final Position position) {
        return getValue(position) == '#';
    }

    private boolean isFree(final Position position) {
        return !isWall(position);
    }
    

    private char getValue(final Position position) {
        return maze[position.y()][position.x()];
    }

    public MazePath calculatePath() {
        final MazePath mazePath = new MazePath();
        final MazeTravelCosts mazeTravelCosts = calculateCosts();
        final Position startPosition = mazeTravelCosts.startPosition();
        final Position endPosition = mazeTravelCosts.endPosition();
        mazePath.add(endPosition);
        Position nextPosition = endPosition;
        try {
            while (!nextPosition.equals(startPosition)) {
    
                final var p = nextPosition;
                nextPosition = nextPosition.neighboursStream()
                        .filter(dimension::isInBounds)
                        .filter(neighbour -> mazeTravelCosts.costsAt(p) - 1 == mazeTravelCosts.costsAt(neighbour))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("No exit"));
                mazePath.add(nextPosition);
            }
        } catch (final IllegalStateException e) {
            return new MazePath();
        }

        return mazePath;
    }
}