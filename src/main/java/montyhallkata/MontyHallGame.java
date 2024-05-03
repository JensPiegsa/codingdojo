package montyhallkata;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Range;

public class MontyHallGame {

    private final RandomNumberGenerator randomNumberGeneratorPrizeDoor;
    private final RandomNumberGenerator randomNumberGeneratorGameMaster;
    private int selectedDoor;
    private int prizeDoor;
    private int openDoor;

    public MontyHallGame(final RandomNumberGenerator randomNumberGeneratorPrizeDoor, RandomNumberGenerator randomNumberGeneratorGameMaster) {
        this.randomNumberGeneratorPrizeDoor = randomNumberGeneratorPrizeDoor;
        this.randomNumberGeneratorGameMaster = randomNumberGeneratorGameMaster;
        prizeDoor = this.randomNumberGeneratorPrizeDoor.nextInt(3);
    }

    public void playersFirstSelection(@Range(from = 0, to = 2) final int selectedDoor) {
        this.selectedDoor = selectedDoor;
    }

    public int gameMasterOpensDoor() {
        final List<Integer> doors = new ArrayList<>(List.of(0, 1, 2));
        doors.removeAll(List.of(selectedDoor, prizeDoor));
            int option = randomNumberGeneratorGameMaster.nextInt(2);
        if (selectedDoor != prizeDoor) {
            openDoor = doors.getFirst();
        } else {
            openDoor = doors.get(option);
        }
        return openDoor;
    }

    public boolean playerKeepsSelection() {
        return selectedDoor == prizeDoor;
    }

    public boolean playerSwitchesSelection() {
        final List<Integer> doors = new ArrayList<>(List.of(0, 1, 2));
        doors.removeAll(List.of(selectedDoor, openDoor));
        selectedDoor = doors.getFirst();
        
        return selectedDoor == prizeDoor;
    }
}
