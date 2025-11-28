package interface_adapter.vet_score;

import interface_adapter.ViewManagerModel;
import interface_adapter.high_score.HighScoreViewModel;
import use_case.Vet.VetOutputBoundary;
import use_case.Vet.VetOutputData;

import javax.swing.*;

public class VetScorePresenter implements VetOutputBoundary {
    private final VetScoreViewModel vetScoreViewModel;
    private final ViewManagerModel viewManagerModel;
    private final HighScoreViewModel highscoreViewModel = new HighScoreViewModel();

    public VetScorePresenter(VetScoreViewModel vetScoreViewModel,
                             ViewManagerModel viewManagerModel) {

        this.vetScoreViewModel = vetScoreViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView(VetOutputData vetOutputData) {

        VetScoreState newState = new VetScoreState();
        newState.setMetRequirements(vetOutputData.getRequirements());
        vetScoreViewModel.printListeners();
        // replace state in viewmodel
        vetScoreViewModel.setState(newState);
        vetScoreViewModel.firePropertyChanged();

        this.viewManagerModel.setState(vetScoreViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        }

    @Override
    public void prepareFailView(String message) {

    }

    @Override
    public void switchToScoreView() {
        viewManagerModel.setState(highscoreViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
