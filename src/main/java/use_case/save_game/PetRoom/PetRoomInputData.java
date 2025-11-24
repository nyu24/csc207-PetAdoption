package use_case.save_game.PetRoom;

public class PetRoomInputData {
    private final String petName;
    private final String petType;
    private final int foodLevel;
    private final int waterLevel;
    private final int joyLevel;
    private final int cleanLevel;

    public PetRoomInputData(String pet_name, String pet_type, int food_level, int water_level, int joy_level, int clean_level) {
        this.petName = pet_name;
        this.petType = pet_type;
        this.foodLevel = food_level;
        this.waterLevel = water_level;
        this.joyLevel = joy_level;
        this.cleanLevel = clean_level;

    }

    String getPetName() {
        return petName;
    }
    String getPetType() {
        return petType;
    }
    int getFoodLevel() {
        return foodLevel;
    }
    int getWaterLevel() {
        return waterLevel;
    }
    int getJoyLevel() {
        return joyLevel;
    }
    int getCleanLevel() {
        return cleanLevel;
    }
}
