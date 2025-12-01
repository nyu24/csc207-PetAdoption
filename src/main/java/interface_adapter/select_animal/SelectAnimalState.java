package interface_adapter.select_animal;

import java.util.ArrayList;

import entities.ApiPet;

public class SelectAnimalState {
    private ArrayList<ApiPet> apiPetList;

    public ArrayList<ApiPet> getApiPetList() {
        return apiPetList;
    }

    public void setApiPetList(ArrayList<ApiPet> apiPetList) {
        this.apiPetList = apiPetList;
    }
}
