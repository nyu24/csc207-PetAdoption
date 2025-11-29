package interface_adapter.select_animal;

import entities.APIPet;

import java.util.ArrayList;

public class SelectAnimalState{
    private ArrayList<APIPet> apiPetList;

    public ArrayList<APIPet> getApiPetList() {
        return apiPetList;
    }
    public void setApiPetList(ArrayList<APIPet> apiPetList) {
        this.apiPetList = apiPetList;
    }
}
