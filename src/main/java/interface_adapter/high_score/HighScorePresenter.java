package interface_adapter.high_score;

import interface_adapter.ViewManagerModel;
import use_case.high_score.HighScoreOutputBoundary;

public class HighScorePresenter implements HighScoreOutputBoundary {
    private final HighScoreViewModel highScoreViewModel;
    private final ViewManagerModel viewManagerModel;
    public HighScorePresenter(ViewManagerModel viewManagerModel, HighScoreViewModel highScoreViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.highScoreViewModel = highScoreViewModel;
    }
    // switches between other views.

}
