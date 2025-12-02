package interface_adapter.title;

import interface_adapter.ViewManagerModel;
import interface_adapter.high_score.HighScoreViewModel;
import interface_adapter.load_game.LoadGameViewModel;
import interface_adapter.set_parameters.SetParamViewModel;
import use_case.switch_view.SwitchViewOutputBoundary;

public class SwitchViewPresenter implements SwitchViewOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final LoadGameViewModel loadGameViewModel;
    private final SetParamViewModel setParamViewModel;
    private final HighScoreViewModel highScoreViewModel;

    public SwitchViewPresenter(ViewManagerModel viewManagerModel,
                               LoadGameViewModel loadGameViewModel,
                               SetParamViewModel setParamViewModel,
                               HighScoreViewModel highScoreViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loadGameViewModel = loadGameViewModel;
        this.setParamViewModel = setParamViewModel;
        this.highScoreViewModel = highScoreViewModel;
    }

    @Override
    public void switchToLoadGameView() {
        viewManagerModel.setState(loadGameViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToSetParamView() {
        viewManagerModel.setState(setParamViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToHighScoreView() {
        viewManagerModel.setState(highScoreViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}