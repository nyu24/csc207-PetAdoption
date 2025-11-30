package interface_adapter.PetRoom;

import interface_adapter.ViewManagerModel;
import interface_adapter.save_game.SaveGameState;
import interface_adapter.save_game.SaveGameViewModel;
import use_case.PetRoom.PetRoomOutputBoundary;
import use_case.PetRoom.PetRoomOutputData;

public class PetRoomPresenter implements PetRoomOutputBoundary{
    private final PetRoomViewModel petRoomViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SaveGameViewModel saveGameViewModel;
//    private final VetScoreViewModel vetScoreViewModel;
    public PetRoomPresenter(PetRoomViewModel petRoomViewModel, ViewManagerModel viewManagerModel, SaveGameViewModel saveGameViewModel) {
        this.petRoomViewModel = petRoomViewModel;
        this.viewManagerModel = viewManagerModel;
//        this.vetScoreViewModel = new VetScoreViewModel();
        this.saveGameViewModel = saveGameViewModel;
    }
    @Override
    public void updateValues(PetRoomOutputData petRoomOutputData) {
        PetRoomState petRoomState = new PetRoomState();
        petRoomState.setFood(petRoomOutputData.getFinalFood());
        petRoomState.setWater(petRoomOutputData.getFinalWater());
        petRoomState.setCleanliness(petRoomOutputData.getFinalCleanliness());
        petRoomState.setHappiness(petRoomOutputData.getFinalHappiness());
        petRoomState.setScore(petRoomOutputData.getScore());
        petRoomState.setPetType(petRoomOutputData.getPetType());
        petRoomState.setRoomType(petRoomOutputData.getRoomType());
        petRoomViewModel.setState(petRoomState);
        petRoomViewModel.firePropertyChange("value_update");
    }

    @Override
    public void prepareFailView(String message) {
        System.out.println("Pet Room Error:" + message);
    }

    @Override
    public void switchToSaveGameView() {
        final SaveGameState saveGameState = new SaveGameState();
        saveGameState.setTimeLeft(petRoomViewModel.getState().getTimer());
        saveGameState.setCurrScore(petRoomViewModel.getState().getScore());
        saveGameState.setCurrPet(petRoomViewModel.getState().getCurrPet());
        saveGameState.setAPIPet(saveGameState.getCurrentPet().getApiPet());
        viewManagerModel.setState(saveGameViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

//    @Override
//    public void switchToVetView(){
//        viewManagerModel.setState(vetScoreViewModel.getViewName());
//        viewManagerModel.firePropertyChanged();
//    }
}
