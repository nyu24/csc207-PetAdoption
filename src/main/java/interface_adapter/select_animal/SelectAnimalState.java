package interface_adapter.select_animal;

import entities.APIPet;

import java.util.ArrayList;

public class SelectAnimalState{
    private APIPet apiPet;
    private String selectAnimalError;
    private ArrayList<APIPet> apiPetList;

    public APIPet getApiPet() {
        return apiPet;
    }
    public void setApiPet(APIPet apiPet) {
        this.apiPet = apiPet;
    }

    public ArrayList<APIPet> getApiPetList() {
        return apiPetList;
    }
    public void setApiPetList(ArrayList<APIPet> apiPetList) {
        this.apiPetList = apiPetList;
    }

    //error getter and setter TODO: to figure out later
    public String getSelectAnimalError() {
        return selectAnimalError;
    }
    public void setSelectAnimalError(String selectAnimalError) {
        this.selectAnimalError = selectAnimalError;
    }
}
