package use_case.buttons;

import entities.Pet;

public class ButtonsDataAccessObject implements ButtonsRepository {
    private Pet pet;

    public ButtonsDataAccessObject(Pet initialpet) {
        pet = initialpet;
    }

    @Override
    public Pet load() {
        return pet;
    }

    /**
     * Saves given pet.
     * @param new_pet is the pet that is saved.
     */
    public void save(Pet new_pet) {
        this.pet = new_pet;
    }
}
