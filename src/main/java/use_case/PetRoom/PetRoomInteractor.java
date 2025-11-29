package use_case.PetRoom;
import entities.Room;
public class PetRoomInteractor implements PetRoomInputBoundary {
    private final Room room;
    private final PetRoomOutputBoundary petRoomPresenter;

    public PetRoomInteractor(Room room, PetRoomOutputBoundary petRoomPresenter) {
        this.room = room;
        this.petRoomPresenter = petRoomPresenter;
    }

    @Override
    public void execute(PetRoomInputData petRoomInputData) {
        String action = petRoomInputData.getAction();
        switch (action) {
            case "feed":
                room.applyFoodAction();
                break;
            case "water":
                room.applyWaterAction();
                break;
            case "clean":
                room.applyCleanlinessAction();
                break;
            case "play":
                room.applyHappinessAction();
            case "tick":
                room.tick();
                break;
            default:
                petRoomPresenter.prepareFailView("Invalid action.");
                return;
        }

        PetRoomOutputData petRoomOutputData = new PetRoomOutputData(room.getFood(), room.getWater(), room.getCleanliness(), room.getHappiness());
        petRoomPresenter.prepareSuccessView(petRoomOutputData);
    }

}
