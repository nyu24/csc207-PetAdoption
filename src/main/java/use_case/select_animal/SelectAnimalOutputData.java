package use_case.select_animal;

import entities.Pet;

public class SelectAnimalOutputData {
    private final Pet pet;

    public SelectAnimalOutputData(Pet pet) {
        this.pet = pet;
    }

    public Pet getPet() {
        return pet;
    }
}
