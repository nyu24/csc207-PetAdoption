package view;

import interface_adapter.save_game.SaveGameController;
import interface_adapter.save_game.SaveGameViewModel;
import interface_adapter.save_game.SaveGameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Objects;

public class SaveGameView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "save game";
    private final SaveGameViewModel saveGameViewModel;

    private final JButton saveYes;
    private final JButton saveNo;

    private SaveGameController controller = null;

    public SaveGameView(SaveGameViewModel saveGameViewModel) {
        this.saveGameViewModel = saveGameViewModel;
        this.addPropertyChangeListener(this);

        final JLabel saveLabel = new JLabel("Save game?");
        saveLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        final JLabel warningLabel = new JLabel("Warning: Will override current save if it exists.");
        warningLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        saveYes = new JButton("Yes");
        buttons.add(saveYes);
        saveNo = new JButton("No");
        buttons.add(saveNo);

        saveYes.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(saveYes)) {
                            final SaveGameState currentState = saveGameViewModel.getState();

                            controller.execute(
                                    currentState.getTimeLeft(),
                                    currentState.getCurrentPet()
                            );
                        }
                    }
                }
        );

        saveNo.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {controller.switchToPetRoomView();}
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(saveLabel);
        this.add(warningLabel);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) { JOptionPane.showMessageDialog(this,
            "You are not supposed to this.");}

    public void propertyChange(PropertyChangeEvent evt) {
        final SaveGameState currentState = (SaveGameState) evt.getNewValue();
        if (currentState.getPetError() != null) {
            JOptionPane.showMessageDialog(this, currentState.getPetError());
        }
    }

    public String getViewName() {return viewName;}

    public void setSaveGameController(SaveGameController controller) {this.controller = controller;}






}
