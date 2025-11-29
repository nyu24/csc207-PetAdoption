package interface_adapter.save_game;

import entities.Pet;
import use_case.save_game.SaveGameInputBoundary;
import use_case.save_game.SaveGameInputData;

public class SaveGameController {

    private final SaveGameInputBoundary saveGameUseCaseInteractor;

    public SaveGameController(SaveGameInputBoundary saveGameUseCaseInteractor) {
        this.saveGameUseCaseInteractor = saveGameUseCaseInteractor;
    }

    public void execute(int timeLeft, int currScore, Pet currPet) {
        final SaveGameInputData saveGameInputData = new SaveGameInputData(timeLeft, currScore, currPet);

        saveGameUseCaseInteractor.execute(saveGameInputData);
    }

    public void switchToPetRoomView() { saveGameUseCaseInteractor.switchToPetRoomView(); }


}
