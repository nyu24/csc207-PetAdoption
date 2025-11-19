package interface_adapter.high_score;

import interface_adapter.ViewModel;

public class HighScoreViewModel extends ViewModel<HighScoreState> {
    public static final String TITLE_LABEL = "High Scores";
    public static final String CLOSE_BUTTON_LABEL = "Close";

    public HighScoreViewModel() {
        super("High Scores");
        setState(new HighScoreState());
    }

}
