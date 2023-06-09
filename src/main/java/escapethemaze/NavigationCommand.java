package escapethemaze;

public enum  NavigationCommand {
    FORWARD('F'), TURN_RIGHT('R'), TURN_BACK('B'), TURN_LEFT('L');

    private final char navigationCharacter;

    NavigationCommand(final char navigationCharacter) {

        this.navigationCharacter = navigationCharacter;
    }
}
