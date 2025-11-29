package use_case.select_animal;

import entities.APIPet;


public class SelectAnimalInputData {
    private final APIPet apiPet;

    public SelectAnimalInputData(APIPet pet) {
        this.apiPet = pet;
    }

    public APIPet getApiPet() {
        return apiPet;
    }
}
