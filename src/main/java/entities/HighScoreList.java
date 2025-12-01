package entities;

import java.util.List;

public class HighScoreList {

    private List<Integer> highScores;

    public HighScoreList(List<Integer> highScores) {
        this.highScores = highScores;
    }

    public List<Integer> getHighScores() {
        return highScores;
    }

    public void setHighScores(List<Integer> highScores) {
        this.highScores = highScores;
    }

    @Override
    public String toString() {
        return "HighScoreList{" + "HighScores=" + highScores + '}';
    }

    /**
     * Converts the HighScoreList into a String that can be more easily displayed.
     * @return string formatted like a table of the top 10 scores.
     */
    public String printTopTen() {
        final StringBuilder builder = new StringBuilder();
        int i = 1;
        builder.append("<html><pre>");
        for (Integer score : highScores) {
            builder.append("<b>" + i + ".\t</b>");
            builder.append(score + "\n");
            i++;
            if (i > Constants.MAX_SHOWN_SCORES) {
                break;
            }
        }
        builder.append("</pre></html>");
        return builder.toString();
    }

}
