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
        if (!room.isBasic() && !action.equals("tick")) {
            petRoomPresenter.prepareFailView("Pet is busy.");
            return;
        }
        if (action.equals("feed")) {
            room.applyFoodAction();
        } else if (action.equals("water")) {
            room.applyWaterAction();
        } else if (action.equals("clean")) {
            room.applyCleanlinessAction();
        } else if (action.equals("tick")) {
            room.tick();
        } else {
            petRoomPresenter.prepareFailView("Invalid action.");
            return;
        }

        PetRoomOutputData petRoomOutputData = new PetRoomOutputData(room.getFood(), room.getWater(), room.getCleanliness(), room.getHappiness(), mapBackground(room.getBackgroundMode()), room.isBasic());
        petRoomPresenter.prepareSuccessView(petRoomOutputData);
    }
    public String mapBackground(String mode) {
        if(mode.equals("basic")) {return "dog_room_basic.jpg";}
        else if(mode.equals("food")) {
            return "dog_room_food.jpg";
        }
        else if(mode.equals("water")) { return "dog_room_water.jpg";}
        else if(mode.equals("sleepy")) {return "dog_room_sleepy.jpg";}
        else{
            return "dog_room_basic.jpg";
        }
    }


}
