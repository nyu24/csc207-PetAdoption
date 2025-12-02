package use_case.set_parameters;

import data_access.ApiPetDataAccessObject;
import entities.ApiPet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SetParametersInteractorTest {

    // Tests to see if the accessed APIPage is correct
    @Test
    void successAPIPageResult(){
        SetParamInputData inputData = new SetParamInputData("Barnyard", "Short", "White", "Sheep", "Male");
        SetParamDataAccessInterface dataAccessInterface = new ApiPetDataAccessObject();

        SetParamOutputBoundary successPresenter = new SetParamOutputBoundary() {
            @Override
            public void prepareSuccessView(SetParamOutputData outputData) {
                ArrayList<ApiPet> apiPage = dataAccessInterface.getApiPetArrayList(inputData.getType(),
                        inputData.getCoat(), inputData.getColour(),
                        inputData.getBreed(), inputData.getGender());

                ArrayList<ApiPet> outputPage = outputData.getApiPetArrayList();
                // as each object APIPet will have different internal identifiers, I'll compare the values of each APIPet
                assertEquals(apiPage.size(), outputPage.size());

                //NOTE: sometimes this may fail if given parameters that are common.
                //Hence, why all the parameters are used, and for an uncommon animal
                //Because the API may update the page WHILE the test is being run.
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

    //Makes sure the program 'fails' when the animal type parameter is empty
    @Test
    void failureEmptyType(){
        SetParamInputData inputData = new SetParamInputData("", "", "", "", "Male");
        SetParamDataAccessInterface dataAccessInterface = new ApiPetDataAccessObject();

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

    // Checks if Types list is obtained/run
    @Test
    void successTypeList(){
        SetParamDataAccessInterface dataAccessInterface = new ApiPetDataAccessObject();

        SetParamOutputBoundary successPresenter = new SetParamOutputBoundary() {
            @Override
            public void prepareSuccessView(SetParamOutputData outputData) {
                // making sure the DAI exists to call later
                assertNotNull(dataAccessInterface);
            }
            @Override
            public void prepareFailView(String errorMessage) {
                fail("Failure is unexpected.");
            }
        };
        SetParamInteractor interactor = new SetParamInteractor(dataAccessInterface,  successPresenter);

        interactor.getTypes();
    }

    // Checks if Attributes list is obtained/run
    @Test
    void successAttributesTypeList(){
        SetParamInputData inputData = new SetParamInputData("Cat", "", "", "", "");
        SetParamDataAccessInterface dataAccessInterface = new ApiPetDataAccessObject();

        SetParamOutputBoundary successPresenter = new SetParamOutputBoundary() {
            @Override
            public void prepareSuccessView(SetParamOutputData outputData) {
                // making sure the type exists to use
                assertNotNull(inputData.getType());
            }
            @Override
            public void prepareFailView(String errorMessage) {
                fail("Failure is unexpected.");
            }
        };
        SetParamInteractor interactor = new SetParamInteractor(dataAccessInterface,  successPresenter);

        interactor.getTypeAttributes(inputData.getType());
    }
}
