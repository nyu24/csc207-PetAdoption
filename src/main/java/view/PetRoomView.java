package view;

import interface_adapter.PetRoom.PetRoomViewModel;
import interface_adapter.PetRoom.PetRoomState;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Objects;

public class PetRoomView extends JPanel implements PropertyChangeListener {
    private final String viewName = "pet room";
    private final PetRoomViewModel petRoomViewModel;

    private final Image petRoomImage;
    private final JProgressBar foodbar;
    private final JProgressBar waterbar;
    private final JProgressBar happinessbar;
    private final JProgressBar cleanlinessbar;

    public PetRoomView(PetRoomViewModel petRoomViewModel){
        this.petRoomViewModel = petRoomViewModel;
        petRoomViewModel.addPropertyChangeListener(this);


        foodbar = new JProgressBar(0, 100);
        waterbar = new JProgressBar(0, 100);
        happinessbar = new JProgressBar(0, 100);
        cleanlinessbar = new JProgressBar(0, 100);
        foodbar.setValue(100);
        waterbar.setValue(100);
        happinessbar.setValue(100);
        cleanlinessbar.setValue(100);

        setLayout(new BorderLayout());
        JPanel meterPanel= new JPanel();
        meterPanel.setLayout(new BoxLayout(meterPanel, BoxLayout.Y_AXIS));
        meterPanel.add(foodbar);
        meterPanel.add(waterbar);
        meterPanel.add(happinessbar);
        meterPanel.add(cleanlinessbar);
        add(meterPanel, BorderLayout.NORTH);

        petRoomImage = loadBackground("dog_room_basic.jpg");


    }
    public Image loadBackground(String path){
        return new ImageIcon(Objects.requireNonNull(getClass().getResource(path))).getImage();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(petRoomImage, 0, 0, getWidth(), getHeight(), this);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (!"state".equals(evt.getPropertyName())) {
            PetRoomState petRoomState = petRoomViewModel.getState();
            foodbar.setValue(petRoomState.getFood());
            waterbar.setValue(petRoomState.getWater());
            happinessbar.setValue(petRoomState.getHappiness());
            cleanlinessbar.setValue(petRoomState.getCleanliness());
            repaint();
        }

    }
}
