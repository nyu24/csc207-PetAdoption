package entities;


import java.util.List;

public class HighScoreList {



    private List<Integer> HighScores;

    public HighScoreList(List<Integer> highScores) {
        HighScores = highScores;
    }

    public List<Integer> getHighScores() {
        return HighScores;
    }

    public void setHighScores(List<Integer> highScores) {
        HighScores = highScores;
    }


    @Override
    public String toString() {
        return "HighScoreList{" +
                "HighScores=" + HighScores +
                '}';
    }

}
