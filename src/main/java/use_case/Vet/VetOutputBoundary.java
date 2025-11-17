package use_case.Vet;

public interface VetOutputBoundary {

    void prepareSuccessView(VetOutputData vetOutputData);

    void prepareFailView(String errorMessage);
}
