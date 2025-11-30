package view;

import interface_adapter.title.TitleState;
import interface_adapter.title.TitleViewModel;
import interface_adapter.title.SwitchViewController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.sound.sampled.*;
import java.io.IOException;
import java.io.File;

public class TitleView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "title";
    private final TitleViewModel titleViewModel;

    private final JButton start;
    private final JButton load;
    private final JButton scores;

    private SwitchViewController controller = null;

    public TitleView(TitleViewModel titleViewModel) {
        this.titleViewModel = titleViewModel;
        titleViewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(240, 248, 255));
        this.setBorder(new EmptyBorder(20, 20, 20, 20));

        final JLabel title = new JLabel("Pet Adoption Simulator");
        final Font titleFont = new Font("Georgia", Font.BOLD, 30);
        title.setFont(titleFont);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(new Color(0, 0, 25));

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

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(Box.createVerticalStrut(40));
        this.add(start);
        this.add(Box.createVerticalStrut(20));
        this.add(load);
        this.add(Box.createVerticalStrut(20));
        this.add(scores);
        this.add(Box.createVerticalGlue());
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);

        button.setFont(new Font("Georgia", Font.PLAIN, 20));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        Dimension dim = new Dimension(250, 60);
        button.setPreferredSize(dim);
        button.setMaximumSize(dim);
        button.setMinimumSize(dim);

        return button;
    }

    private void playSound() {
        try {
            File soundFile = new File("src/main/resources/sounds/click.wav");

            if (!soundFile.exists()) {
                System.out.println("Wrong directory");
                return;
            }

            AudioInputStream audio = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }

    public void actionPerformed(ActionEvent evt) { JOptionPane.showMessageDialog(this,
            "You are not supposed to see this.");}

    public void propertyChange(PropertyChangeEvent evt) {
        final TitleState currentState = (TitleState) evt.getNewValue();
    }

    public String getViewName() { return viewName;}

    public void setSwitchViewController(SwitchViewController switchViewController) {
        this.controller = switchViewController;
    }

}
