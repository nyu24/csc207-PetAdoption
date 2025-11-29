package view;

import entities.APIPet;
import interface_adapter.select_animal.SelectAnimalController;
import interface_adapter.select_animal.SelectAnimalViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class SelectAnimalView extends JPanel{
    //variables
    private final String viewName = "Select Animal";
    private final SelectAnimalViewModel selectAnimalViewModel;

    //Controller
    private SelectAnimalController selectAnimalController = null;

    public SelectAnimalView(SelectAnimalViewModel selectAnimalViewModel) {
        this.selectAnimalViewModel = selectAnimalViewModel;

        //main panel initialization
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        final JLabel title = new JLabel("Select an animal :)");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        JButton readyBtn = new JButton("Ready To Select.");
        buttons.add(readyBtn);

        //action listener for 'ready' button
        readyBtn.addActionListener(
                e -> {
                    buttons.removeAll();

                    ArrayList<APIPet> apiPetArrayList = selectAnimalViewModel.getState().getApiPetList();
                    JScrollPane scrollPane = new JScrollPane(new animalPane(apiPetArrayList));
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                    //setting scrollPane size so it scrolls
                    int scrollPaneWidth = 1000;
                    int scrollPaneHeight = 700;
                    scrollPane.setMinimumSize(new Dimension(scrollPaneWidth, scrollPaneHeight));
                    scrollPane.setPreferredSize(new Dimension(scrollPaneWidth, scrollPaneHeight));
                    mainPanel.add(scrollPane);
                    mainPanel.revalidate();
                }
        );

        //adding things to the main panel
        mainPanel.add(title);
        mainPanel.add(buttons);

        this.add(mainPanel);
    }

    public class animalPane extends JPanel {
        animalPane(ArrayList<APIPet> apiPetArrayList) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            for(APIPet apiPet : apiPetArrayList) {
                String nameAPIPet = apiPet.getName();
                String imageUrlAPI = apiPet.getImage();
                String descriptionAPI = apiPet.getDescription();

                this.add(new animalItem(nameAPIPet, imageUrlAPI, descriptionAPI, apiPet));
            }
        }
    }

    public class animalItem extends JPanel {
        animalItem(String nameAPI, String imageUrlAPI, String descriptionAPI, APIPet chosenPet) {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JButton selectBtn = new JButton("Select & Start Game");
            selectBtn.addActionListener(
                    e -> selectAnimalController.execute(chosenPet)
            );

            if(!Objects.equals(imageUrlAPI, "")) {
                URL url = null;
                try {
                    url = new URL(imageUrlAPI);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    BufferedImage imageUrl = ImageIO.read(url);
                    ImageIcon imageIcon = new ImageIcon(imageUrl);
                    Image originalImage = imageIcon.getImage();

                    //rescaling image to desired
                    int desiredWidth = 100;
                    int desiredHeight = 100;
                    Image scaledImage = originalImage.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);

                    JLabel image = new JLabel(scaledIcon);
                    this.add(image);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            JLabel name = new JLabel("Name: " + nameAPI);
            JLabel description = new JLabel("Description: " + descriptionAPI);
            JLabel endOfSection = new JLabel("---------------------------------------------------------------");

            this.add(name);
            this.add(description);
            this.add(selectBtn);
            this.add(endOfSection);
        }
    }
    // ----------------------------------------------------

    public String getViewName() {
        return viewName;
    }

    public void setSelectAnimalController(SelectAnimalController selectAnimalController) {
        this.selectAnimalController = selectAnimalController;
    }
}
