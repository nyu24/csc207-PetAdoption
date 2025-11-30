package use_case.select_animal;

import entities.APIPet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SelectAnimalInteractorTest {

    //Test for making sure the correct APIPet is stored within the new Pet
    @Test
    void successfulAnimalSelection(){
        //Creating an APIPet that will be used in the test
        APIPet apiPet = new APIPet();
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

        SelectAnimalOutputBoundary successPresenter = outputData -> {
            //Checks to see if the correct apiPet is stored, other variables aren't necessary to test
            //as they are created by execute(); and isn't provided in the inputData for SelectAnimalInputData
            Assertions.assertEquals(outputData.getPet().getApiPet(), apiPet);
        };

        SelectAnimalInputBoundary interactor = new SelectAnimalInteractor(successPresenter);
        interactor.execute(inputData);
    }
}
