package interface_adapter.set_parameters;

/**
 * State for the Set Parameters View
 */
public class SetParamState {
    //parameters
    private String type;
    private String colour;
    private String breed;
    private String gender;
    private String coat;

    //error TODO: idk how this works figure it out
    private String setParamError;

    //getters and setters
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getColour() {
        return colour;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getBreed() {
        return breed;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCoat() {
        return coat;
    }
    public void setCoat(String coat) {
        this.coat = coat;
    }

    //error getter and setter TODO: to figure out later
    public String getSetParamError() {
        return setParamError;
    }
    public void setSetParamError(String setParamError) {
        this.setParamError = setParamError;
    }
}
