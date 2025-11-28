package use_case.high_score;

import entities.HighScoreList;

public interface HighScoreDataAccessInterface {

    void save(int score);

    HighScoreList get();
}
