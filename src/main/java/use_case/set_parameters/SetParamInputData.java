package use_case.set_parameters;

public class SetParamInputData {
    //variables
    private String type;
    private String breed;
    private String colour;
    private String coat;
    private String gender;

    public SetParamInputData(String type, String coat, String colour, String breed, String gender) {
        this.type = type;
        this.breed = breed;
        this.colour = colour;
        this.coat = coat;
        this.gender = gender;
    }

    public String getType() {
        return type;
    }
    public String getBreed() {
        return breed;
    }
    public String getColour() {
        return colour;
    }
    public String getCoat() {
        return coat;
    }
    public String getGender() {
        return gender;
    }
}