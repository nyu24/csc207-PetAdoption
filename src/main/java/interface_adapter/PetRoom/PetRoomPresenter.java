package interface_adapter.PetRoom;

import use_case.PetRoom.PetRoomOutputBoundary;
import use_case.PetRoom.PetRoomOutputData;

public class PetRoomPresenter implements PetRoomOutputBoundary{
    private final PetRoomViewModel petRoomViewModel;
    public PetRoomPresenter(PetRoomViewModel petRoomViewModel) {
        this.petRoomViewModel = petRoomViewModel;
    }
    @Override
    public void prepareSuccessView(PetRoomOutputData petRoomOutputData) {
        PetRoomState petRoomState = new PetRoomState();
        petRoomState.setFood(petRoomOutputData.getFinalFood());
        petRoomState.setWater(petRoomOutputData.getFinalWater());
        petRoomState.setCleanliness(petRoomOutputData.getFinalCleanliness());
        petRoomState.setHappiness(petRoomOutputData.getFinalHappiness());
        petRoomViewModel.setState(petRoomState);
        petRoomViewModel.firePropertyChanged();
    }
    @Override
    public void prepareFailView(String message) {
        System.out.println("Pet Room Error:" + message);
    }
}
