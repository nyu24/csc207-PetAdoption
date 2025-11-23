package use_case.save_game.PetRoom;

public class PetRoomInputData {
    private final String pet_name;
    private final String pet_type;
    private final int food_level;
    private final int water_level;
    private final int joy_level;
    private final int clean_level;

    public PetRoomInputData(String pet_name, String pet_type, int food_level, int water_level, int joy_level, int clean_level) {
        this.pet_name = pet_name;
        this.pet_type = pet_type;
        this.food_level = food_level;
        this.water_level = water_level;
        this.joy_level = joy_level;
        this.clean_level = clean_level;

    }

    String getPet_name() {
        return pet_name;
    }
    String getPet_type() {
        return pet_type;
    }
    int getFood_level() {
        return food_level;
    }
    int getWater_level() {
        return water_level;
    }
    int getJoy_level() {
        return joy_level;
    }
    int getClean_level() {
        return clean_level;
    }
}
