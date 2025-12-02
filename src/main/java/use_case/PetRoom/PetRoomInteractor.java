package use_case.PetRoom;
import entities.Pet;
import entities.Room;
import entities.Vet;

public class PetRoomInteractor implements PetRoomInputBoundary {
    private final Room room;
    private final PetRoomOutputBoundary petRoomPresenter;
    private final Vet vet;
    private String action;
    private String petType;
    private PetRoomOutputData petRoomOutputData;

    public PetRoomInteractor(Room room, PetRoomOutputBoundary petRoomPresenter, Vet vet) {
        this.room = room;
        this.petRoomPresenter = petRoomPresenter;
        this.vet = vet;
    }

    @Override
    public void execute(PetRoomInputData petRoomInputData) {
        action = petRoomInputData.getAction();
        petType = petRoomInputData.getPetType();
        int score = petRoomInputData.getScore();
        if (petType != null && !petType.isEmpty()) {
            room.setPetType(petType);
        }
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
                    break;
                case "tick":
                    room.tick();
                    if (vet.inRange(room.getFood()) && vet.inRange(room.getWater())
                            &&
                            vet.inRange(room.getCleanliness()) && vet.inRange(room.getHappiness())) {
                        score += 1;
                    }
                    break;
                default:
                    petRoomPresenter.prepareFailView("Invalid action.");
                    return;
            }
        }

        petRoomOutputData = new PetRoomOutputData(room.getFood(), room.getWater(),
                room.getCleanliness(), room.getHappiness(), score, room.getPetType(), room.getRoomType(),
                room.getCurrPet());
        petRoomPresenter.updateValues(petRoomOutputData);
    }

    @Override
    public void setRoomParameters(int food, int water, int cleanliness, int happiness, Pet currPet) {
        room.setFood(food);
        room.setWater(water);
        room.setCleanliness(cleanliness);
        room.setHappiness(happiness);
        room.setCurrPet(currPet);
    }

    @Override
    public void switchToSaveGameView() {
        petRoomPresenter.switchToSaveGameView();
    }


}
