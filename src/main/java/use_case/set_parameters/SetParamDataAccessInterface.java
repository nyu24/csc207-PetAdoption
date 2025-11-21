package use_case.set_parameters;

import entities.APIPet;

import java.util.ArrayList;

/**
 * DAO for the Set Parameter Use Case
 */
public interface SetParamDataAccessInterface {

    /**
     * Returns the APIPetPage with the given parameters
     */
    ArrayList<APIPet> getApiPetArrayList(String type, String coat, String colour, String breed, String gender);
}
