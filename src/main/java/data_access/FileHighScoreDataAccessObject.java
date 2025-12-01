package data_access;

import java.io.*;
import java.util.ArrayList;

import entities.Constants;
import entities.HighScoreList;
import use_case.high_score.HighScoreDataAccessInterface;

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
            catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        highScores.sort((score0, score1) -> {
            return -1 * score0.compareTo(score1);
        }); // sort in reverse order
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
            catch (IOException exc) {
                throw new RuntimeException(exc);
            }
        }
    }

    /**
     * Saves high scores to save file.
     * @param score value to save.
     */

    public void save(int score) {
        // saves high scores
        highScores.add(score);
        highScores.sort((score0, score1) -> {
            return -1 * score0.compareTo(score1);
        });
        mostRecentlySavedScore = score;
        save();
    }

    /**
     * Retrieves the HighScoreList from the save file.
     * @return HighScoreList of the save file's contents
     */

    public HighScoreList get() {
        return new HighScoreList(highScores);
    }

    /**
     * Converts the HighScoreList into a String that can be more easily displayed.
     * @return string formatted like a table of the top 10 scores.
     */
    public String getAsString() {
        final StringBuilder builder = new StringBuilder();
        int i = 1;
        builder.append("<html><pre>");
        for (Integer score : highScores) {
            builder.append("<b>" + i + ".\t</b>");
            builder.append(score + "\n");
            i++;
            if (i >= Constants.MAX_SHOWN_SCORES) {
                break;
            }
        }
        builder.append("</pre></html>");
        return builder.toString();
    }

    public String getLastAsString() {
        return "Final Score: " + mostRecentlySavedScore;
    }
}
