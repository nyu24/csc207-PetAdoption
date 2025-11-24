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
        final int foodLevel = petRoomInputData.getFoodLevel();
        final int waterLevel = petRoomInputData.getWaterLevel();
        final int joyLevel = petRoomInputData.getJoyLevel();
        final int cleanLevel = petRoomInputData.getCleanLevel();

    }
}
