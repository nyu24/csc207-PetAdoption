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

//    public static void main(String[] args) {
//
//        SaveGameViewModel view  = new SaveGameViewModel();
//        SwingUtilities.invokeLater(() -> new SaveGameView(view));
//
//    }


    public SaveGameView(SaveGameViewModel saveGameViewModel) {
        JFrame frame = new JFrame("test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

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
        saveYes = new JButton(SaveGameViewModel.YES_BUTTON_LABEL);
        saveYes.setPreferredSize(new Dimension(100, 30));
        buttons.add(saveYes);
        saveNo = new JButton(SaveGameViewModel.NO_BUTTON_LABEL);
        saveNo.setPreferredSize(new Dimension(100, 30));
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

        this.add(Box.createVerticalGlue());
        this.add(saveLabel);
        this.add(Box.createVerticalStrut(10));
        this.add(warningLabel);
        this.add(Box.createVerticalStrut(10));
        this.add(buttons);
        this.add(Box.createVerticalGlue());

//        frame.add(this);
//        frame.setVisible(true);
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
