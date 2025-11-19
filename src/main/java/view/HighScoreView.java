package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HighScoreView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "High Scores";

    private final JButton closeView = new JButton("Close");
    public HighScoreView() {

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void propertyChange(PropertyChangeEvent evt) {

    }
}
