package interface_adapter.PetRoom;

import interface_adapter.ViewManagerModel;
import interface_adapter.vet_score.VetScoreViewModel;
import use_case.PetRoom.PetRoomOutputBoundary;
import use_case.PetRoom.PetRoomOutputData;

public class PetRoomPresenter implements PetRoomOutputBoundary{
    private final PetRoomViewModel petRoomViewModel;
    private final ViewManagerModel viewManagerModel;
//    private final VetScoreViewModel vetScoreViewModel;
    public PetRoomPresenter(PetRoomViewModel petRoomViewModel, ViewManagerModel viewManagerModel) {
        this.petRoomViewModel = petRoomViewModel;
        this.viewManagerModel = viewManagerModel;
//        this.vetScoreViewModel = new VetScoreViewModel();
    }
    @Override
    public void prepareSuccessView(PetRoomOutputData petRoomOutputData) {
        PetRoomState petRoomState = new PetRoomState();
        petRoomState.setFood(petRoomOutputData.getFinalFood());
        petRoomState.setWater(petRoomOutputData.getFinalWater());
        petRoomState.setCleanliness(petRoomOutputData.getFinalCleanliness());
        petRoomState.setHappiness(petRoomOutputData.getFinalHappiness());
        petRoomState.setBackgroundImageName(petRoomOutputData.getBackgroundImageName());
        petRoomState.setButtonsEnabled(petRoomOutputData.getButtonsEnabled());
        petRoomViewModel.setState(petRoomState);
        petRoomViewModel.firePropertyChanged();
    }
    @Override
    public void prepareFailView(String message) {
        System.out.println("Pet Room Error:" + message);
    }

//    @Override
//    public void switchToVetView(){
//        viewManagerModel.setState(vetScoreViewModel.getViewName());
//        viewManagerModel.firePropertyChanged();
//    }
}
