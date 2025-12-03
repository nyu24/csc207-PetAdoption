package interface_adapter.PetRoom;

import interface_adapter.ViewManagerModel;
import interface_adapter.save_game.SaveGameState;
import interface_adapter.save_game.SaveGameViewModel;
import use_case.PetRoom.PetRoomOutputBoundary;
import use_case.PetRoom.PetRoomOutputData;

public class PetRoomPresenter implements PetRoomOutputBoundary {
    private final PetRoomViewModel petRoomViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SaveGameViewModel saveGameViewModel;
    private PetRoomState petRoomState;

    public PetRoomPresenter(PetRoomViewModel petRoomViewModel, ViewManagerModel viewManagerModel, SaveGameViewModel saveGameViewModel) {
        this.petRoomViewModel = petRoomViewModel;
        this.viewManagerModel = viewManagerModel;
        this.saveGameViewModel = saveGameViewModel;
    }

    @Override
    public void updateValues(PetRoomOutputData petRoomOutputData) {
        petRoomState = new PetRoomState();
        petRoomState.setFood(petRoomOutputData.getFinalFood());
        petRoomState.setWater(petRoomOutputData.getFinalWater());
        petRoomState.setCleanliness(petRoomOutputData.getFinalCleanliness());
        petRoomState.setHappiness(petRoomOutputData.getFinalHappiness());
        petRoomState.setScore(petRoomOutputData.getScore());
        petRoomState.setPetType(petRoomOutputData.getPetType());
        petRoomState.setRoomType(petRoomOutputData.getRoomType());
        petRoomState.setCurrPet(petRoomOutputData.getCurrPet());
        petRoomViewModel.setState(petRoomState);
        petRoomViewModel.firePropertyChange("value_update");
    }

    @Override
    public void prepareFailView(String message) {
        System.out.println("Pet Room Error:" + message);
    }

    @Override
    public void switchToSaveGameView() {
        petRoomViewModel.firePropertyChange("timer_stop");
        final SaveGameState saveGameState = new SaveGameState();
        saveGameState.setTimeLeft(petRoomViewModel.getState().getTimer());
        saveGameState.setCurrScore(petRoomViewModel.getState().getScore());
        saveGameState.setCurrPet(petRoomViewModel.getState().getCurrPet());
        saveGameState.getCurrPet().setHunger(petRoomViewModel.getState().getFood());
        saveGameState.getCurrPet().setThirst(petRoomViewModel.getState().getWater());
        saveGameState.getCurrPet().setCleanliness(petRoomViewModel.getState().getCleanliness());
        saveGameState.getCurrPet().setHappiness(petRoomViewModel.getState().getHappiness());
        saveGameState.setApiPet(saveGameState.getCurrPet().getApiPet());
        saveGameViewModel.setState(saveGameState);
        viewManagerModel.setState(saveGameViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
