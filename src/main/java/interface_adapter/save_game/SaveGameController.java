package interface_adapter.save_game;

import entities.Pet;
import use_case.save_game.SaveGameInputBoundary;
import use_case.save_game.SaveGameInputData;

public class SaveGameController {

    private final SaveGameInputBoundary saveGameUseCaseInteractor;

    public SaveGameController(SaveGameInputBoundary saveGameUseCaseInteractor) {
        this.saveGameUseCaseInteractor = saveGameUseCaseInteractor;
    }

    /**
     * Executes the Save Game Use Case.
     * @param timeLeft how much time is left in the current game.
     * @param currScore how much score in the current game.
     * @param currPet what pet entity in the cureent game.
     */
    public void execute(int timeLeft, int currScore, Pet currPet) {
        final SaveGameInputData saveGameInputData = new SaveGameInputData(timeLeft, currScore, currPet);

        saveGameUseCaseInteractor.execute(saveGameInputData);
    }

    /**
     * Executes the switch to Pet Room Use Case.
     */
    public void switchToPetRoomView() {
        saveGameUseCaseInteractor.switchToPetRoomView();
    }

}
