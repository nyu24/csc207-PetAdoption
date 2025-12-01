package use_case.set_parameters;

import entities.APIPet;
import java.util.*;

/**
 * Output Data for the Set Param Use Case
 */
public class SetParamOutputData {
    private final ArrayList<APIPet> apiPetArrayList;

    public SetParamOutputData(ArrayList<APIPet> apiPetArrayList) {
        this.apiPetArrayList = apiPetArrayList;
    }
    public ArrayList<APIPet> getApiPetArrayList() {
        return apiPetArrayList;
    }

}