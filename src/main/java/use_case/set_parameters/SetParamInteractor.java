package use_case.set_parameters;

import entities.APIPet;
import java.util.*;

public class SetParamInteractor implements SetParamInputBoundary{
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

        //verify that the animal type is inputted (is mandatory)
        if(type.isEmpty()){
            setParamPresenter.prepareFailView("You MUST choose an animal TYPE!");
        }
        else{
            //TODO: should call the APIPetDataAccessObject to create a page of apiPets using the correct
            //TODO: parameters
            final ArrayList<APIPet> apiPage = setParamDataAccessObject.getApiPetArrayList(setParamInputData.getType(),
                    setParamInputData.getCoat(), setParamInputData.getColour(), setParamInputData.getBreed(),
                    setParamInputData.getGender());

            //TODO: uhhh
            final SetParamOutputData setParamOutputData = new SetParamOutputData(apiPage, false);
            setParamPresenter.prepareSuccessView(setParamOutputData);
        }




    }
}
