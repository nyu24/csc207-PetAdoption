package view;

import interface_adapter.PetRoom.PetRoomController;
import interface_adapter.PetRoom.PetRoomViewModel;
import interface_adapter.PetRoom.PetRoomState;
import interface_adapter.PetRoom.PetRoomController;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PetRoomView extends JPanel implements PropertyChangeListener {
    private final String viewName = "pet room";
    private final PetRoomViewModel petRoomViewModel;
    private PetRoomController petRoomController;

    private Image petRoomImage;
    private final JProgressBar foodbar;
    private final JProgressBar waterbar;
    private final JProgressBar happinessbar;
    private final JProgressBar cleanlinessbar;
    private final Timer timer;
    private int elapsedSeconds = 10;//120;
    private final JLabel timerLabel = new JLabel("Time: 0s");

    //temp buttons
    private final JButton feed = new JButton("feed");
    private final JButton clean = new JButton("clean");
    private final JButton water = new JButton("water");


    public PetRoomView(PetRoomViewModel petRoomViewModel) {
        this.petRoomViewModel = petRoomViewModel;
        this.petRoomController = petRoomController;


        foodbar = new JProgressBar(0, 100);
        waterbar = new JProgressBar(0, 100);
        happinessbar = new JProgressBar(0, 100);
        cleanlinessbar = new JProgressBar(0, 100);
        foodbar.setValue(50);
        waterbar.setValue(50);
        happinessbar.setValue(80);
        cleanlinessbar.setValue(50);


        //temp buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(feed);
        buttonPanel.add(clean);
        buttonPanel.add(water);
        feed.addActionListener(e -> {
            if (petRoomController != null) {
                petRoomController.execute("feed");
            }
        });
        water.addActionListener(e -> {
            if (petRoomController != null) {
                petRoomController.execute("water");
            }
        });

        clean.addActionListener(e -> {
            if (petRoomController != null) {
                petRoomController.execute("clean");
            }
        });

        petRoomImage = loadBackground("dog_room_basic.jpg");

        timer = new Timer(1000, e -> {
            elapsedSeconds--;
            if (elapsedSeconds <= 0) {
                Map<String, Integer> stats = new HashMap<>();
                PetRoomState petRoomState = petRoomViewModel.getState();
                stats.put("hunger", petRoomState.getFood());
                stats.put("thirst", petRoomState.getWater());
                stats.put("cleanliness", petRoomState.getCleanliness());
                stats.put("happiness", petRoomState.getHappiness());
                petRoomController.sendPetData(stats);
            }
            timerLabel.setText("Time: " + elapsedSeconds);
            if (petRoomController != null) {
                petRoomController.execute("tick");
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
}
