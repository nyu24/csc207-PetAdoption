package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.load_game.LoadGameController;
import interface_adapter.load_game.LoadGameState;
import interface_adapter.load_game.LoadGameViewModel;

/**
 * The View for when the user is trying to load a save file.
 */
public class LoadGameView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "load game";
    private final LoadGameViewModel loadGameViewModel;

    private final JButton loadYes;
    private final JButton loadNo;

    private LoadGameController controller;

    public LoadGameView(LoadGameViewModel loadGameViewModel) {

        this.loadGameViewModel = loadGameViewModel;
        loadGameViewModel.addPropertyChangeListener(this);

        final JLabel saveLabel = new JLabel(LoadGameViewModel.LOAD_LABEL);
        final Font saveFont = new Font(Font.MONOSPACED, Font.BOLD, 24);
        saveLabel.setFont(saveFont);
        saveLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel warningLabel = new JLabel(LoadGameViewModel.WARNING_LABEL);
        final Font warningFont = new Font(Font.MONOSPACED, Font.BOLD, 12);
        warningLabel.setFont(warningFont);
        warningLabel.setForeground(Color.RED);
        warningLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        loadYes = createButton(LoadGameViewModel.YES_BUTTON_LABEL);
        buttons.add(loadYes);
        loadNo = createButton(LoadGameViewModel.YES_BUTTON_LABEL);
        buttons.add(loadNo);

        loadYes.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(loadYes)) {
                            controller.execute();
                        }
                    }
                }
        );

        loadNo.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        controller.switchToTitleScreen();
                    }
                }
        );

        final int verticalGap = 10;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalGlue());
        this.add(saveLabel);
        this.add(Box.createVerticalStrut(verticalGap));
        this.add(warningLabel);
        this.add(Box.createVerticalStrut(verticalGap));
        this.add(buttons);
        this.add(Box.createVerticalGlue());

    }

    private JButton createButton(String text) {
        final JButton button = new JButton(text);
        final int buttonWidth = 100;
        final int buttonHeight = 30;

        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        final Dimension dim = new Dimension(buttonWidth, buttonHeight);
        button.setPreferredSize(dim);
        button.setMaximumSize(dim);
        button.setMinimumSize(dim);

        return button;
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this,
                "You are not supposed to see this.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final LoadGameState currentState = (LoadGameState) evt.getNewValue();
        if (!currentState.getSaveDataExist()) {
            JOptionPane.showMessageDialog(this, "No save data exist.");
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setLoadGameController(LoadGameController loadGameController) {
        this.controller = loadGameController;
    }

}