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
    private HighScoreViewModel highScoreViewModel;
    private HighScoreController highScoreController = null;

    private final String viewName = "High Scores";
    private final JLabel highScoreLabel = new JLabel();
    private final JLabel currentScoreLabel = new JLabel();


    private final JButton close;
    private final JButton checkHighScoresButton;

    private int currentScore = 0;

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
        // add buttons
        buttons.add(checkHighScoresButton);
        buttons.add(close);
        checkHighScoresButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(checkHighScoresButton)) {
                            final HighScoreState currentState = highScoreViewModel.getState();
                            currentState.setCurrentScore(currentScore);
                            if (highScoreController != null) {
                                highScoreController.execute(currentState.getCurrentScore(), false);
                            }
                            currentScoreLabel.setText("Current Score: " + currentState.getCurrentScore());
                            highScoreLabel.setText(currentState.getHighScoreList().printTopTen());
                        }
                    }
                }
        );


        buttons.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(currentScoreLabel);
        this.add(title);
        this.add(highScoreLabel);
        this.add(buttons);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        final HighScoreState currentState = highScoreViewModel.getState();
        currentState.setCurrentScore(currentScore);
        highScoreController.execute(
                currentState.getCurrentScore(), true
        );
        currentScoreLabel.setText("Current Score: " + currentState.getCurrentScore());
        highScoreLabel.setText(currentState.getHighScoreList().printTopTen());
        System.exit(0);

    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            HighScoreState highScoreState = (HighScoreState) evt.getNewValue();
            int currentScore = highScoreState.getCurrentScore();
            System.out.println("Current Score: " + currentScore); // print to console for testing
            currentScoreLabel.setText("Current Score: " + currentScore);
            this.currentScore = highScoreState.getCurrentScore();
//            highScoreLabel.setText(highScoreState.getHighScoreList().printTopTen());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setHighScoreController(HighScoreController controller) {
        this.highScoreController = controller;
    }
}
