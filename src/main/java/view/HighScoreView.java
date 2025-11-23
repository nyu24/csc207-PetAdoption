package view;

import data_access.FileHighScoreDataAccessObject;
import interface_adapter.high_score.HighScoreViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class HighScoreView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "High Scores";

    private final JButton closeView = new JButton("Close");
    private HighScoreViewModel highScoreViewModel;
    private final JLabel highScoreLabel;


    // Temporary until we have an appbuilder class or similar
    {
        try {
            highScoreLabel = new JLabel(new FileHighScoreDataAccessObject("src/test/java/high_scores.csv").getAsString());
            System.out.println(new FileHighScoreDataAccessObject("src/test/java/high_scores.csv").getAsString());
            highScoreLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // private controller...

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

        this.add(title);
        this.add(highScoreLabel);
        this.add(buttons);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "I have no idea when this will appear.");
    }

    public void propertyChange(PropertyChangeEvent evt) {

    }
}
