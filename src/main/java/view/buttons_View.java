package view;

import interface_adapter.buttons_viewModel;
import interface_adapter.buttons_State;
import interface_adapter.buttons_controller;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.net.URL;


public class buttons_View extends JFrame implements ActionListener {
    private JButton feed;
    private JButton clean;
    private JButton water ;
    private JButton play ;
    private buttons_controller buttons_controller;
    private buttons_viewModel buttonsViewModel;
    private ImageIcon feed_image;
    private ImageIcon clean_image;
    private ImageIcon water_image;
    private ImageIcon play_image;

    buttons_View(buttons_viewModel buttonsViewModel) {
        this.buttonsViewModel = buttonsViewModel;
        this.buttonsViewModel.firePropertyChange();

        //image for the feed button
        URL feed_imageURL = getClass().getResource("/images_buttons/—Pngtree—theres a bone in the_4287031.png");
        ImageIcon imageIcon_feed = new ImageIcon(feed_imageURL);
        Image scaleImage_feed = imageIcon_feed.getImage().getScaledInstance(45,45,Image.SCALE_DEFAULT);
        feed_image = new ImageIcon(scaleImage_feed);

        //image for the clean button
        URL clean_imageURL = getClass().getResource("/images_buttons/sponge-emoji-clipart-md.png");
        ImageIcon imageIcon_clean = new ImageIcon(clean_imageURL);
        Image scaleImage_clean = imageIcon_clean.getImage().getScaledInstance(45,45,Image.SCALE_DEFAULT);
        clean_image = new ImageIcon(scaleImage_clean);

        //image for the water button
        URL water_imageURL = getClass().getResource("/images_buttons/b6410ff0-049c-4f19-a53e-b0b048aadc40.jpg");
        ImageIcon imageIcon_water = new ImageIcon(water_imageURL);
        Image scaleImage_water = imageIcon_water.getImage().getScaledInstance(45,45,Image.SCALE_DEFAULT);
        water_image = new ImageIcon(scaleImage_water);

        //image for the play button
        URL play_imageURL = getClass().getResource("/images_buttons/tennis-emoji-clipart-md.png");
        ImageIcon imageIcon_play = new ImageIcon(play_imageURL);
        Image scaleImage_play = imageIcon_play.getImage().getScaledInstance(45,45,Image.SCALE_DEFAULT);
        play_image = new ImageIcon(scaleImage_play);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setVisible(true);


        final JPanel buttons = new JPanel();
        feed = new JButton(feed_image);
        clean = new JButton(clean_image);
        water = new JButton(water_image);
        play = new JButton(play_image);

        setLayout(new FlowLayout());
        buttons.add(clean);
        buttons.add(water);
        buttons.add(play);
        this.add(feed);
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

