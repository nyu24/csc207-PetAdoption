package use_case.select_animal;

import entities.ApiPet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class SelectAnimalInteractorTest {

    //Test for making sure the correct APIPet is stored within the new Pet
    @Test
    void successfulAnimalSelection(){
        //Creating an APIPet that will be used in the test
        ApiPet apiPet = new ApiPet();
        apiPet.setType("Dog");
        apiPet.setDescription("");
        apiPet.setName("");
        apiPet.setImage("");
        apiPet.setGender("Male");
        apiPet.setCoat("");
        apiPet.setColour("");
        apiPet.setBreed("");
        apiPet.setUrl("");

        SelectAnimalInputData inputData = new SelectAnimalInputData(apiPet);


        SelectAnimalOutputBoundary successPresenter = new SelectAnimalOutputBoundary() {
            @Override
            public void prepareSuccessView(SelectAnimalOutputData outputData) {
                //Checks to see if the correct apiPet is stored, other variables aren't necessary to test
                //as they are created by execute(); and isn't provided in the inputData for SelectAnimalInputData
                Assertions.assertEquals(outputData.getPet().getApiPet(), apiPet);
            }

            @Override
            public void prepareSuccessViewBack() {
                fail("This should not be reached.");
            }
        };

        SelectAnimalInputBoundary interactor = new SelectAnimalInteractor(successPresenter);
        interactor.execute(inputData);
    }

    // Checks if the back button is pressed
    @Test
    void successfulBack(){

        SelectAnimalOutputBoundary successPresenter = new SelectAnimalOutputBoundary() {
            @Override
            public void prepareSuccessView(SelectAnimalOutputData outputData) {
                fail("This should not be reached.");
            }

            @Override
            public void prepareSuccessViewBack() {
                // no need to check anything here, as im just checking if the button can be pressed with no error
            }
        };

        SelectAnimalInputBoundary interactor = new SelectAnimalInteractor(successPresenter);
        interactor.executeBack();
    }
}
