package view;

import interface_adapter.high_score.HighScoreViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HighScoreView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "High Scores";

    private final JButton closeView = new JButton("Close");
    private HighScoreViewModel highScoreViewModel;

    // private controller...

    private final JButton close;
    public HighScoreView(HighScoreViewModel highScoreViewModel) {
        this.highScoreViewModel = highScoreViewModel;
        highScoreViewModel.addPropertyChangeListener(this);
        final JLabel title = new JLabel(HighScoreViewModel.TITLE_LABEL);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        close = new JButton(HighScoreViewModel.CLOSE_BUTTON_LABEL);
        buttons.add(close);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void propertyChange(PropertyChangeEvent evt) {

    }
}
