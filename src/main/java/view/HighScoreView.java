package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.high_score.HighScoreController;
import interface_adapter.high_score.HighScoreState;
import interface_adapter.high_score.HighScoreViewModel;

public class HighScoreView extends JPanel implements ActionListener, PropertyChangeListener {
    private HighScoreViewModel highScoreViewModel;
    private HighScoreController highScoreController;

    private final String viewName = "High Scores";
    private final JLabel highScoreLabel = new JLabel();
    private final JLabel currentScoreLabel = new JLabel();
    private final JLabel cannotGoBackToTitleLabel = new JLabel();

    private final JButton close;
    private final JButton checkHighScoresButton;
    private final JButton titleScreenButton;

    private int currentScore = -1;

    public HighScoreView(HighScoreViewModel highScoreViewModel) {
        this.highScoreViewModel = highScoreViewModel;
        highScoreViewModel.addPropertyChangeListener(this);

        // title
        final JLabel title = new JLabel(HighScoreViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        // buttons
        final JPanel buttons = new JPanel();
        close = new JButton(HighScoreViewModel.CLOSE_BUTTON_LABEL);
        close.addActionListener(this);
        checkHighScoresButton = new JButton("Check high scores");

        titleScreenButton = new JButton("Back to title screen");
        // add buttons
        buttons.add(checkHighScoresButton);
        buttons.add(close);
        buttons.add(titleScreenButton);

        checkHighScoresButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        if (event.getSource().equals(checkHighScoresButton)) {
                            final HighScoreState currentState = highScoreViewModel.getState();
                            currentState.setCurrentScore(currentScore);
                            if (highScoreController != null) {
                                highScoreController.execute(currentState.getCurrentScore(), false);
                            }
                            if (currentScore == -1) {
                                currentScoreLabel.setText("No Current Score");
                            }
                            else {
                                currentScoreLabel.setText("Current Score: " + currentState.getCurrentScore());
                            }
                            highScoreLabel.setText(currentState.getHighScoreList().printTopTen());
                        }
                    }
                }
        );

        titleScreenButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    if (currentScore == -1) {
                        highScoreController.switchToTitleView();
                    }
                    else {
                        cannotGoBackToTitleLabel.setText(
                                "You cannot go back to title screen after the game is complete.");
                    }
                }
            }
        );

        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(currentScoreLabel);
        this.add(title);
        this.add(highScoreLabel);
        this.add(buttons);
        this.add(cannotGoBackToTitleLabel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (currentScore != -1) {
            final HighScoreState currentState = highScoreViewModel.getState();
            currentState.setCurrentScore(currentScore);
            highScoreController.execute(
                    currentState.getCurrentScore(), true
            );
            currentScoreLabel.setText("Current Score: " + currentState.getCurrentScore());
            highScoreLabel.setText(currentState.getHighScoreList().printTopTen());
        }
        System.exit(0);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            final HighScoreState highScoreState = (HighScoreState) evt.getNewValue();
            final int changeCurrentScore = highScoreState.getCurrentScore();
            System.out.println("Current Score: " + changeCurrentScore);
            currentScoreLabel.setText("Current Score: " + changeCurrentScore);
            this.currentScore = highScoreState.getCurrentScore();
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setHighScoreController(HighScoreController controller) {
        this.highScoreController = controller;
    }
}
