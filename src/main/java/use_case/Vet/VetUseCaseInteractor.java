package use_case.Vet;

import entities.Pet;
import entities.Vet;

public class VetUseCaseInteractor implements VetInputBoundary{

    private final Vet vet;

    public VetUseCaseInteractor(Vet vet) {
        this.vet = vet;
    }

    public void execute(VetInputData vetInputData) {
        Pet pet = vetInputData.getPet();
        boolean gameWin = this.vet.checkWin(pet);
        final VetOutputData vetOutputData = new VetOutputData(gameWin);
    }
}
