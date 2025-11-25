package use_case.PetRoom;

public class PetRoomOutputData {
    private final int finalFood;
    private final int finalWater;
    private final int finalHappiness;
    private final int finalCleanliness;
    private final String backgroundImageName;
    private final boolean buttonsEnabled;


    public PetRoomOutputData(int finalFood, int finalWater, int finalHappiness, int finalCleanliness, String backgroundImageName, boolean buttonsEnabled) {
        this.finalFood = finalFood;
        this.finalWater = finalWater;
        this.finalHappiness = finalHappiness;
        this.finalCleanliness = finalCleanliness;
        this.backgroundImageName = backgroundImageName;
        this.buttonsEnabled = buttonsEnabled;

    }
    public int getFinalFood() {return finalFood;}
    public int getFinalWater() {return finalWater;}
    public int getFinalHappiness() {return finalHappiness;}
    public int getFinalCleanliness() {return finalCleanliness;}
    public String getBackgroundImageName() {return backgroundImageName;}
    public boolean getButtonsEnabled() {return buttonsEnabled;}
}