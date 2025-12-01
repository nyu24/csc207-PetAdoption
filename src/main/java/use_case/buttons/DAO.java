package use_case.buttons;

import entities.Pet;

public class DAO implements Repository {
    private Pet pet;

    public DAO(Pet initialpet) {
        pet = initialpet;
    }

    @Override
    public Pet load() {
        return pet ;
    }

    public void save(Pet pet) {
        this.pet = pet;
    }
}
