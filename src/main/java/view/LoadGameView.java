package view;

import interface_adapter.load_game.LoadGameController;
import interface_adapter.load_game.LoadGameViewModel;
import interface_adapter.load_game.LoadGameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoadGameView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "load game";
    private final LoadGameViewModel loadGameViewModel;

    private final JButton loadYes;
    private final JButton loadNo;

    private LoadGameController controller = null;

//    public static void main(String[] args) {
//
//        LoadGameViewModel view  = new LoadGameViewModel();
//        SwingUtilities.invokeLater(() -> new LoadGameView(view));
//
//    }


    public LoadGameView(LoadGameViewModel loadGameViewModel) {
        JFrame frame = new JFrame("test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

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
        loadYes = new JButton(LoadGameViewModel.YES_BUTTON_LABEL);
        loadYes.setPreferredSize(new Dimension(100, 30));
        buttons.add(loadYes);
        loadNo = new JButton(LoadGameViewModel.NO_BUTTON_LABEL);
        loadNo.setPreferredSize(new Dimension(100, 30));
        buttons.add(loadNo);

        loadYes.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(loadYes)) {
                            final LoadGameState currentState = loadGameViewModel.getState();

                            controller.execute(
                                    currentState.getSaveDataExist()
                            );
                        }
                    }
                }
        );

        loadNo.addActionListener(
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
        final LoadGameState currentState = (LoadGameState) evt.getNewValue();
        if (!currentState.getSaveDataExist()) {
            JOptionPane.showMessageDialog(this, "No save data exist.");
        }
    }

    public String getViewName() {return viewName;}

    public void setLoadGameController(LoadGameController controller) {this.controller = controller;}






}
