package view;

import interface_adapter.high_score.HighScoreController;
import interface_adapter.high_score.HighScoreState;
import interface_adapter.high_score.HighScoreViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HighScoreView extends JPanel implements ActionListener, PropertyChangeListener {

    private static final int INVALID_SCORE = -1;
    private static final String CURRENT_SCORE = "Current Score: ";

    private HighScoreViewModel highScoreViewModel;
    private HighScoreController highScoreController = null;

    private final String viewName = "High Scores";
    private final JLabel highScoreLabel = new JLabel();
    private final JLabel currentScoreLabel = new JLabel();
    private final JLabel cannotGoBackToTitleLabel = new JLabel();

    private final JButton close;
    private final JButton checkHighScoresButton;
    private final JButton titleScreenButton;

    private int currentScore = INVALID_SCORE;

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
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(checkHighScoresButton)) {
                            final HighScoreState currentState = highScoreViewModel.getState();
                            currentState.setCurrentScore(currentScore);
                            if (highScoreController != null) {
                                highScoreController.execute(currentState.getCurrentScore(), false);
                            }
                            if (currentScore == INVALID_SCORE) {
                                currentScoreLabel.setText("No Current Score");
                            }
                            else {
                                currentScoreLabel.setText(CURRENT_SCORE + currentState.getCurrentScore());
                            }
                            highScoreLabel.setText(currentState.getHighScoreList().printTopTen());
                        }
                    }
                }
        );

        titleScreenButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    if (currentScore == INVALID_SCORE) {
                        highScoreController.switchToTitleView();
                    }
                    else {
                        cannotGoBackToTitleLabel.setText(
                                "You cannot go back to title screen after the game is complete.");
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
            currentScoreLabel.setText(CURRENT_SCORE + currentState.getCurrentScore());
            highScoreLabel.setText(currentState.getHighScoreList().printTopTen());
        }
        System.exit(0);

    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            final HighScoreState highScoreState = (HighScoreState) evt.getNewValue();
            final int changeCurrentScore = highScoreState.getCurrentScore();
            System.out.println(CURRENT_SCORE + changeCurrentScore);
            currentScoreLabel.setText(CURRENT_SCORE + changeCurrentScore);
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
