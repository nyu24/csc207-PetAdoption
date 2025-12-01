package interface_adapter.high_score;

import interface_adapter.ViewManagerModel;
import interface_adapter.select_animal.SelectAnimalState;
import interface_adapter.set_parameters.SetParamState;
import interface_adapter.set_parameters.SetParamViewModel;
import interface_adapter.title.TitleViewModel;
import use_case.high_score.HighScoreOutputBoundary;
import use_case.high_score.HighScoreOutputData;
import use_case.set_parameters.SetParamOutputData;

/**
 * Presenter clas for the high score use case.
 */

public class HighScorePresenter implements HighScoreOutputBoundary {
    private final HighScoreViewModel highScoreViewModel;
    private final ViewManagerModel viewManagerModel;
    private final TitleViewModel titleViewModel;
    public HighScorePresenter(ViewManagerModel viewManagerModel, HighScoreViewModel highScoreViewModel, TitleViewModel titleViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.highScoreViewModel = highScoreViewModel;
        this.titleViewModel = titleViewModel;
    }
    // switches between other views.
    @Override
    public void prepareSuccessView(HighScoreOutputData highScoreOutputData) {
        final HighScoreState highScoreState = highScoreViewModel.getState();
        highScoreState.setHighScoreList(highScoreOutputData.getHighScoreList());
        highScoreViewModel.firePropertyChanged();

        this.viewManagerModel.setState(highScoreViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage){
        final HighScoreState highScoreState = highScoreViewModel.getState();
        highScoreState.setHighScoreError(errorMessage);
        highScoreViewModel.firePropertyChanged();
    }

    @Override
    public void switchToTitleView(){
        viewManagerModel.setState(titleViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }



}
