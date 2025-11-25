package view;

import interface_adapter.PetRoom.PetRoomController;
import interface_adapter.PetRoom.PetRoomViewModel;
import interface_adapter.PetRoom.PetRoomState;
import interface_adapter.buttons.buttons_controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Objects;

public class PetRoomView extends JPanel implements PropertyChangeListener{
    private final String viewName = "pet room";
    private final PetRoomViewModel petRoomViewModel;
    private PetRoomController petRoomController;

    private Image petRoomImage;
    private final JProgressBar foodbar;
    private final JProgressBar waterbar;
    private final JProgressBar happinessbar;
    private final JProgressBar cleanlinessbar;
    private final Timer timer;
    private int elapsedSeconds = 120;
    private final JLabel timerLabel = new JLabel("Time: 0s");

    //temp buttons
    private final JButton feed = new JButton("feed");
    private final JButton clean = new JButton("clean");
    private final JButton water = new JButton("water");


    public PetRoomView(PetRoomViewModel petRoomViewModel) {
        this.petRoomViewModel = petRoomViewModel;


        foodbar = new JProgressBar(0, 100);
        waterbar = new JProgressBar(0, 100);
        happinessbar = new JProgressBar(0, 100);
        cleanlinessbar = new JProgressBar(0, 100);

        //temp buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(feed);
        buttonPanel.add(clean);
        buttonPanel.add(water);
        feed.addActionListener(e -> {
            if (petRoomController != null) {
                petRoomController.execute("feed");
                petRoomImage = loadBackground("dog_room_food.jpg");
                foodbar.setValue(petRoomViewModel.getState().getFood());

            }
        });
        water.addActionListener(e -> {
            if (petRoomController != null) {
                petRoomController.execute("water");
                petRoomImage = loadBackground("dog_room_water.jpg");
                waterbar.setValue(petRoomViewModel.getState().getWater());
            }
        });

        clean.addActionListener(e -> {
            if (petRoomController != null) {
                petRoomController.execute("clean");
                petRoomImage = loadBackground("dog_room_basic.jpg");
                cleanlinessbar.setValue(petRoomViewModel.getState().getCleanliness());

            }
        });


        timer = new Timer(1000, e -> {
            if (elapsedSeconds != 0){
                elapsedSeconds--;
                if (petRoomController!= null) {
                    petRoomController.execute("tick");
                    foodbar.setValue(petRoomViewModel.getState().getFood());
                    waterbar.setValue(petRoomViewModel.getState().getWater());
                    cleanlinessbar.setValue(petRoomViewModel.getState().getCleanliness());
                    happinessbar.setValue(petRoomViewModel.getState().getHappiness());}
            timerLabel.setText("Time: " + elapsedSeconds);
            petRoomImage = loadBackground("dog_room_basic.jpg");}

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
        meterPanel.add(new JLabel("happiness"));
        meterPanel.add(happinessbar);
        meterPanel.add(new JLabel("cleanliness"));
        meterPanel.add(cleanlinessbar);
        add(meterPanel, BorderLayout.NORTH);
        add(timerLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);


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
            petRoomImage = loadBackground(petRoomState.getBackgroundImageName());
            repaint();
        }

    }
    public void setPetRoomController(PetRoomController petRoomController) {
        this.petRoomController = petRoomController;
    }
    public String getViewName(){return viewName;}
    //@Override
    //public void actionPerformed(ActionEvent e){}
}
