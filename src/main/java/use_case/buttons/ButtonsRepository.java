package use_case.buttons;

import entities.Pet;

// repository interface for buttons
public interface ButtonsRepository {

    /**
     * Loads a new pet.
     * @return pet.
     */
    Pet load();

    /**
     * Saves the given pet.
     * @param pet is the pet that is saved.
     */
    void save(Pet pet);
}
