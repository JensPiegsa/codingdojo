package escapethemaze.submit;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

public class Maze {

    private final Pose player;
    private final Dimension dimension;
    private final char[][] maze;

    public Maze(final char[][] maze) {
        this.maze = maze;
        dimension = new Dimension(maze[0].length, maze.length);
        player = findPlayer();
    }

    public static List<Character> escape(final char[][] maze) {
        return new Maze(maze).escape();
    }

    List<Character> escape() {
        final MazeTravelCosts costs = calculateCosts();
        final MazePath path = calculatePath(costs);
        final NavigationCommands commands = calculateNavigationCommands(path);

        return commands.chars();
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

    public MazePath calculatePath(final MazeTravelCosts costs) {
        final MazePath mazePath = new MazePath();
        final Position startPosition = costs.startPosition();
        final Position endPosition = costs.endPosition();
        mazePath.add(endPosition);
        Position nextPosition = endPosition;
        try {
            while (!nextPosition.equals(startPosition)) {
    
                final var p = nextPosition;
                nextPosition = nextPosition.neighboursStream()
                        .filter(dimension::isInBounds)
                        .filter(neighbour -> costs.costsAt(p) - 1 == costs.costsAt(neighbour))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("No exit"));
                mazePath.add(nextPosition);
            }
        } catch (final IllegalStateException e) {
            return new MazePath();
        }

        return mazePath;
    }

    NavigationCommands calculateNavigationCommands(final MazePath path) {
        final NavigationCommands navigationCommands = new NavigationCommands();
        final MazePath reversed = path.reversed();
        Direction lastDirection = player.direction();
        Position lastPosition = player.position();

        for (int index = 1; index < reversed.length(); index++) {
            final Position pathPosition = reversed.get(index);
            final Direction requiredDirection = lastPosition.directionTowards(pathPosition);

            final NavigationCommand turnCommand = lastDirection.turnTowards(requiredDirection);
            if (turnCommand != null) {
                navigationCommands.add(turnCommand);
            }
            navigationCommands.add(NavigationCommand.FORWARD);

            lastPosition = pathPosition;
            lastDirection = requiredDirection;
        }
        return navigationCommands;
    }
}

record Costs(int[][] values, Dimension dimension) {

    public Costs(final Dimension dimension) {
        this(initializeRows(dimension), dimension);
    }

    public boolean isEmpty(final Position position) {
        return getValue(position) == -1;
    }

    public int getValue(final Position position) {
        return values[position.y()][position.x()];
    }

    public void setValue(final Position position, final int value) {
        values[position.y()][position.x()] = value;
    }

    private static int[][] initializeRows(final Dimension dimension) {
        return fill(new int[dimension.height()][dimension.width()]);
    }

    private static int[][] fill(final int[][] values) {
        for (final int[] row : values) {
            Arrays.fill(row, -1);
        }
        return values;
    }

    public void setMaximumValue(final Position position) {
        setValue(position, Integer.MAX_VALUE);
    }

    @Override
    public String toString() {
        final var out = new StringBuilder();
        for (int y = 0; y < dimension.height(); y++) {
            for (int x = 0; x < dimension.width(); x++) {
                final Position position = Position.of(x, y);
                out.append(getValue(position));
                out.append(' ');
            }
            out.append('\n');
        }
        return out.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Costs costs = (Costs) o;
        return Arrays.deepEquals(values, costs.values);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(values);
    }
}

record Dimension(int width, int height) {

    public boolean isInBounds(final Position position) {
        return width > 0 && height > 0
                && position.x() >= 0 && position.x() < width
                && position.y() >= 0 && position.y() < height;
    }

    public boolean isAtBorder(final Position position) {
        return position.x() == 0
                || position.x() == width - 1
                || position.y() == 0
                || position.y() == height - 1;
    }
}

enum Direction {
    east('>'),  // 0
    south('v'), // 1
    west('<'),  // 2
    north('^'); // 3

    private final char character;

    Direction(final char character) {
        this.character = character;
    }

    public static Direction of(final char character) {
        for (final Direction direction : values()) {
            if (direction.character == character) {
                return direction;
            }
        }
        return null;
    }

    @SuppressWarnings("ReturnOfNull")
    public static Direction fromDelta(final int deltaX, final int deltaY) {
        if (deltaX < 0 && deltaY == 0) {
            return west;
        }
        if (deltaX > 0 && deltaY == 0) {
            return east;
        }
        if (deltaX == 0 && deltaY < 0) {
            return north;
        }
        if (deltaX == 0 && deltaY > 0) {
            return south;
        }
        return null;
    }

    public NavigationCommand turnTowards(final Direction requiredDirection) {
        if (requiredDirection == this) {
            return null;
        }
        final int directionDelta = (values().length + requiredDirection.ordinal() - ordinal()) % values().length;
        return NavigationCommand.values()[directionDelta];
    }
}

class MazePath {

    private final List<Position> positions;

    @SuppressWarnings("unused")
    public static MazePath fromString(final String s) {
        if ("_".equals(s)) {
            return new MazePath();
        }

        final String[] split = s.split(" ");

        final List<Position> p = Arrays.stream(split)
                .filter(StringUtils::isNotBlank)
                .map(Position::fromString)
                .toList();

        return new MazePath(p);
    }

    public MazePath() {
        this(emptyList());
    }

    public MazePath(final List<Position> positions) {
        this.positions = new ArrayList<>(positions);
    }

    public MazePath add(final Position position) {
        positions.add(position);
        return this;
    }

    public Position get(int index) {
        return positions.get(index);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final MazePath mazePath = (MazePath) o;
        return Objects.equals(positions, mazePath.positions);
    }

    @Override
    @SuppressWarnings("ObjectInstantiationInEqualsHashCode")
    public int hashCode() {
        return Objects.hash(positions);
    }

    public MazePath reversed() {
        final var reversed = new ArrayList<>(positions);
        Collections.reverse(reversed);
        return new MazePath(reversed);
    }

    public int length() {
        return positions.size();
    }
}

record MazeTravelCosts(
        Costs costs,
        Position startPosition,
        Position endPosition) {

    public int costsAt(final Position position) {
        return costs.getValue(position);
    }
}

enum NavigationCommand {
    FORWARD('F'), TURN_RIGHT('R'), TURN_BACK('B'), TURN_LEFT('L');

    private final char navigationCharacter;

    NavigationCommand(final char navigationCharacter) {

        this.navigationCharacter = navigationCharacter;
    }

    public char getCharacter() {
        return navigationCharacter;
    }
}

class NavigationCommands {

    private final List<NavigationCommand> commands = new ArrayList<>();

    public NavigationCommands add(final NavigationCommand navigationCommand) {
        commands.add(navigationCommand);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NavigationCommands that = (NavigationCommands) o;
        return Objects.equals(commands, that.commands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commands);
    }

    @Override
    public String toString() {
        return "NavigationCommands{" +
                "commands=" + commands +
                '}';
    }

    public List<Character> chars() {
        return commands.stream()
                .map(NavigationCommand::getCharacter)
                .toList();
    }
}

record Pose(Position position, Direction direction) {}

record Position(int x, int y) {
    public static Position of(final int x, final int y) {
        return new Position(x, y);
    }

    public Stream<Position> neighboursStream() {
        return Stream.of(
                of(x + 1, y),
                of(x - 1, y),
                of(x, y + 1),
                of(x, y - 1));
    }

    public List<Position> neighbours() {
        return neighboursStream().toList();
    }

    public static Position fromString(final String s) {
        final String[] split = s.split(",");
        return of(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    public Direction directionTowards(final Position other) {
        return Direction.fromDelta(other.x - x, other.y - y);
    }
}
