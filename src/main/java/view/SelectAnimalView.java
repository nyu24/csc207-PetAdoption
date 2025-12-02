package view;

import entities.ApiPet;
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

        //resetting the JPanel and renewing it
        this.removeAll();
        this.revalidate();
        this.repaint();

        //main panel initialization
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        final JLabel title = new JLabel("Select an animal :)");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        JButton readyBtn = new JButton("Ready To Select.");
        buttons.add(readyBtn);

        //lets the user go back to the Set Param view to change parameters
        JButton backBtn = new JButton("Back To Parameters");
        backBtn.setBackground(Color.GREEN);
        backBtn.addActionListener(
                e -> {
                    selectAnimalController.executeBack();
                }
        );

        //lets the user refresh the Select Animal View to the correct scrollPane
        JButton refreshBtn = new JButton("Refresh Results");
        refreshBtn.setBackground(Color.CYAN);
        refreshBtn.addActionListener(
                e -> {
                    mainPanel.removeAll();
                    mainPanel.add(title);

                    buttons.removeAll();
                    buttons.add(backBtn);
                    buttons.add(refreshBtn);

                    ArrayList<ApiPet> apiPetArrayList = selectAnimalViewModel.getState().getApiPetList();
                    AnimalPane animalPane = new AnimalPane(apiPetArrayList);

                    JScrollPane scrollPane = new JScrollPane(animalPane);
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                    //setting scrollPane size so it scrolls
                    int scrollPaneWidth = 1000;
                    int scrollPaneHeight = 700;
                    scrollPane.setMinimumSize(new Dimension(scrollPaneWidth, scrollPaneHeight));
                    scrollPane.setPreferredSize(new Dimension(scrollPaneWidth, scrollPaneHeight));

                    //checking to see if the animalPane is empty
                    int emptyPanelSize = 0;
                    if(animalPane.getComponentCount() <= emptyPanelSize){
                        System.out.println("no animals?");
                        mainPanel.add(new JLabel("No such animals. Try removing some parameters?"));
                    }
                    else{
                        mainPanel.add(scrollPane);
                    }
                    mainPanel.add(buttons);
                    mainPanel.revalidate();
                }
        );

        //action listener for 'ready' button
        readyBtn.addActionListener(
                e -> {
                    buttons.removeAll();
                    buttons.add(backBtn);
                    buttons.add(refreshBtn);

                    ArrayList<ApiPet> apiPetArrayList = selectAnimalViewModel.getState().getApiPetList();
                    AnimalPane animalPane = new AnimalPane(apiPetArrayList);

                    JScrollPane scrollPane = new JScrollPane(animalPane);
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                    //setting scrollPane size so it scrolls
                    int scrollPaneWidth = 1000;
                    int scrollPaneHeight = 700;
                    scrollPane.setMinimumSize(new Dimension(scrollPaneWidth, scrollPaneHeight));
                    scrollPane.setPreferredSize(new Dimension(scrollPaneWidth, scrollPaneHeight));

                    //checking to see if the animalPane is empty
                    int emptyPanelSize = 0;
                    if(animalPane.getComponentCount() <= emptyPanelSize){
                        System.out.println("no animals?");
                        mainPanel.add(new JLabel("No such animals. Try removing some parameters?"));
                    }
                    else{
                        mainPanel.add(scrollPane);
                    }
                    mainPanel.add(buttons);
                    mainPanel.revalidate();
                }
        );

        //adding things to the main panel
        mainPanel.add(title);
        mainPanel.add(buttons);

        this.add(mainPanel);
    }

    public class AnimalPane extends JPanel {
        AnimalPane(ArrayList<ApiPet> apiPetArrayList) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            for(ApiPet apiPet : apiPetArrayList) {
                String nameAPIPet = apiPet.getName();
                String imageUrlAPI = apiPet.getImage();
                String descriptionAPI = apiPet.getDescription();

                this.add(new AnimalItem(nameAPIPet, imageUrlAPI, descriptionAPI, apiPet));
            }
        }
    }

    public class AnimalItem extends JPanel {
        AnimalItem(String nameAPI, String imageUrlAPI, String descriptionAPI, ApiPet chosenPet) {
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
