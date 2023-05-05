package escapethemaze;

public record MazeTravelCosts(
        Costs costs,
        Position startPosition,
        Position endPosition) {

    public int costsAt(final Position position) {
        return costs.getValue(position);
    }
}
