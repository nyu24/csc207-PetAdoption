package use_case.select_animal;

import entities.Pet;

public class SelectAnimalInteractor implements SelectAnimalInputBoundary{
    private final SelectAnimalOutputBoundary selectAnimalPresenter;

    public SelectAnimalInteractor(SelectAnimalOutputBoundary selectAnimalOutputBoundary) {
        this.selectAnimalPresenter = selectAnimalOutputBoundary;
    }

    @Override
    public void execute(SelectAnimalInputData selectAnimalInputData) {
        double maximumValue = 100.0;
        double createdValue = 100.0;
        final Pet pet = new Pet(maximumValue, maximumValue, maximumValue, maximumValue);
        pet.setThirst(createdValue);
        pet.setHunger(createdValue);
        pet.setCleanliness(createdValue);
        pet.setCleanliness(createdValue);

        pet.setApiPet(selectAnimalInputData.getApiPet());

        final SelectAnimalOutputData selectAnimalOutputData = new SelectAnimalOutputData(pet);
        selectAnimalPresenter.prepareSuccessView(selectAnimalOutputData);
    }
}
