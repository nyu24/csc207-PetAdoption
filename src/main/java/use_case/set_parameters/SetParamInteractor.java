package use_case.set_parameters;

import java.util.*;

import entities.ApiPet;

public class SetParamInteractor implements SetParamInputBoundary {
    private final SetParamDataAccessInterface setParamDataAccessObject;
    private final SetParamOutputBoundary setParamPresenter;

    public SetParamInteractor(SetParamDataAccessInterface setParamDataAccessInterface,
                              SetParamOutputBoundary setParamOutputBoundary) {
        this.setParamDataAccessObject = setParamDataAccessInterface;
        this.setParamPresenter = setParamOutputBoundary;
    }

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
     * This function returns the attributes for the specific type.
     * @param type of animal to get attributes of
     * @return type attributes
     */
    public ArrayList<ArrayList<String>> getTypeAttributes(String type) {
        return setParamDataAccessObject.getTypeAttributesList(type);
    }

    /**
     * Returns possible animal types.
     * @return an ArrayList of possible animal types
     */
    public ArrayList<String> getTypes() {
        return setParamDataAccessObject.getTypes();
    }
}
