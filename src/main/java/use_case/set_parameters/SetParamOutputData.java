package use_case.set_parameters;

import entities.APIPet;
import java.util.*;

/**
 * Output Data for the Set Param Use Case
 */
public class SetParamOutputData {
    //TODO: review maybe, some thinga arent used
    private final ArrayList<APIPet> apiPetArrayList;
    private final boolean useCaseFailed;

    public SetParamOutputData(ArrayList<APIPet> apiPetArrayList,
                              boolean useCaseFailed) {
        this.apiPetArrayList = apiPetArrayList;
        this.useCaseFailed = useCaseFailed;
    }
    public ArrayList<APIPet> getApiPetArrayList() {
        return apiPetArrayList;
    }

}