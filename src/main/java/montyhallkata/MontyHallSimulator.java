package montyhallkata;

public class MontyHallSimulator {

    private final RandomNumberGenerator randomNumberGeneratorPrizeDoor;
    private final RandomNumberGenerator randomNumberGeneratorPlayerDoor;
    private final RandomNumberGenerator randomNumberGeneratorGameMaster;

    public MontyHallSimulator(final RandomNumberGenerator randomNumberGeneratorPrizeDoor,
                              RandomNumberGenerator randomNumberGeneratorPlayerDoor,
                              RandomNumberGenerator randomNumberGeneratorGameMaster) {
        this.randomNumberGeneratorPrizeDoor = randomNumberGeneratorPrizeDoor;
        this.randomNumberGeneratorPlayerDoor = randomNumberGeneratorPlayerDoor;
        this.randomNumberGeneratorGameMaster = randomNumberGeneratorGameMaster;
    }

    public SimulationResult run(int rounds, boolean playerKeepsDoor) {
       int wins = 0;

        for (int i = 0; i < rounds; i++) {
            final MontyHallGame game = new MontyHallGame(randomNumberGeneratorPrizeDoor, randomNumberGeneratorGameMaster);

            game.playersFirstSelection(randomNumberGeneratorPlayerDoor.nextInt(3));
            game.gameMasterOpensDoor();

            boolean result;
            if (playerKeepsDoor) {
                result = game.playerKeepsSelection();
            } else {
                result = game.playerSwitchesSelection();
            }

            if (result) wins++;
        }

        return new SimulationResult(wins);
    }
}
