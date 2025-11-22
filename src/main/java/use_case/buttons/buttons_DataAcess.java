package use_case.buttons;

import entities.Pet;

/**
 * DAO interface for buttons use case
 */
public interface buttons_DataAcess {
    /**
     * updates the hunger level of the pet
     * @param pet is the pet of which we want to update the hunger level
     */
    void updateHunger(Pet pet);

    /**
     * updates the cleanlisness level of the given pet
     */
    void updateCleanliness(Pet pet);

    /**
     * updates the thirst level of the pet
     */
    void updateThirst(Pet pet);

    /**
     * updates the happiness level of the pet
     */
    void updateHapiness(Pet pet);



}
