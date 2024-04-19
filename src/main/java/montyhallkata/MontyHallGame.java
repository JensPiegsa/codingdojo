package montyhallkata;

import org.jetbrains.annotations.Range;

public class MontyHallGame {

    private final RandomNumberGenerator randomNumberGenerator;
    private int selectedDoor;
    private int prizeDoor;

    public MontyHallGame(final RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
        prizeDoor = this.randomNumberGenerator.nextInt(3);
    }

    public void playersFirstSelection(@Range(from = 0, to = 2) final int selectedDoor) {
        this.selectedDoor = selectedDoor;
    }

    public int gameMasterOpensDoor() {
        return 0;
    }

    public boolean playerKeepsSelection() {
        return false;
    }

    public boolean playerSwitchesSelection() {
        return false;
    }
}
