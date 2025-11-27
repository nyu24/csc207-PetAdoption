package view;

import interface_adapter.PetRoom.PetRoomController;
import interface_adapter.PetRoom.PetRoomViewModel;
import interface_adapter.PetRoom.PetRoomState;
import interface_adapter.buttons.buttons_State;
import interface_adapter.buttons.buttons_controller;
import interface_adapter.buttons.buttons_viewModel;
import interface_adapter.buttons.buttons_State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.Objects;

public class PetRoomView extends JPanel implements PropertyChangeListener, ActionListener {
    private final String viewName = "pet room";
    private final PetRoomViewModel petRoomViewModel;
    private PetRoomController petRoomController;
    private buttons_controller buttonsController;
    private final buttons_viewModel buttonsViewModel;

    private Image petRoomImage ;
    private final JProgressBar foodbar;
    private final JProgressBar waterbar;
    private final JProgressBar happinessbar;
    private final JProgressBar cleanlinessbar;
    private final Timer timer;
    private int elapsedSeconds = 120;
    private final JLabel timerLabel = new JLabel("Time: 0s");

    //temp buttons
    private JButton feed;
    private JButton clean;
    private JButton water ;
    private JButton play ;
    private buttons_controller buttons_controller;
    private ImageIcon feed_image;
    private ImageIcon clean_image;
    private ImageIcon water_image;
    private ImageIcon play_image;

    private Timer backgroundResetTimer;


    public PetRoomView(PetRoomViewModel petRoomViewModel, buttons_viewModel buttonsViewModel) {
        this.petRoomViewModel = petRoomViewModel;
        this.petRoomViewModel.addPropertyChangeListener(this);
        this.buttonsViewModel = buttonsViewModel;
        petRoomImage = loadBackground("dog_room_basic.jpg");


        foodbar = new JProgressBar(0, 100);
        waterbar = new JProgressBar(0, 100);
        cleanlinessbar = new JProgressBar(0, 100);
        happinessbar = new JProgressBar(0, 100);

        foodbar.setValue(100);
        waterbar.setValue(100);
        cleanlinessbar.setValue(100);
        happinessbar.setValue(100);
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
        URL play_imageURL = getClass().getResource("/images_buttons/—Pngtree—toy ball water polo round_7670359.png");
        ImageIcon imageIcon_play = new ImageIcon(play_imageURL);
        Image scaleImage_play = imageIcon_play.getImage().getScaledInstance(45,45,Image.SCALE_DEFAULT);
        play_image = new ImageIcon(scaleImage_play);

        //temp buttons
        final JPanel buttonPanel = new JPanel();
        feed = new JButton(feed_image);
        clean = new JButton(clean_image);
        water = new JButton(water_image);
        play = new JButton(play_image);

        setLayout(new FlowLayout());
        buttonPanel.add(clean);
        buttonPanel.add(water);
        buttonPanel.add(play);
        buttonPanel.add(feed);
        this.add(buttonPanel);


//        feed.addActionListener(e -> {
//            if (petRoomController != null && buttonsController != null) {
//                petRoomController.execute("feed");
//                buttonsController.FeedClicked();
//                switchBackgroundTemp("dog_room_food.jpg");
//
//            }
//        });
//        water.addActionListener(e -> {
//            if (petRoomController != null && buttonsController != null) {
//                petRoomController.execute("water");
//                buttonsController.WaterClicked();
//                switchBackgroundTemp("dog_room_water.jpg");
//            }
//        });
//
//        clean.addActionListener(e -> {
//            if (petRoomController != null && buttonsController != null) {
//                petRoomController.execute("clean");
//                buttonsController.CleanClicked();
//                petRoomImage = loadBackground("dog_room_clean.jpg");
//                repaint();
//
//            }
//        });
//
//        play.addActionListener(e -> {
//            if (petRoomController != null && buttonsController != null) {
//                petRoomController.execute("play");
//                buttonsController.PlayClicked();
//                petRoomImage = loadBackground("dog_room_water.jpg");
//                repaint();
//
//            }
//        });


        timer = new Timer(1000, e -> {
            if (elapsedSeconds != 0){
                elapsedSeconds--;
                if (petRoomController!= null) {
                    petRoomController.execute("tick");
                }
            timerLabel.setText("Time: " + elapsedSeconds);}

            else{
                timerLabel.setText("Time's Up!");
            }
        });
        timer.start();

        setLayout(new BorderLayout());
        JPanel meterPanel= new JPanel();
        meterPanel.setLayout(new GridLayout(4, 1));
        meterPanel.add(new JLabel("food"));
        meterPanel.add(foodbar);
        meterPanel.add(new JLabel("water"));
        meterPanel.add(waterbar);
        meterPanel.add(new JLabel("cleanliness"));
        meterPanel.add(cleanlinessbar);
        meterPanel.add(new JLabel("happiness"));
        meterPanel.add(happinessbar);
        add(meterPanel, BorderLayout.NORTH);
        add(timerLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);


    }
    private void switchBackgroundTemp(String imageName){
        if (backgroundResetTimer != null && backgroundResetTimer.isRunning()) {
            backgroundResetTimer.stop();
        }
        petRoomImage = loadBackground(imageName);
        repaint();
        backgroundResetTimer = new Timer(2000, e -> {
            petRoomImage = loadBackground("dog_room_basic.jpg");
            repaint();
            backgroundResetTimer.stop();
        });
        backgroundResetTimer.setRepeats(false);
        backgroundResetTimer.start();
    }
    public Image loadBackground(String path){
        return new ImageIcon(Objects.requireNonNull(getClass().getResource("/" + path))).getImage();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(petRoomImage, 0, 0, getWidth(), getHeight(), this);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            PetRoomState petRoomState = petRoomViewModel.getState();
            foodbar.setValue(petRoomState.getFood());
            waterbar.setValue(petRoomState.getWater());
            happinessbar.setValue(petRoomState.getHappiness());
            cleanlinessbar.setValue(petRoomState.getCleanliness());
            repaint();
        }

    }
    public void setPetRoomController(PetRoomController petRoomController) {
        this.petRoomController = petRoomController;
    }
    public void setButtonsController(buttons_controller buttonsController) {
        this.buttonsController = buttonsController;
    }
    public String getViewName(){return viewName;}

    @Override
    public void actionPerformed(ActionEvent e) {

        PetRoomState petRoomState = petRoomViewModel.getState();
        buttons_State buttonsState =  buttonsViewModel.getState();
        if (e.getSource().equals(feed)) {
            buttons_controller.FeedClicked();
            petRoomController.execute("feed");
            switchBackgroundTemp("dog_room_food.jpg");
            foodbar.setValue((int) buttonsState.getHunger());

        }


        if (e.getSource() == clean) {
            buttons_controller.CleanClicked();
            petRoomController.execute("clean");
            switchBackgroundTemp("dog_room_clean.jpg");
            cleanlinessbar.setValue((int) buttonsState.getCleanliness());
        }

        if (e.getSource() == water) {
            buttons_controller.WaterClicked();
            petRoomController.execute("water");
            switchBackgroundTemp("dog_room_water.jpg");
            waterbar.setValue((int) buttonsState.getThirst());
        }

        if (e.getSource() == play) {
            buttons_controller.PlayClicked();
            petRoomController.execute("play");
            switchBackgroundTemp("dog_room_basic.jpg");
            happinessbar.setValue((int) buttonsState.getHapiness()); // i basiczlly just added these lines
        }
    }

}

