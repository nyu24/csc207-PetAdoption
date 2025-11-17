package use_case.Vet;

import entities.Pet;
import entities.Vet;

public class VetUseCaseInteractor implements VetInputBoundary{

    private final Vet vet;
    private final VetOutputBoundary vetPresenter;
    public VetUseCaseInteractor(Vet vet, VetOutputBoundary vetPresenter) {
        this.vet = vet;
        this.vetPresenter = vetPresenter;
    }

    public void execute(VetInputData vetInputData) {
        Pet pet = vetInputData.getPet();
        boolean gameWin = this.vet.checkWin(pet);
        final VetOutputData vetOutputData = new VetOutputData(gameWin);

        this.vetPresenter.prepareSuccessView(vetOutputData);
    }
}
