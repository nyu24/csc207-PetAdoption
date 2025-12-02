package use_case.buttons;

import entities.Pet;

public class ButtonsDataAccessObject implements ButtonsRepository {
    private Pet pet;

    public ButtonsDataAccessObject(Pet initialpet) {
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
