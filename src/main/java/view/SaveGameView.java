package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.save_game.SaveGameController;
import interface_adapter.save_game.SaveGameState;
import interface_adapter.save_game.SaveGameViewModel;

/**
 * The View for when the user is trying to save a save file.
 */
public class SaveGameView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "save game";
    private final SaveGameViewModel saveGameViewModel;

    private final JButton saveYes;
    private final JButton saveNo;

    private SaveGameController controller;

    public SaveGameView(SaveGameViewModel saveGameViewModel) {

        this.saveGameViewModel = saveGameViewModel;
        saveGameViewModel.addPropertyChangeListener(this);

        final JLabel saveLabel = new JLabel(SaveGameViewModel.SAVE_LABEL);
        final Font saveFont = new Font(Font.MONOSPACED, Font.BOLD, 24);
        saveLabel.setFont(saveFont);
        saveLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel warningLabel = new JLabel(SaveGameViewModel.WARNING_LABEL);
        final Font warningFont = new Font(Font.MONOSPACED, Font.BOLD, 12);
        warningLabel.setFont(warningFont);
        warningLabel.setForeground(Color.RED);
        warningLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        saveYes = createButton(SaveGameViewModel.YES_BUTTON_LABEL);
        buttons.add(saveYes);
        saveNo = createButton(SaveGameViewModel.NO_BUTTON_LABEL);
        buttons.add(saveNo);

        saveYes.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(saveYes)) {
                            final SaveGameState currentState = saveGameViewModel.getState();

                            controller.execute(
                                    currentState.getTimeLeft(),
                                    currentState.getCurrScore(),
                                    currentState.getCurrPet()
                            );
                        }
                    }
                }
        );

        saveNo.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        controller.switchToPetRoomView();
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
            "You are not supposed to this.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SaveGameState currentState = (SaveGameState) evt.getNewValue();
        if (currentState.getPetError() != null) {
            JOptionPane.showMessageDialog(this, currentState.getPetError());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setSaveGameController(SaveGameController saveGameController) {
        this.controller = saveGameController;
    }

}
