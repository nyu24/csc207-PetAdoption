package use_case.buttons;

import entities.Pet;

/**
 * ButtonsRepository interface for buttons.
 */
public interface ButtonsRepository {

    /**
     * Loads a new pet.
     * @return a new pet
     */
    Pet load();

    /**
     * Saves a given pet.
     * @param pet is the pet that is saves
     */
    void save(Pet pet);
}
