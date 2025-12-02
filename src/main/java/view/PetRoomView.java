package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.swing.*;

import interface_adapter.PetRoom.PetRoomController;
import interface_adapter.PetRoom.PetRoomState;
import interface_adapter.PetRoom.PetRoomViewModel;
import interface_adapter.buttons.ButtonsController;
import interface_adapter.buttons.ButtonsViewModel;

public class PetRoomView extends JPanel implements PropertyChangeListener, ActionListener {
    private static final int TIME_LIMIT = 60;
    private static final int START_HUNGER = 80;
    private static final int START_THIRST = 80;
    private static final int START_CLEANLINESS = 80;
    private static final int START_HAPPINESS = 80;
    private static final int START_VALUE = 100;
    private static final int DIM = 45;
    private static final int TIMER = 1000;
    private static final int BT = 2000;
    private static final int G = 4;

    private final String viewName = "pet room";
    private final PetRoomViewModel petRoomViewModel;
    private PetRoomController petRoomController;
    private ButtonsController buttonsController;
    private final ButtonsViewModel buttonsViewModel;


    private Image petRoomImage;
    private final JProgressBar foodbar;
    private final JProgressBar waterbar;
    private final JProgressBar happinessbar;
    private final JProgressBar cleanlinessbar;
    private final Timer timer;
    private int elapsedSeconds;
    private final JLabel timerLabel = new JLabel("Time: 0s");

    private JButton feed;
    private JButton clean;
    private JButton water;
    private JButton play;
    private JButton save;
    private ImageIcon feedI;
    private ImageIcon cleanI;
    private ImageIcon waterI;
    private ImageIcon playI;

    private Timer backgroundResetTimer;

    // Constants
    private String currRoom;

    private final URL feedUrl;
    private final URL waterUrl;
    private final URL cleanUrl;
    private final URL happyUrl;
    private final ImageIcon feedIcon;
    private final ImageIcon waterIcon;
    private final ImageIcon cleanIcon;
    private final ImageIcon happyIcon;
    private final Image feedImage;
    private final Image waterImage;
    private final Image cleanImage;
    private final Image happyImage;
    private PetRoomState currentState;
    private PetRoomState petRoomState;
    private Map<String, Integer> stats;
    private final JPanel meterPanel;

    public PetRoomView(PetRoomViewModel petRoomViewModel, ButtonsViewModel buttonsViewModel) {
        this.petRoomViewModel = petRoomViewModel;
        this.petRoomViewModel.addPropertyChangeListener(this);
        this.buttonsViewModel = buttonsViewModel;
        petRoomImage = loadBackground("Press_button.jpg");

        foodbar = new JProgressBar(0, START_VALUE);
        waterbar = new JProgressBar(0, START_VALUE);
        cleanlinessbar = new JProgressBar(0, START_VALUE);
        happinessbar = new JProgressBar(0, START_VALUE);

        foodbar.setValue(START_VALUE);
        waterbar.setValue(START_VALUE);
        cleanlinessbar.setValue(START_VALUE);
        happinessbar.setValue(START_VALUE);

        feedUrl = getClass().getResource("/images_buttons/—Pngtree—theres a bone in the_4287031.png");
        feedIcon = new ImageIcon(feedUrl);
        feedImage = feedIcon.getImage().getScaledInstance(DIM, DIM, Image.SCALE_DEFAULT);
        feedI = new ImageIcon(feedImage);

        cleanUrl = getClass().getResource("/images_buttons/sponge-emoji-clipart-md.png");
        cleanIcon = new ImageIcon(cleanUrl);
        cleanImage = cleanIcon.getImage().getScaledInstance(DIM, DIM, Image.SCALE_DEFAULT);
        cleanI = new ImageIcon(cleanImage);

        waterUrl = getClass().getResource("/images_buttons/b6410ff0-049c-4f19-a53e-b0b048aadc40.jpg");
        waterIcon = new ImageIcon(waterUrl);
        waterImage = waterIcon.getImage().getScaledInstance(DIM, DIM, Image.SCALE_DEFAULT);
        waterI = new ImageIcon(waterImage);

        happyUrl = getClass().getResource("/images_buttons/—Pngtree—toy ball water polo round_7670359.png");
        happyIcon = new ImageIcon(happyUrl);
        happyImage = happyIcon.getImage().getScaledInstance(DIM, DIM, Image.SCALE_DEFAULT);
        playI = new ImageIcon(happyImage);

        final JPanel buttonPanel = new JPanel();
        feed = new JButton(feedI);
        clean = new JButton(cleanI);
        water = new JButton(waterI);
        play = new JButton(playI);
        save = new JButton("Save");
        feed.addActionListener(this);
        clean.addActionListener(this);
        water.addActionListener(this);
        play.addActionListener(this);

        buttonPanel.add(clean);
        buttonPanel.add(water);
        buttonPanel.add(play);
        buttonPanel.add(feed);
        buttonPanel.add(save);

        save.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(save)) {
                            currentState = petRoomViewModel.getState();

                            petRoomController.switchToSaveGameView();
                        }
                    }
                }
        );

        timer = new Timer(TIMER, e -> {
            elapsedSeconds--;
            if (elapsedSeconds <= 0) {
                System.out.println(elapsedSeconds);

                stats = new HashMap<>();

                petRoomState = petRoomViewModel.getState();

                stats.put("Hunger", petRoomState.getFood());
                stats.put("Thirst", petRoomState.getWater());
                stats.put("Cleanliness", petRoomState.getCleanliness());
                stats.put("Happiness", petRoomState.getHappiness());

                petRoomController.sendPetData(stats, petRoomState.getScore(), petRoomState.getCurrPet());
                ((Timer) e.getSource()).stop();
                petRoomViewModel.firePropertyChange("timerExpired");
            }
            else {
                if (petRoomController != null) {
                    currentState = petRoomViewModel.getState();
                    petRoomController.execute("tick", currentState.getScore(), currentState.getPetType());
                }
                timerLabel.setText("Time: " + elapsedSeconds);
            }
        }
        );

        setLayout(new BorderLayout());
        meterPanel = new JPanel();
        meterPanel.setLayout(new GridLayout(G, 1));
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

    private void switchBackgroundTemp(String imageName) {
        if (backgroundResetTimer != null && backgroundResetTimer.isRunning()) {
            backgroundResetTimer.stop();
        }
        petRoomImage = loadBackground(imageName);
        repaint();
        backgroundResetTimer = new Timer(BT, e -> {
            petRoomImage = loadBackground(petRoomViewModel.getState().getPetType() + "_room_basic.jpg");
            repaint();
        });
        backgroundResetTimer.setRepeats(false);
        backgroundResetTimer.start();
    }

    /**
     * Load the Background.
     * @param path image path thats followed
     * @return new image.
     */
    public Image loadBackground(String path) {
        return new ImageIcon(Objects.requireNonNull(getClass().getResource("/" + path))).getImage();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(petRoomImage, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("=== PROPERTY CHANGE FIRED ===");
        System.out.println("Property name: " + evt.getPropertyName());
        if ("value_update".equals(evt.getPropertyName())) {
            petRoomState = petRoomViewModel.getState();

            System.out.println("Food: " + petRoomState.getFood());
            System.out.println("Water: " + petRoomState.getWater());
            System.out.println("Clean: " + petRoomState.getCleanliness());
            System.out.println("Happy: " + petRoomState.getHappiness());
            System.out.println("Timer: " + elapsedSeconds);
            System.out.println("score: " + petRoomState.getScore());
            System.out.println("pet_name: " + petRoomState.getCurrPet().getName());
            System.out.println(petRoomState.getRoomType());

            // The below is so that the state remembers to update rest of the non-game states updated by the interactor
            petRoomViewModel.getState().setTimer(elapsedSeconds);

            foodbar.setValue(petRoomState.getFood());
            waterbar.setValue(petRoomState.getWater());
            happinessbar.setValue(petRoomState.getHappiness());
            cleanlinessbar.setValue(petRoomState.getCleanliness());
            this.currRoom = petRoomState.getRoomType();
            repaint();
        }

        else if ("timer_start".equals(evt.getPropertyName())) {
            // If load a save file.
            if (petRoomViewModel.getState().getTimer() > 0) {
                elapsedSeconds = petRoomViewModel.getState().getTimer();
                petRoomController.setRoomParameters(petRoomViewModel.getState().getFood(),
                        petRoomViewModel.getState().getWater(),
                        petRoomViewModel.getState().getCleanliness(),
                        petRoomViewModel.getState().getHappiness(),
                        petRoomViewModel.getState().getCurrPet());
            }
            else {
                elapsedSeconds = TIME_LIMIT;
                petRoomController.setRoomParameters(START_HUNGER,
                        START_THIRST,
                        START_CLEANLINESS,
                        START_HAPPINESS,
                        petRoomViewModel.getState().getCurrPet());
            }
            petRoomImage = loadBackground(petRoomViewModel.getState().getPetType() + "_room_basic.jpg");
            timerLabel.setText("Time: " + elapsedSeconds);
            timer.start();
        }
        else if ("timer_stop".equals(evt.getPropertyName())) {
            timer.stop();
        }
    }

    public void setPetRoomController(PetRoomController petRoomController) {
        this.petRoomController = petRoomController;
    }

    public void setButtonsController(ButtonsController buttonsController) {
        this.buttonsController = buttonsController;
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (petRoomController == null) {
            System.out.println("ERROR: Controller not set!");
            return;
        }
        petRoomState = petRoomViewModel.getState();
        if (e.getSource().equals(feed)) {
            System.out.println("Feed button clicked!");
            buttonsController.FeedClicked();
            petRoomController.execute("feed", petRoomState.getScore(), petRoomState.getPetType());
            switchBackgroundTemp(this.currRoom);
        }
        if (e.getSource() == clean) {
            buttonsController.CleanClicked();
            petRoomController.execute("clean", petRoomState.getScore(), petRoomState.getPetType());
            switchBackgroundTemp(this.currRoom);
        }
        if (e.getSource() == water) {
            buttonsController.WaterClicked();
            petRoomController.execute("water", petRoomState.getScore(), petRoomState.getPetType());
            switchBackgroundTemp(this.currRoom);
        }
        if (e.getSource() == play) {
            buttonsController.PlayClicked();
            petRoomController.execute("play", petRoomState.getScore(), petRoomState.getPetType());
            switchBackgroundTemp(this.currRoom);
        }
    }

}

