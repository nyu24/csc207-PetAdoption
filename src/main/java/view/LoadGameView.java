package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.*;

import interface_adapter.load_game.LoadGameController;
import interface_adapter.load_game.LoadGameState;
import interface_adapter.load_game.LoadGameViewModel;

/**
 * The View for when the user is trying to load a save file.
 */
public class LoadGameView extends AbstractView {
    private final String viewName = "load game";
    private LoadGameViewModel loadGameViewModel;

    private JButton loadYes;
    private JButton loadNo;

    private JLabel loadLabel;
    private JLabel warningLabel;
    private JPanel buttons;

    private LoadGameController controller;

    public LoadGameView(LoadGameViewModel loadGameViewModel) {

        this.loadGameViewModel = loadGameViewModel;
        this.loadGameViewModel.addPropertyChangeListener(this);

    }

    @Override
    protected void setupLayout() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    @Override
    protected void createComponents() {
        loadLabel = new JLabel(LoadGameViewModel.LOAD_LABEL);
        final Font loadFont = new Font(Font.MONOSPACED, Font.BOLD, 24);
        loadLabel.setFont(loadFont);
        loadLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        warningLabel = new JLabel(LoadGameViewModel.WARNING_LABEL);
        final Font warningFont = new Font(Font.MONOSPACED, Font.BOLD, 12);
        warningLabel.setFont(warningFont);
        warningLabel.setForeground(Color.RED);
        warningLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttons = new JPanel();
        loadYes = createButton(LoadGameViewModel.YES_BUTTON_LABEL);
        loadNo = createButton(LoadGameViewModel.NO_BUTTON_LABEL);

        buttons.add(loadYes);
        buttons.add(loadNo);
    }

    @Override
    protected void addComponents() {
        final int verticalGap = 10;

        this.add(Box.createVerticalGlue());
        this.add(loadLabel);
        this.add(Box.createVerticalStrut(verticalGap));
        this.add(warningLabel);
        this.add(Box.createVerticalStrut(verticalGap));
        this.add(buttons);
        this.add(Box.createVerticalGlue());
    }

    @Override
    protected void addListeners() {
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
