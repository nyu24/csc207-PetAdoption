package data_access;

import entities.HighScoreList;
import use_case.high_score.HighScoreDataAccessInterface;

import java.io.*;
import java.util.ArrayList;

/**
 * Retrieves and process high scores from a CSV high score save file.
 */
public class FileHighScoreDataAccessObject implements HighScoreDataAccessInterface {

    private final File csvFile;
    private final ArrayList<Integer> highScores = new ArrayList<>();
    private int mostRecentlySavedScore = -1;

    // Gets high scores from csv at specified directory, loads them into highScores ArrayList
    public FileHighScoreDataAccessObject(String csvPath) {
        this.csvFile = new File(csvPath);

        if (csvFile.length() == 0) {
            save();
        }

        else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {


                String row;
                while ((row = reader.readLine()) != null) {
                    final String[] col = row.split(",");
                    for (int i = 0; i < col.length; i++) {
                        highScores.add(Integer.parseInt(col[i]));
                    }
                }
            }
            catch (IOException e) {
                //?
            }
        }

        highScores.sort((i0, i1) -> { return -1 * i0.compareTo(i1); }); // sort in reverse order
    }

    private void save() {
        final BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));

            for (int score : highScores) {
                final String line = Integer.toString(score) + ",";
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        }
        catch (IOException ex) {
            try {
                new File("high_scores.csv").createNewFile();
            }
            catch (IOException ex1) {
            }
        }
    }

    public void save(int score) {
        // saves high scores
        highScores.add(score);
        highScores.sort((i0, i1) -> { return -1 * i0.compareTo(i1); }); // sort in reverse order
        mostRecentlySavedScore = score;
        save();
    }

    public HighScoreList get() {
        return new HighScoreList(highScores);
    }

    public String getAsString() {
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

    public String getLastAsString() {
        return "Final Score: " + mostRecentlySavedScore;
    }
}
