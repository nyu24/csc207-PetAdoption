package use_case.set_parameters;

import entities.APIPet;
import java.util.ArrayList;

/**
 * DAO for the Set Parameter Use Case
 */
public interface SetParamDataAccessInterface {

    /**
     * Returns the APIPetPage with the given parameters
     * @param type type of pet as a string
     * @param coat coat length as a string
     * @param colour colour as a string
     * @param breed breed of the pet as a string
     * @param gender gender of the animal as a string
     */
    ArrayList<APIPet> getApiPetArrayList(String type, String coat, String colour, String breed, String gender);

    /**
     * return an arraylist of arraylists, that correspond to the animal types attributes
     * @param type
     */
    ArrayList<ArrayList<String>> getTypeAttributesList(String type);

    /**
     * return type lists
     * @return typeList
     */
    ArrayList<String> getTypes();

    void setAPIPetArrayList(ArrayList<APIPet> apiPetArrayList);
}
