package use_case.select_animal;

import entities.Pet;

/**
 * Output data for select animal use case
 */
public class SelectAnimalOutputData {
    private final Pet pet;

    public SelectAnimalOutputData(Pet pet) {
        this.pet = pet;
    }

    public Pet getPet() {
        return pet;
    }
}
