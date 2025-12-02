package use_case.set_parameters;

import entities.ApiPet;
import java.util.*;

/**
 * Interactor for set parameters use case.
 */
public class SetParamInteractor implements SetParamInputBoundary {
    private final SetParamDataAccessInterface setParamDataAccessObject;
    private final SetParamOutputBoundary setParamPresenter;

    public SetParamInteractor(SetParamDataAccessInterface setParamDataAccessInterface,
                              SetParamOutputBoundary setParamOutputBoundary) {
        this.setParamDataAccessObject = setParamDataAccessInterface;
        this.setParamPresenter = setParamOutputBoundary;
    }

    /**
     * Executes the view switch and verifying if the type is there.
     * @param setParamInputData the input data
     */
    @Override
    public void execute(SetParamInputData setParamInputData) {
        final String type = setParamInputData.getType();

        // verify that the animal type is inputted (is mandatory)
        if (type.isEmpty()) {
            setParamPresenter.prepareFailView("You MUST choose an animal TYPE!");
        }
        else {
            final ArrayList<ApiPet> apiPage = setParamDataAccessObject.getApiPetArrayList(setParamInputData.getType(),
                    setParamInputData.getCoat(), setParamInputData.getColour(), setParamInputData.getBreed(),
                    setParamInputData.getGender());

            final SetParamOutputData setParamOutputData = new SetParamOutputData(apiPage);
            setParamPresenter.prepareSuccessView(setParamOutputData);
        }
    }

    /**
     * Gives the attributes for the specified type.
     * @param type of animal to get attributes of
     * @return list of list of string for the attributes.
     */
    public ArrayList<ArrayList<String>> getTypeAttributes(String type) {
        return setParamDataAccessObject.getTypeAttributesList(type);
    }

    /**
     * Returns a list of animal types.
     * @return a list of animal types
     */
    public ArrayList<String> getTypes() {
        return setParamDataAccessObject.getTypes();
    }
}
