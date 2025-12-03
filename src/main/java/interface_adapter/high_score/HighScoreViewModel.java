package interface_adapter.high_score;

import interface_adapter.ViewModel;

public class HighScoreViewModel extends ViewModel<HighScoreState> {
    public static final String TITLE_LABEL = "High Scores";
    public static final String CLOSE_BUTTON_LABEL = "Save score and close";
    public static final String HIGH_SCORE_LIST = "High Score";

    public HighScoreViewModel() {
        super(TITLE_LABEL);
        setState(new HighScoreState());
    }

}
