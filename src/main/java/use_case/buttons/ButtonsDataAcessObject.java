package use_case.buttons;

import entities.Pet;

public class ButtonsDataAcessObject implements ButtonsRepository {
    private Pet pet;

    public ButtonsDataAcessObject(Pet initialpet) {
        pet = initialpet;
    }

    @Override
    public Pet load() {
        return pet;
    }

    /**
     * Saves the given pet_object (pet).
     * @param pet_object the pet object that is saved.
      */
    public void save(Pet pet_object) {
        this.pet = pet_object;
    }
}
