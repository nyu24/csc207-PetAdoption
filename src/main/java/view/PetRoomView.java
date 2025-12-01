package view;

import interface_adapter.PetRoom.PetRoomController;
import interface_adapter.PetRoom.PetRoomViewModel;
import interface_adapter.PetRoom.PetRoomState;
import interface_adapter.buttons.ButtonsState;
import interface_adapter.buttons.ButtonsController;
import interface_adapter.buttons.ButtonsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;
import java.net.URL;
import java.util.Objects;

public class PetRoomView extends JPanel implements PropertyChangeListener, ActionListener {
    private final String viewName = "pet room";
    private final PetRoomViewModel petRoomViewModel;
    private PetRoomController petRoomController;
    private ButtonsController buttonsController;
    private final ButtonsViewModel buttonsViewModel;

    private Image petRoomImage ;
    private final JProgressBar foodbar;
    private final JProgressBar waterbar;
    private final JProgressBar happinessbar;
    private final JProgressBar cleanlinessbar;
    private final Timer timer;
    private int elapsedSeconds = 1000;
    private final JLabel timerLabel = new JLabel("Time: 0s");

    //temp buttons
    private JButton feed;
    private JButton clean;
    private JButton water ;
    private JButton play ;
    private ButtonsController ButtonsController;
    private ImageIcon feed_image;
    private ImageIcon clean_image;
    private ImageIcon water_image;
    private ImageIcon play_image;

    private Timer backgroundResetTimer;


    public PetRoomView(PetRoomViewModel petRoomViewModel, ButtonsViewModel buttonsViewModel) {
        this.petRoomViewModel = petRoomViewModel;
        this.petRoomViewModel.addPropertyChangeListener(this);
        this.buttonsViewModel = buttonsViewModel;
        petRoomImage = loadBackground("Dog_room_basic.jpg");

       // this.petRoomViewModel.addPropertyChangeListener(this);

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
        feed.addActionListener(this);
        clean.addActionListener(this);
        water.addActionListener(this);
        play.addActionListener(this);

        buttonPanel.add(clean);
        buttonPanel.add(water);
        buttonPanel.add(play);
        buttonPanel.add(feed);

//
//        feed.addActionListener(e -> {
//            if (petRoomController != null && buttonsController != null) {
//                petRoomController.execute("feed");
//                buttonsController.FeedClicked();
//                switchBackgroundTemp("Dog_room_food.jpg");
//
//            }
//        });
//        water.addActionListener(e -> {
//            if (petRoomController != null && buttonsController != null) {
//                petRoomController.execute("water");
//                buttonsController.WaterClicked();
//                switchBackgroundTemp("Dog_room_water.jpg");
//            }
//        });
//
//        clean.addActionListener(e -> {
//            if (petRoomController != null && buttonsController != null) {
//                petRoomController.execute("clean");
//                buttonsController.CleanClicked();
//                petRoomImage = loadBackground("Dog_room_clean.jpg");
//                repaint();
//
//            }
//        });
//
//        play.addActionListener(e -> {
//            if (petRoomController != null && buttonsController != null) {
//                petRoomController.execute("play");
//                buttonsController.PlayClicked();
//                petRoomImage = loadBackground("Dog_room_water.jpg");
//                repaint();
//
//            }
//        });


        timer = new Timer(1000, e -> {
            elapsedSeconds--;
            if (elapsedSeconds <= 0) {
                System.out.println(elapsedSeconds);

                Map<String, Integer> stats = new HashMap<>();

                PetRoomState petRoomState = petRoomViewModel.getState();

                stats.put("Hunger", petRoomState.getFood());
                stats.put("Thirst", petRoomState.getWater());
                stats.put("Cleanliness", petRoomState.getCleanliness());
                stats.put("Happiness", petRoomState.getHappiness());

//                petRoomController.switchToVetView();
                petRoomController.sendPetData(stats, petRoomState.getScore(),  petRoomState.getCurrPet());
                  ((Timer) e.getSource()).stop();
                  return;
//                    timer.stop();
//                petRoomState.setTimer(elapsedSeconds);
//
//                petRoomViewModel.firePropertyChange("timerExpired");
            }
            else {
                if (petRoomController!= null) {
                    PetRoomState currentState =  petRoomViewModel.getState();
                    petRoomController.execute("tick", currentState.getScore(), currentState.getPetType());
                }
            timerLabel.setText("Time: " + elapsedSeconds);}

//            else{
//                timerLabel.setText("Time's Up!");
//            }
        });
//        timer.start();

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
            petRoomImage = loadBackground("Dog_room_basic.jpg");
            repaint();
            backgroundResetTimer.stop();
        });
        //backgroundResetTimer.setRepeats(false);
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
        System.out.println("=== PROPERTY CHANGE FIRED ==="); // DEBUG
        System.out.println("Property name: " + evt.getPropertyName());
        if ("value_update".equals(evt.getPropertyName())) {
            PetRoomState petRoomState = petRoomViewModel.getState();

            System.out.println("Food: " + petRoomState.getFood());        // ADD THIS
            System.out.println("Water: " + petRoomState.getWater());      // ADD THIS
            System.out.println("Clean: " + petRoomState.getCleanliness()); // ADD THIS
            System.out.println("Happy: " + petRoomState.getHappiness());
            System.out.println(petRoomState.getRoomType()); // ADD THIS


            foodbar.setValue(petRoomState.getFood());
            waterbar.setValue(petRoomState.getWater());
            happinessbar.setValue(petRoomState.getHappiness());
            cleanlinessbar.setValue(petRoomState.getCleanliness());
            //switchBackgroundTemp(petRoomState.getPetType());
            repaint();
        }

        else if ("timer_start".equals(evt.getPropertyName())) {
            elapsedSeconds = 5;
            timerLabel.setText("Time: " + elapsedSeconds);
            timer.start();
        }


    }
    public void setPetRoomController(PetRoomController petRoomController) {
        this.petRoomController = petRoomController;
    }
    public void setButtonsController(ButtonsController buttonsController) {
        this.buttonsController = buttonsController;
    }
    public String getViewName(){return viewName;}

    @Override
    public void actionPerformed(ActionEvent e) {

        if (petRoomController == null) {
            System.out.println("ERROR: Controller not set!");
            return;
        }

        PetRoomState petRoomState = petRoomViewModel.getState();
        ButtonsState buttonsState =  buttonsViewModel.getState();
        if (e.getSource().equals(feed)) {
            System.out.println("Feed button clicked!");
            buttonsController.feedClicked();
            petRoomController.execute("feed", petRoomState.getScore(), petRoomState.getPetType());
            switchBackgroundTemp(petRoomState.getRoomType());
            foodbar.setValue((int) buttonsState.getHunger());

        }


        if (e.getSource() == clean) {
            buttonsController.cleanClicked();
            petRoomController.execute("clean", petRoomState.getScore(), petRoomState.getPetType());
            switchBackgroundTemp(petRoomState.getRoomType());
            cleanlinessbar.setValue((int) buttonsState.getCleanliness());
        }

        if (e.getSource() == water) {
            buttonsController.waterClicked();
            petRoomController.execute("water", petRoomState.getScore(), petRoomState.getPetType());
            switchBackgroundTemp(petRoomState.getRoomType());
            waterbar.setValue((int) buttonsState.getThirst());
        }

        if (e.getSource() == play) {
            buttonsController.playClicked();
            petRoomController.execute("play", petRoomState.getScore(), petRoomState.getPetType());
            switchBackgroundTemp(petRoomState.getRoomType());
            happinessbar.setValue((int) buttonsState.getHapiness()); // i basiczlly just added these lines idk how to get it working tho
        }
    }

}

