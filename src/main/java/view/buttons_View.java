package view;

import interface_adapter.buttons_viewModel;
import interface_adapter.buttons_State;
import interface_adapter.buttons_controller;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class buttons_View extends JFrame implements ActionListener {
    private JButton feed;
    private JButton clean;
    private JButton water ;
    private JButton play ;
    private buttons_controller buttons_controller;
    private buttons_viewModel buttonsViewModel;
    private ImageIcon feed_image = new ImageIcon();
    private ImageIcon clean_image = new ImageIcon();
    private ImageIcon water_image = new ImageIcon() ;
    private ImageIcon play_image = new ImageIcon();

    buttons_View(buttons_viewModel buttonsViewModel) {
        this.buttonsViewModel = buttonsViewModel;
        this.buttonsViewModel.firePropertyChange();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500,500);
        this.setVisible(true);


        final JPanel buttons = new JPanel();
        buttons.setBounds(0, 0, 500, 500);
        feed = new JButton("Feed");
        clean = new JButton("Clean");
        water = new JButton("Water");
        play = new JButton("Play");

        feed.setBounds(200,100,100,50);
        clean.setBounds(200,100,100,50);
        water.setBounds(200,100,100,50);
        play.setBounds(200,100,100,50);

        buttons.add(feed);
        buttons.add(clean);
        buttons.add(water);
        buttons.add(play);
        this.add(buttons);
        }
    @Override
    public void actionPerformed(ActionEvent e) {
        final buttons_State buttonsState = buttonsViewModel.getState();

        if (e.getSource().equals(feed)) {
            buttons_controller.FeedClicked(buttonsState.getPet());

        if (e.getSource() == clean) {
            buttons_controller.CleanClicked(buttonsState.getPet());
        }
        }
        if (e.getSource() == water) {
            buttons_controller.WaterClicked(buttonsState.getPet());
        }

        if (e.getSource() == play) {
            buttons_controller.PlayClicked(buttonsState.getPet());
        }
    }

    }

