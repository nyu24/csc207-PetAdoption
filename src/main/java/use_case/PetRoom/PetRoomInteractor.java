package use_case.PetRoom;
import entities.APIPet;
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
        String petType = petRoomInputData.getPetType();
        int food = petRoomInputData.getFood();
        int water = petRoomInputData.getWater();
        int clean =  petRoomInputData.getClean();
        int happy =  petRoomInputData.getHappy();
        if (petType != null && !petType.isEmpty()) {
            room.setPetType(petType);
        }
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
                break;
            case "tick":
                room.tick(food, water, clean, happy);
                break;
            default:
                petRoomPresenter.prepareFailView("Invalid action.");
                return;
        }

        PetRoomOutputData petRoomOutputData = new PetRoomOutputData(room.getFood(), room.getWater(), room.getCleanliness(), room.getHappiness(), room.getScore(), room.getRoomType(), room.getPetType());
        petRoomPresenter.updateValues(petRoomOutputData);
    }
}
