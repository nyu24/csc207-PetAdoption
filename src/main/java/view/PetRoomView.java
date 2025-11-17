package view;

import interface_adapter.PetRoom.PetRoomController;
import interface_adapter.PetRoom.PetRoomViewModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Objects;

public class PetRoomView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "pet room";
    private final PetRoomViewModel petRoomViewModel;
    private final PetRoomController petRoomController;

    private final Image petRoomImage;
    private final JButton waterButton;
    private final JButton foodButton;

    public PetRoomView(PetRoomViewModel petRoomViewModel, PetRoomController petRoomController) throws IOException {
        this.petRoomViewModel = petRoomViewModel;
        this.petRoomController = petRoomController;
        petRoomImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("dogroom_csc207.jpg"))).getImage();
        ImageIcon waterBowlImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("waterbowl_csc207.png")));
        waterButton = new JButton(waterBowlImage);
        noButtonBackground(waterButton);
        waterButton.addActionListener(this);
        waterButton.setActionCommand("water");
        ImageIcon foodBowlImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("foodbowl_csc207.png")));
        foodButton = new JButton(foodBowlImage);
        noButtonBackground(foodButton);
        foodButton.addActionListener(this);
        foodButton.setActionCommand("feed");

    }
    private void noButtonBackground(JButton button){
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(petRoomImage, 0, 0, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
