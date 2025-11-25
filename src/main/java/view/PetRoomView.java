package view;

import interface_adapter.PetRoom.PetRoomController;
import interface_adapter.PetRoom.PetRoomViewModel;
import interface_adapter.PetRoom.PetRoomState;
import interface_adapter.buttons.buttons_controller;
import interface_adapter.buttons.buttons_viewModel;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class PetRoomView extends JPanel implements PropertyChangeListener{
    private final String viewName = "pet room";
    private final PetRoomViewModel petRoomViewModel;
    private PetRoomController petRoomController;
    private buttons_controller buttonsController;

    private Image petRoomImage ;
    private final JProgressBar foodbar;
    private final JProgressBar waterbar;
    private final JProgressBar happinessbar;
    private final JProgressBar cleanlinessbar;
    private final Timer timer;
    private int elapsedSeconds = 120;
    private final JLabel timerLabel = new JLabel("Time: 0s");

    //temp buttons
    private buttons_View buttonsView;
    private buttons_viewModel buttonsViewModel;

    private Timer backgroundResetTimer;


    public PetRoomView(PetRoomViewModel petRoomViewModel,  buttons_viewModel buttonsViewModel) {
        this.petRoomViewModel = petRoomViewModel;
        this.petRoomViewModel.addPropertyChangeListener(this);
        petRoomImage = loadBackground("dog_room_basic.jpg");


        foodbar = new JProgressBar(0, 100);
        waterbar = new JProgressBar(0, 100);
        cleanlinessbar = new JProgressBar(0, 100);
        happinessbar = new JProgressBar(0, 100);

        buttonsView = new buttons_View(buttonsViewModel);

        foodbar.setValue(100);
        waterbar.setValue(100);
        cleanlinessbar.setValue(100);
        happinessbar.setValue(100);

//        //temp buttons
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.add(feed);
//        buttonPanel.add(clean);
//        buttonPanel.add(water);
//        buttonPanel.add(play);
//        feed.addActionListener(e -> {
//            if (petRoomController != null && buttonsController != null) {
//                petRoomController.execute("feed");
//                //buttonsController.FeedClicked();
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
//

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
        add(buttonsView, BorderLayout.SOUTH);


    }
    private void updateProgressBars() {
        PetRoomState state = petRoomViewModel.getState();
        foodbar.setValue(state.getFood());
        waterbar.setValue(state.getWater());
        cleanlinessbar.setValue(state.getCleanliness());
        happinessbar.setValue(state.getHappiness());
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

    public void setButtonsView(buttons_View buttonsView) {
        this.buttonsView = buttonsView;
    }

    public buttons_View getButtonsView() {
        return buttonsView;
    }

    public String getViewName(){return viewName;}
}


