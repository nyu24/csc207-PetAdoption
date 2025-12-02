package interface_adapter.PetRoom;

import interface_adapter.ViewModel;

public class PetRoomViewModel extends ViewModel<PetRoomState> {
    public PetRoomViewModel() {
        super("pet room");
        setState(new PetRoomState());
    }
}
