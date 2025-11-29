package use_case.PetRoom;
import entities.APIPet;
import entities.Room;
import entities.Pet;
public class PetRoomInteractor implements PetRoomInputBoundary {
    private final Room room;
    private final Pet pet;
    private final PetRoomOutputBoundary petRoomPresenter;

    public PetRoomInteractor(Room room, Pet pet, PetRoomOutputBoundary petRoomPresenter) {
        this.room = room;
        this.pet = pet;
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

        room.setPetType(getTypeFromPet());
        PetRoomOutputData petRoomOutputData = new PetRoomOutputData(room.getFood(), room.getWater(), room.getCleanliness(), room.getHappiness(), room.getRoomType(), room.getPetType());
        petRoomPresenter.prepareSuccessView(petRoomOutputData);
    }
    public String getTypeFromPet(){
        APIPet type = pet.getApiPet();
        return type.getType();
    }
}
