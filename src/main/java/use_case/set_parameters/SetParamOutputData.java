package use_case.set_parameters;

import entities.ApiPet;
import java.util.*;

/**
 * Output Data for the Set Param Use Case.
 */
public class SetParamOutputData {
    private final ArrayList<ApiPet> apiPetArrayList;

    public SetParamOutputData(ArrayList<ApiPet> apiPetArrayList) {
        this.apiPetArrayList = apiPetArrayList;
    }

    public ArrayList<ApiPet> getApiPetArrayList() {
        return apiPetArrayList;
    }

}