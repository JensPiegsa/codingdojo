package escapethemaze;

public enum NavigationCommand {
    FORWARD('F'), TURN_RIGHT('R');

    private final char navigationCharacter;

    NavigationCommand(final char navigationCharacter) {

        this.navigationCharacter = navigationCharacter;
    }
}
