package use_case.set_parameters;

import entities.ApiPet;
import java.util.ArrayList;

/**
 * ButtonsDataAccessObject for the Set Parameter Use Case
 */
public interface SetParamDataAccessInterface {

    /**
     * Returns the APIPetPage with the given parameters.
     * @param type type of pet as a string
     * @param coat coat length as a string
     * @param colour colour as a string
     * @param breed breed of the pet as a string
     * @param gender gender of the animal as a string
     */
    ArrayList<ApiPet> getApiPetArrayList(String type, String coat, String colour, String breed, String gender);

    /**
     * Return an arraylist of arraylists, that correspond to the animal types attributes.
     * @param type of animal to get attributes of
     */
    ArrayList<ArrayList<String>> getTypeAttributesList(String type);

    /**
     * Return type lists.
     * @return typeList
     */
    ArrayList<String> getTypes();

}
