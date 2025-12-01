package use_case.high_score;

import entities.HighScoreList;

public interface HighScoreDataAccessInterface {

    /**
     * Saves the score.
     * @param score value to save.
     */
    void save(int score);

    /**
     * Gets the high score list.
     * @return the HighScoreList from the DAO.
     */
    HighScoreList get();
}
