package use_case.select_animal;

import entities.ApiPet;

/**
 * Input data to be received from Set Parameter.
 */
public class SelectAnimalInputData {
    private final ApiPet apiPet;

    public SelectAnimalInputData(ApiPet pet) {
        this.apiPet = pet;
    }

    public ApiPet getApiPet() {
        return apiPet;
    }
}
