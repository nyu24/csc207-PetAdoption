package use_case.Vet;

import entities.Pet;
import entities.Vet;

import java.util.List;

public class VetUseCaseInteractor implements VetInputBoundary{

    private final Vet vet;
    private final VetOutputBoundary vetPresenter;
    public VetUseCaseInteractor(Vet vet, VetOutputBoundary vetPresenter) {
        this.vet = vet;
        this.vetPresenter = vetPresenter;
    }

    public void execute(VetInputData vetInputData) {
        Pet pet = vetInputData.getPet();
        List<List<String>> requirements = this.vet.checkRequirements(pet);
        final VetOutputData vetOutputData = new VetOutputData(requirements);

        this.vetPresenter.prepareSuccessView(vetOutputData);
    }
}
