package view;

import data_access.FileHighScoreDataAccessObject;
import interface_adapter.high_score.HighScoreController;
import interface_adapter.high_score.HighScoreViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class HighScoreView extends JPanel implements ActionListener, PropertyChangeListener {
    private HighScoreViewModel highScoreViewModel;
    private HighScoreController highScoreController = null;

    private final String viewName = "High Scores";
    private final JLabel highScoreLabel;
    private final JLabel currentScoreLabel;
    private HighScoreController highscoreController = null;

    public static final String CSV_SAVE_LOCATION = "src/test/java/high_scores.csv";
    {
        try {
            highScoreLabel = new JLabel(new FileHighScoreDataAccessObject(CSV_SAVE_LOCATION).getAsString());
            highScoreLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    {
        try {
            currentScoreLabel = new JLabel(new FileHighScoreDataAccessObject(CSV_SAVE_LOCATION).getLastAsString());
            currentScoreLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final JButton close;
    public HighScoreView(HighScoreViewModel highScoreViewModel) {
        this.highScoreViewModel = highScoreViewModel;
        highScoreViewModel.addPropertyChangeListener(this);
        final JLabel title = new JLabel(HighScoreViewModel.TITLE_LABEL);

        title.setAlignmentX(Component.LEFT_ALIGNMENT);


        final JPanel buttons = new JPanel();
        close = new JButton(HighScoreViewModel.CLOSE_BUTTON_LABEL);
        buttons.add(close);
        buttons.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        close.addActionListener(this);

        this.add(currentScoreLabel);
        this.add(title);
        this.add(highScoreLabel);
        this.add(buttons);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }

    public void propertyChange(PropertyChangeEvent evt) {

    }

    public String getViewName() {
        return viewName;
    }

    public void setHighScoreController(HighScoreController controller) {
        this.highScoreController = controller;
    }
}
