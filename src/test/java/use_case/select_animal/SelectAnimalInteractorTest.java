package use_case.select_animal;

import data_access.APIPetDataAccessObject;
import entities.APIPet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SelectAnimalInteractorTest {

    @Test
    void successfulAnimalSelection(){
        APIPet apiPet = new APIPet();
        //setting up apiPet for testing purposes
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
                assertEquals(outputData.getPet().getApiPet(), apiPet);
            }
        };

        SelectAnimalInputBoundary interactor = new SelectAnimalInteractor(successPresenter);
        interactor.execute(inputData);
    }
}
