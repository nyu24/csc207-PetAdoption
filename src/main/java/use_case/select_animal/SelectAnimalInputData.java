package use_case.select_animal;

import entities.ApiPet;

/**
 * Input data for select animal uses case.
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
