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

/**
 * View for select animal.
 */
public class SelectAnimalView extends JPanel {
    // variables
    private final String viewName = "Select Animal";
    private final SelectAnimalViewModel selectAnimalViewModel;

    // Controller
    private SelectAnimalController selectAnimalController = null;

    public SelectAnimalView(SelectAnimalViewModel selectAnimalViewModel) {
        this.selectAnimalViewModel = selectAnimalViewModel;

        // resetting the JPanel and renewing it
        this.removeAll();
        this.revalidate();
        this.repaint();

        // main panel initialization
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        final JLabel title = new JLabel("Select an animal :)");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        final JButton readyBtn = new JButton("Ready To Select.");
        buttons.add(readyBtn);

        // lets the user go back to the Set Param view to change parameters
        final JButton backBtn = new JButton("Back To Parameters");
        backBtn.setBackground(Color.GREEN);
        backBtn.addActionListener(
                e -> {
                    selectAnimalController.executeBack();
                }
        );

        // lets the user refresh the Select Animal View to the correct scrollPane
        final JButton refreshBtn = new JButton("Refresh Results");
        refreshBtn.setBackground(Color.CYAN);
        refreshBtn.addActionListener(
                e -> {
                    mainPanel.removeAll();
                    mainPanel.add(title);

                    buttons.removeAll();
                    buttons.add(backBtn);
                    buttons.add(refreshBtn);

                    final ArrayList<ApiPet> apiPetArrayList = selectAnimalViewModel.getState().getApiPetList();
                    final AnimalPane animalPane = new AnimalPane(apiPetArrayList);

                    final JScrollPane scrollPane = new JScrollPane(animalPane);
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                    // setting scrollPane size so it scrolls
                    final int scrollPaneWidth = 1000;
                    final int scrollPaneHeight = 700;
                    scrollPane.setMinimumSize(new Dimension(scrollPaneWidth, scrollPaneHeight));
                    scrollPane.setPreferredSize(new Dimension(scrollPaneWidth, scrollPaneHeight));

                    // checking to see if the animalPane is empty
                    final int emptyPanelSize = 0;
                    if (animalPane.getComponentCount() <= emptyPanelSize) {
                        mainPanel.add(new JLabel("No such animals. Try removing some parameters?"));
                    }
                    else {
                        mainPanel.add(scrollPane);
                    }
                    mainPanel.add(buttons);
                    mainPanel.revalidate();
                }
        );

        // action listener for 'ready' button
        readyBtn.addActionListener(
                e -> {
                    buttons.removeAll();
                    buttons.add(backBtn);
                    buttons.add(refreshBtn);

                    final ArrayList<ApiPet> apiPetArrayList = selectAnimalViewModel.getState().getApiPetList();
                    final AnimalPane animalPane = new AnimalPane(apiPetArrayList);

                    final JScrollPane scrollPane = new JScrollPane(animalPane);
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                    // setting scrollPane size so it scrolls
                    final int scrollPaneWidth = 1000;
                    final int scrollPaneHeight = 700;
                    scrollPane.setMinimumSize(new Dimension(scrollPaneWidth, scrollPaneHeight));
                    scrollPane.setPreferredSize(new Dimension(scrollPaneWidth, scrollPaneHeight));

                    // checking to see if the animalPane is empty
                    final int emptyPanelSize = 0;
                    if (animalPane.getComponentCount() <= emptyPanelSize) {
                        mainPanel.add(new JLabel("No such animals. Try removing some parameters?"));
                    }
                    else {
                        mainPanel.add(scrollPane);
                    }
                    mainPanel.add(buttons);
                    mainPanel.revalidate();
                }
        );

        // adding things to the main panel
        mainPanel.add(title);
        mainPanel.add(buttons);

        this.add(mainPanel);
    }

    public String getViewName() {
        return viewName;
    }

    public void setSelectAnimalController(SelectAnimalController selectAnimalController) {
        this.selectAnimalController = selectAnimalController;
    }

    public class AnimalPane extends JPanel {
        AnimalPane(ArrayList<ApiPet> apiPetArrayList) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            for (ApiPet apiPet : apiPetArrayList) {
                final String nameApiPet = apiPet.getName();
                final String imageUrlApi = apiPet.getImage();
                final String descriptionApi = apiPet.getDescription();

                this.add(new AnimalItem(nameApiPet, imageUrlApi, descriptionApi, apiPet));
            }
        }
    }

    public class AnimalItem extends JPanel {
        AnimalItem(String nameapi, String imageurlapi, String descriptionapi, ApiPet chosenPet) {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            final JButton selectBtn = new JButton("Select & Start Game");
            selectBtn.addActionListener(
                    e -> selectAnimalController.execute(chosenPet)
            );

            if (!Objects.equals(imageurlapi, "")) {
                URL url = null;
                try {
                    url = new URL(imageurlapi);
                }
                catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    final BufferedImage imageUrl = ImageIO.read(url);
                    final ImageIcon imageIcon = new ImageIcon(imageUrl);
                    final Image originalImage = imageIcon.getImage();

                    // rescaling image to desired
                    final int desiredWidth = 100;
                    final int desiredHeight = 100;
                    final Image scaledImage = originalImage.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
                    final ImageIcon scaledIcon = new ImageIcon(scaledImage);

                    final JLabel image = new JLabel(scaledIcon);
                    this.add(image);
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            final JLabel name = new JLabel("Name: " + nameapi);
            final JLabel description = new JLabel("Description: " + descriptionapi);
            final JLabel endOfSection = new JLabel("---------------------------------------------------------------");

            this.add(name);
            this.add(description);
            this.add(selectBtn);
            this.add(endOfSection);
        }
    }
}
