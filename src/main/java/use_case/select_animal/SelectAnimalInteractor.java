package use_case.select_animal;

import entities.Pet;

public class SelectAnimalInteractor implements SelectAnimalInputBoundary{
    private final SelectAnimalOutputBoundary selectAnimalPresenter;

    public SelectAnimalInteractor(SelectAnimalOutputBoundary selectAnimalOutputBoundary) {
        this.selectAnimalPresenter = selectAnimalOutputBoundary;
    }

    @Override
    public void execute(SelectAnimalInputData selectAnimalInputData) {
        int maximumValue = 100;
        int createdValue = 100;
        final Pet pet = new Pet(maximumValue, maximumValue, maximumValue, maximumValue);
        pet.setThirst(createdValue);
        pet.setHunger(createdValue);
        pet.setCleanliness(createdValue);
        pet.setHappiness(createdValue);

        pet.setApiPet(selectAnimalInputData.getApiPet());

        final SelectAnimalOutputData selectAnimalOutputData = new SelectAnimalOutputData(pet);
        selectAnimalPresenter.prepareSuccessView(selectAnimalOutputData);
    }
}
