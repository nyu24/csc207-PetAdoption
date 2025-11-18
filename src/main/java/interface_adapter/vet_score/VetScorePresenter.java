package interface_adapter.vet_score;

import interface_adapter.ViewManagerModel;
import use_case.Vet.VetOutputBoundary;
import use_case.Vet.VetOutputData;

public class VetScorePresenter implements VetOutputBoundary {
    private final VetScoreViewModel vetScoreViewModel;
    private final ViewManagerModel viewManagerModel;

    public VetScorePresenter(VetScoreViewModel vetScoreViewModel,
                             ViewManagerModel viewManagerModel) {

        this.vetScoreViewModel = vetScoreViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView(VetOutputData vetOutputData) {
        vetScoreViewModel.firePropertyChanged("vet checking score");
    }

    @Override
    public void prepareFailView(String message) {

    }

}
