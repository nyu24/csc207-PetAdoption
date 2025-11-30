package use_case.set_parameters;

import data_access.APIPetDataAccessObject;
import entities.APIPet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SetParametersInteractorTest {

    @Test
    void successAPIPageResult(){
        SetParamInputData inputData = new SetParamInputData("Dog", "", "", "", "Male");
        SetParamDataAccessInterface dataAccessInterface = new APIPetDataAccessObject();

        SetParamOutputBoundary successPresenter = new SetParamOutputBoundary() {
            @Override
            public void prepareSuccessView(SetParamOutputData outputData) {
                ArrayList<APIPet> apiPage = dataAccessInterface.getApiPetArrayList(inputData.getType(),
                        inputData.getCoat(), inputData.getColour(),
                        inputData.getBreed(), inputData.getGender());

                ArrayList<APIPet> outputPage = outputData.getApiPetArrayList();
                // as each object APIPet will have different internal identifiers, I'll compare the values of each APIPet
                assertEquals(apiPage.size(), outputPage.size());

                for(int i = 0; i < apiPage.size(); i++){
                    assertEquals(apiPage.get(i).getName(), outputPage.get(i).getName());
                    assertEquals(apiPage.get(i).getDescription(), outputPage.get(i).getDescription());
                    assertEquals(apiPage.get(i).getType(), outputPage.get(i).getType());
                    assertEquals(apiPage.get(i).getCoat(), outputPage.get(i).getCoat());
                    assertEquals(apiPage.get(i).getColour(), outputPage.get(i).getColour());
                    assertEquals(apiPage.get(i).getBreed(), outputPage.get(i).getBreed());
                    assertEquals(apiPage.get(i).getGender(), outputPage.get(i).getGender());
                    assertEquals(apiPage.get(i).getImage(), outputPage.get(i).getImage());
                    assertEquals(apiPage.get(i).getUrl(), outputPage.get(i).getUrl());
                }

            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Failure is unexpected.");
            }
        };

        SetParamInteractor interactor = new SetParamInteractor(dataAccessInterface, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureEmptyType(){
        SetParamInputData inputData = new SetParamInputData("", "", "", "", "Male");
        SetParamDataAccessInterface dataAccessInterface = new APIPetDataAccessObject();

        SetParamOutputBoundary failurePresenter = new SetParamOutputBoundary() {
            @Override
            public void prepareSuccessView(SetParamOutputData outputData) {
                fail("Success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("You MUST choose an animal TYPE!", errorMessage);
            }
        };

        SetParamInteractor interactor = new SetParamInteractor(dataAccessInterface, failurePresenter);
        interactor.execute(inputData);
    }
}
