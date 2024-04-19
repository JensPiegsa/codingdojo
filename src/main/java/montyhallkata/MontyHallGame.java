package montyhallkata;

import org.jetbrains.annotations.Range;

import java.util.Random;

public class MontyHallGame {

    private int selectedDoor;
    private int prizeDoor;

    public MontyHallGame() {
        prizeDoor = new Random().nextInt(3);
    }

    public void selectDoor(@Range(from=0, to=2) final int selectedDoor) {
        this.selectedDoor = selectedDoor;
    }

    public int openDoor() {
        return 0;
    }
}
