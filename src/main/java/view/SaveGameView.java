package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.*;

import interface_adapter.save_game.SaveGameController;
import interface_adapter.save_game.SaveGameState;
import interface_adapter.save_game.SaveGameViewModel;

/**
 * The View for when the user is trying to save a save file.
 */
public class SaveGameView extends AbstractView {
    private final String viewName = "save game";
    private SaveGameViewModel saveGameViewModel;

    private JButton saveYes;
    private JButton saveNo;

    private JLabel saveLabel;
    private JLabel warningLabel;
    private JPanel buttons;

    private SaveGameController controller;

    public SaveGameView(SaveGameViewModel saveGameViewModel) {
        this.saveGameViewModel = saveGameViewModel;
        this.saveGameViewModel.addPropertyChangeListener(this);
    }

    @Override
    protected void setupLayout() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    @Override
    protected void createComponents() {
        saveLabel = new JLabel(SaveGameViewModel.SAVE_LABEL);
        final Font saveFont = new Font(Font.MONOSPACED, Font.BOLD, 24);
        saveLabel.setFont(saveFont);
        saveLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        warningLabel = new JLabel(SaveGameViewModel.WARNING_LABEL);
        final Font warningFont = new Font(Font.MONOSPACED, Font.BOLD, 12);
        warningLabel.setFont(warningFont);
        warningLabel.setForeground(Color.RED);
        warningLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttons = new JPanel();
        saveYes = createButton(SaveGameViewModel.YES_BUTTON_LABEL);
        saveNo = createButton(SaveGameViewModel.NO_BUTTON_LABEL);

        buttons.add(saveYes);
        buttons.add(saveNo);
    }

    @Override
    protected void addComponents() {
        final int verticalGap = 10;

        this.add(Box.createVerticalGlue());
        this.add(saveLabel);
        this.add(Box.createVerticalStrut(verticalGap));
        this.add(warningLabel);
        this.add(Box.createVerticalStrut(verticalGap));
        this.add(buttons);
        this.add(Box.createVerticalGlue());
    }

    @Override
    protected void addListeners() {
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
    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this,
                "You are not supposed to see this.");
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
