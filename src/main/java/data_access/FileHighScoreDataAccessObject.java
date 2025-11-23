package data_access;

import entities.HighScoreList;
import use_case.high_score.HighScoreDataAccessInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FileHighScoreDataAccessObject implements HighScoreDataAccessInterface {

//    private static final String HEADER = "Highscores";

    private final File csvFile;
    private final ArrayList<Integer> highScores = new ArrayList<Integer>();


    // Gets high scores from csv at specified directory, loads them into highScores ArrayList
    public FileHighScoreDataAccessObject(String csvPath) throws IOException {
        this.csvFile = new File(csvPath);

        if (csvFile.length() == 0) {
            save();
        }

        else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
//                final String header = reader.readLine();


                String row;
                while ((row = reader.readLine()) != null) {
                    final String[] col = row.split(",");
                    for (int i = 0; i < col.length; i++) {
                        highScores.add(Integer.parseInt(col[i]));
                    }
                }
            }
        }
    }

    private void save() {
        final BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
//            writer.write(String.join(",", headers.keySet()));
//            writer.newLine();

            for (int score : highScores) {
                final String line = Integer.toString(score) + ",";
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void save(int score) {
        // saves high scores
        highScores.add(score);
        save();
    }

    public HighScoreList get() {
        return new HighScoreList(highScores);
    }

    public String getAsString() {
        StringBuilder builder = new StringBuilder();
        builder.append("<html><pre>");
        for (Integer score : highScores) {
            builder.append(score + "\n");
        }
        builder.append("</pre></html>"); // this works for some reason?
        return builder.toString();
    }
    // hashmap stores




}
