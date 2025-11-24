package interface_adapter.set_parameters;

import interface_adapter.ViewManagerModel;
import interface_adapter.high_score.HighScoreState;
import interface_adapter.high_score.HighScoreViewModel;
import interface_adapter.select_animal.SelectAnimalState;
import interface_adapter.select_animal.SelectAnimalViewModel;
import use_case.set_parameters.SetParamOutputBoundary;
import use_case.set_parameters.SetParamOutputData;

public class SetParamPresenter implements SetParamOutputBoundary {

    private final SetParamViewModel setParamViewModel;
    //private final SelectAnimalViewModel selectAnimalViewModel;
    private final ViewManagerModel viewManagerModel;

    //TODO: testing
    private final HighScoreViewModel highScoreViewModel;

    //SelectAnimalViewModel selectAnimalViewModel
    public SetParamPresenter(SetParamViewModel setParamViewModel, HighScoreViewModel highScoreViewModel,
                             ViewManagerModel viewManagerModel) {
        this.setParamViewModel = setParamViewModel;
        //this.selectAnimalViewModel = selectAnimalViewModel;
        this.highScoreViewModel = highScoreViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SetParamOutputData response){
        //final SelectAnimalState selectAnimalState = selectAnimalViewModel.getState();
        //setting parameters
        //TODO: probs for the scrolling page stuff
        //this.selectAnimalViewModel.setState(selectAnimalState);
        //this.selectAnimalViewModel.firePropertyChanged();

        //TODO: testing
        final HighScoreState highScoreState = highScoreViewModel.getState();
        this.highScoreViewModel.setState(highScoreState);
        this.highScoreViewModel.firePropertyChanged();

        this.viewManagerModel.setState(highScoreViewModel.getViewName()); //selectAnimalViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage){
        final SetParamState setParamState = setParamViewModel.getState();
        setParamState.setSetParamError(errorMessage);
        setParamViewModel.firePropertyChanged();
    }

}
