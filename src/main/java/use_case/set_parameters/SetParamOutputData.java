package use_case.set_parameters;

import entities.APIPet;
import java.util.*;

/**
 * Output Data for the Set Param Use Case
 */
public class SetParamOutputData {
    //TODO: delete the commented code, if un-needed. aka when i figure this out
    /*
    private final String type;
    private final String breed;
    private final String coat;
    private final String colour;
    private final String gender;
     */
    private final ArrayList<APIPet> apiPetArrayList;
    private final boolean useCaseFailed;

    //String type, String breed, String coat, String colour, String gender,
    public SetParamOutputData(ArrayList<APIPet> apiPetArrayList,
                              boolean useCaseFailed) {
        /*this.type = type;
        this.breed = breed;
        this.coat = coat;
        this.colour = colour;
        this.gender = gender;*/
        this.apiPetArrayList = apiPetArrayList;
        this.useCaseFailed = useCaseFailed;
    }

    /*
    public String getType() {
        return type;
    }
    public String getBreed() {
        return breed;
    }
    public String getCoat() {
        return coat;
    }
    public String getColour() {
        return colour;
    }
    public String getGender() {
        return gender;
    }*/

    public ArrayList<APIPet> getApiPetArrayList() {
        return apiPetArrayList;
    }

}