package use_case.PetRoom;
import entities.Room;
import entities.Vet;

public class PetRoomInteractor implements PetRoomInputBoundary {
    private final Room room;
    private final PetRoomOutputBoundary petRoomPresenter;
    private final Vet vet;

    public PetRoomInteractor(Room room, PetRoomOutputBoundary petRoomPresenter, Vet vet) {
        this.room = room;
        this.petRoomPresenter = petRoomPresenter;
        this.vet = vet;
    }

    @Override
    public void execute(PetRoomInputData petRoomInputData) {
        String action = petRoomInputData.getAction();
        int score = petRoomInputData.getScore();
        if (action != null) {
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
                    if (vet.inRange(room.getFood()) && vet.inRange(room.getWater()) &&
                            vet.inRange(room.getCleanliness()) && vet.inRange(room.getHappiness())){
                    score += 1;
                }
                    break;
                default:
                    petRoomPresenter.prepareFailView("Invalid action.");
                    return;
            }
        }

        PetRoomOutputData petRoomOutputData = new PetRoomOutputData(room.getFood(), room.getWater(), room.getCleanliness(), room.getHappiness(), score);
        petRoomPresenter.updateValues(petRoomOutputData);
    }

}
