package use_case.save_game.PetRoom;

public class PetRoomOutputData {
    private final String pet_name;
    private final int final_food;
    private final int final_water;
    private final int final_joy;
    private final int final_clean;

    public PetRoomOutputData(String pet_name, int final_food, int final_water, int final_joy, int final_clean) {
        this.pet_name = pet_name;
        this.final_food = final_food;
        this.final_water = final_water;
        this.final_joy = final_joy;
        this.final_clean = final_clean;
    }
    public String getPet_name() {
        return pet_name;
    }

    public int getFinal_food() {
        return final_food;
    }

    public int getFinal_water() {
        return final_water;
    }

    public int getFinal_joy() {
        return final_joy;
    }

    public int getFinal_clean() {
        return final_clean;
    }
}
