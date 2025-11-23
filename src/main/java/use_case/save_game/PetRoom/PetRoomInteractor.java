package use_case.save_game.PetRoom;

public class PetRoomInteractor implements PetRoomInputBoundary{
    private final PetRoomUserDataAccessInterface petRoomUserDataAccessObject;
    private final PetRoomOutputBoundary petRoomPresenter;

    public PetRoomInteractor(PetRoomUserDataAccessInterface petRoomUserDataAccessInterface, PetRoomOutputBoundary petRoomOutputBoundary){
        this.petRoomUserDataAccessObject = petRoomUserDataAccessInterface;
        this.petRoomPresenter = petRoomOutputBoundary;
    }

    @Override
    public void execute(PetRoomInputData petRoomInputData) {
        final int food_level = petRoomInputData.getFood_level();
        final int water_level = petRoomInputData.getWater_level();
        final int joy_level = petRoomInputData.getJoy_level();
        final int clean_level = petRoomInputData.getClean_level();

    }
}
