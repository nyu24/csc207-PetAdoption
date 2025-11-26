package interface_adapter.high_score;

import interface_adapter.ViewManagerModel;
import interface_adapter.select_animal.SelectAnimalState;
import interface_adapter.set_parameters.SetParamState;
import interface_adapter.set_parameters.SetParamViewModel;
import use_case.high_score.HighScoreOutputBoundary;
import use_case.set_parameters.SetParamOutputData;

public class HighScorePresenter implements HighScoreOutputBoundary {
    private final HighScoreViewModel highScoreViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SetParamViewModel setParamViewModel;
    public HighScorePresenter(ViewManagerModel viewManagerModel, HighScoreViewModel highScoreViewModel, SetParamViewModel setParamViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.highScoreViewModel = highScoreViewModel;
        this.setParamViewModel = setParamViewModel;
    }
    // switches between other views.
    @Override
    public void prepareSuccessView(SetParamOutputData response){
//        final HighScoreState highScoreState = highScoreViewModel.getState();
//        highScoreViewModel.firePropertyChanged();
//        this.viewManagerModel.setState(highScoreViewModel.getViewName());
//        this.viewManagerModel.firePropertyChanged();
        final SetParamState setParamState = setParamViewModel.getState();
        this.setParamViewModel.setState(setParamState);
        this.setParamViewModel.firePropertyChanged();

        this.viewManagerModel.setState(setParamViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage){
        final HighScoreState highScoreState = highScoreViewModel.getState();
        highScoreState.setHighScoreError(errorMessage);
        highScoreViewModel.firePropertyChanged();
    }

    @Override
    public void switchToSetParamView() {
        viewManagerModel.setState(setParamViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


}
