package use_case.select_animal;

import entities.Pet;

public class SelectAnimalOutputData {
    private final Pet pet;
    private final boolean useCaseFailed;

    public SelectAnimalOutputData(Pet pet,
                              boolean useCaseFailed) {
        this.pet = pet;
        this.useCaseFailed = useCaseFailed;
    }
    public Pet getPet() {
        return pet;
    }
}
