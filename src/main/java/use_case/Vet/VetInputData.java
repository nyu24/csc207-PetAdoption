package use_case.Vet;

import entities.Pet;

import java.util.Map;

public class VetInputData {
    private Pet pet;
    private Map<String, Integer> stats;
    public VetInputData(Pet pet) {
        this.pet = pet;
    }

    public VetInputData(Map<String, Integer> stats) {
        this.stats = stats;
    }

    public Pet getPet() {
        return pet;
    }

    public Map<String, Integer> getStats(){
        return this.stats;
    }



}
