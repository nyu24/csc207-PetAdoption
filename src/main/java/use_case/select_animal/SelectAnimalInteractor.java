package use_case.select_animal;

import entities.APIPet;
import entities.Pet;
import use_case.set_parameters.SetParamOutputData;

import java.util.ArrayList;

public class SelectAnimalInteractor implements SelectAnimalInputBoundary{
    private final SelectAnimalOutputBoundary selectAnimalPresenter;

    public SelectAnimalInteractor(SelectAnimalOutputBoundary selectAnimalOutputBoundary) {
        this.selectAnimalPresenter = selectAnimalOutputBoundary;
    }

    @Override
    public void execute(SelectAnimalInputData selectAnimalInputData) {
        final Pet pet = new Pet(100.0, 100.0, 100.0, 100.0);
        pet.setThirst(100.0);
        pet.setHunger(100.0);
        pet.setCleanliness(100.0);
        pet.setCleanliness(100.0);

        pet.setApiPet(selectAnimalInputData.getApiPet());

        final SelectAnimalOutputData selectAnimalOutputData = new SelectAnimalOutputData(pet, false);
        selectAnimalPresenter.prepareSuccessView(selectAnimalOutputData);
    }
}
