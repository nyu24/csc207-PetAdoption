package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import interface_adapter.title.SwitchViewController;
import interface_adapter.title.TitleViewModel;

/**
 * The View for when the user just started the game.
 */
public class TitleView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "title";
    private final TitleViewModel titleViewModel;

    private final JButton start;
    private final JButton load;
    private final JButton scores;

    private SwitchViewController controller;

    public TitleView(TitleViewModel titleViewModel) {
        this.titleViewModel = titleViewModel;
        titleViewModel.addPropertyChangeListener(this);

        setViewParameters();

        final JLabel title = new JLabel("Pet Adoption Simulator");
        final Font titleFont = new Font("Georgia", Font.BOLD, 30);
        title.setFont(titleFont);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        final Color titleColor = new Color(0, 0, 25);
        title.setForeground(titleColor);

        start = createButton("Start Game");
        load = createButton("Load Game");
        scores = createButton("High Scores");

        start.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        playSound();
                        controller.switchToSetParamView();
                    }
                }
        );

        load.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        playSound();
                        controller.switchToLoadGameView();
                    }
                }
        );

        scores.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        playSound();
                        controller.switchToHighScoreView();
                    }
                }
        );

        final int verticalGap = 20;
        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(Box.createVerticalStrut(2 * verticalGap));
        this.add(start);
        this.add(Box.createVerticalStrut(verticalGap));
        this.add(load);
        this.add(Box.createVerticalStrut(verticalGap));
        this.add(scores);
        this.add(Box.createVerticalGlue());
    }

    private void setViewParameters() {
        final Color background = new Color(240, 248, 255);
        final EmptyBorder margins = new EmptyBorder(20, 20, 20, 20);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(background);
        this.setBorder(margins);
    }

    private JButton createButton(String text) {
        final int fontSize = 20;
        final JButton button = new JButton(text);

        button.setFont(new Font("Georgia", Font.PLAIN, fontSize));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        final Dimension dim = new Dimension(250, 60);
        button.setPreferredSize(dim);
        button.setMaximumSize(dim);
        button.setMinimumSize(dim);

        return button;
    }

    private void playSound() {
        try {
            final File soundFile = new File("src/main/resources/sounds/click.wav");

            if (!soundFile.exists()) {
                System.out.println("Wrong directory");
            }

            final AudioInputStream audio = AudioSystem.getAudioInputStream(soundFile);
            final Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        }
        catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            throw new RuntimeException(ex);
        }
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
        JOptionPane.showMessageDialog(this,
                "You are not supposed to see this.");
    }

    public String getViewName() {
        return viewName;
    }

    public void setSwitchViewController(SwitchViewController switchViewController) {
        this.controller = switchViewController;
    }

}