package use_case.PetRoom;

public class PetRoomInputData {
    private final String action;
    private final String type;
    public PetRoomInputData(String action, String type) {
        this.action = action;
        this.type = type;


    }
    public String getAction() {return action;}
    public String getType() {return type;}
}
