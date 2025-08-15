package minesweepersolver;


public enum Strategy {

    /**
     *  high priority -> handle first
     */
    KNOWN_BORDER(1, "known inner border (uncovered)"),
    UNKNOWN_BORDER(1, "unknown border (covered)"),
    SATURATED(10, "neighbours can be uncovered because of this saturated field (covered)"),
    SATURATED_NEIGHBOUR(10, "field can be uncovered because of saturated neighbour (covered)"),
    ;

    private int priority;
    private String description;

    Strategy(int priority, String description) {
        this.priority = priority;
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }
    public String getDescription() {
        return description;
    }
}
