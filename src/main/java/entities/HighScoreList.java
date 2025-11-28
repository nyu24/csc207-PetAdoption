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
        return "HighScoreList{" +
                "HighScores=" + highScores +
                '}';
    }

    public String printTopTen(){
        StringBuilder builder = new StringBuilder();
        int i = 1;
        builder.append("<html><pre>");
        for (Integer score : highScores) {
            builder.append("<b>" + i + ".\t</b>");
            builder.append(score + "\n");
            i++;
            if(i >= 11){
                break;
            }
        }
        builder.append("</pre></html>"); // this works for some reason?
        return builder.toString();
    }

}
