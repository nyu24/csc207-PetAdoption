package interface_adapter.vet_score;

import interface_adapter.ViewManagerModel;
import interface_adapter.high_score.HighScoreState;
import interface_adapter.high_score.HighScoreViewModel;
import use_case.Vet.VetOutputBoundary;
import use_case.Vet.VetOutputData;

import javax.swing.*;

public class VetScorePresenter implements VetOutputBoundary {
    private final VetScoreViewModel vetScoreViewModel;
    private final ViewManagerModel viewManagerModel;
    private final HighScoreViewModel highscoreViewModel;

    public VetScorePresenter(VetScoreViewModel vetScoreViewModel,
                             ViewManagerModel viewManagerModel,
                             HighScoreViewModel highscoreViewModel) {

        this.vetScoreViewModel = vetScoreViewModel;
        this.viewManagerModel = viewManagerModel;
        this.highscoreViewModel = highscoreViewModel;
    }
    @Override
    public void prepareSuccessView(VetOutputData vetOutputData) {

        VetScoreState newState = new VetScoreState();
        newState.setMetRequirements(vetOutputData.getRequirements());
        newState.setScore(vetOutputData.getScore());
        newState.setCurrPet(vetOutputData.getCurrPet());
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
    public void switchToScoreView(VetOutputData vetOutputData) {
        HighScoreState newState = new HighScoreState();
        newState.setCurrentScore(vetOutputData.getScore());

        highscoreViewModel.setState(newState);
        highscoreViewModel.firePropertyChanged();


        viewManagerModel.setState(highscoreViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
