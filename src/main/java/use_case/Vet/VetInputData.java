package use_case.Vet;

import entities.Pet;

public class VetInputData {
    private Pet pet;

    public VetInputData(Pet pet) {
        this.pet = pet;
    }

    public Pet getPet() {
        return pet;
    }

}
