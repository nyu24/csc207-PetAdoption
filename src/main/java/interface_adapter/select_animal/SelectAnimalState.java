package interface_adapter.select_animal;

import entities.ApiPet;

import java.util.ArrayList;

public class SelectAnimalState{
    private ArrayList<ApiPet> apiPetList;

    public ArrayList<ApiPet> getApiPetList() {
        return apiPetList;
    }
    public void setApiPetList(ArrayList<ApiPet> apiPetList) {
        this.apiPetList = apiPetList;
    }
}
