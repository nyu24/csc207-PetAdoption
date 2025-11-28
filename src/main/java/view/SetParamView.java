package view;

import interface_adapter.set_parameters.SetParamState;
import interface_adapter.set_parameters.SetParamController;
import interface_adapter.set_parameters.SetParamViewModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.*;
import javax.swing.*;

/**
 * the View for when the user is determining the parameters
 */
public class SetParamView extends JPanel implements ActionListener, PropertyChangeListener {
    //final variables
    private final String viewName = "Set Param";
    private final SetParamViewModel setParamViewModel;

    //initialize the textfields and labels --------------------
    //type ----------------------------------------------------
    private JComboBox<String> typeDropdown = new JComboBox<>();
    private final JLabel typeErrorField = new JLabel();

    //breed
    private JComboBox<String> breedDropdown = new JComboBox<>();

    //coat
    private JComboBox<String> coatDropdown = new JComboBox<>();

    //colour
    private JComboBox<String> colorDropdown = new JComboBox<>();

    //gender
    private JComboBox<String> genderDropdown = new JComboBox<>();

    //controller ------------------------------------------------
    private SetParamController setParamController = null;


    public SetParamView(SetParamViewModel setParamViewModel) {
        this.setParamViewModel = setParamViewModel;
        this.setParamViewModel.addPropertyChangeListener(this);

        //main panel initialization
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        final JLabel title = new JLabel("Set Parameters");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        //adding search button
        final JPanel buttons = new JPanel();
        JButton startBtn = new JButton("Start");
        buttons.add(startBtn);

        JButton searchBtn = new JButton("Search");

        //type drop down ----------------
        JPanel typeDropdownPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        typeDropdownPanel.add(new JLabel("Animal Type: "));
        typeDropdownPanel.add(typeDropdown);

        //panels for the other attributes
        JPanel breedDropdownPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        breedDropdownPanel.add(new JLabel("Breed Type: "));
        breedDropdownPanel.add(breedDropdown);

        JPanel coatDropdownPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        coatDropdownPanel.add(new JLabel("Coat Length: "));
        coatDropdownPanel.add(coatDropdown);

        JPanel colorDropdownPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        colorDropdownPanel.add(new JLabel("Color: "));
        colorDropdownPanel.add(colorDropdown);

        JPanel genderDropdownPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderDropdownPanel.add(new JLabel("Gender: "));
        genderDropdownPanel.add(genderDropdown);

        //starts the search/session
        startBtn.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //adding the parameter stuff
                        mainPanel.add(typeDropdownPanel);

                        //adding all to main panel
                        mainPanel.add(breedDropdownPanel);
                        mainPanel.add(coatDropdownPanel);
                        mainPanel.add(colorDropdownPanel);
                        mainPanel.add(genderDropdownPanel);

                        final ArrayList<String> typeOptions = setParamController.getTypes();
                        ArrayList<String> types = new ArrayList<>();
                        types.add("");
                        types.addAll(typeOptions);
                        typeDropdown = new JComboBox<>(types.toArray(new String[0]));
                        typeDropdownPanel.removeAll();
                        typeDropdownPanel.add(new JLabel("Animal Type: "));
                        typeDropdownPanel.add(typeDropdown);

                        buttons.removeAll();
                        buttons.add(searchBtn);

                        mainPanel.revalidate();

                        //action listener for the type, aka refresh the other params to follow this new selected type
                        typeDropdown.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        JComboBox comboBox = (JComboBox) e.getSource();
                                        String type = (String) comboBox.getSelectedItem();

                                        //type attributes -----------------
                                        ArrayList<ArrayList<String>> attributesList = setParamController.getTypeAttributes(type);

                                        //breed
                                        ArrayList<String> breeds = new ArrayList<>();
                                        breeds.add("");
                                        breeds.addAll(attributesList.get(0));
                                        breedDropdown = new JComboBox<>(breeds.toArray(new String[0]));
                                        breedDropdownPanel.removeAll();
                                        breedDropdownPanel.add(new JLabel("Breed Type: "));
                                        breedDropdownPanel.add(breedDropdown);

                                        //coat
                                        ArrayList<String> coats = new ArrayList<>();
                                        coats.add("");
                                        coats.addAll(attributesList.get(1));
                                        coatDropdown = new JComboBox<>(coats.toArray(new String[0]));
                                        coatDropdownPanel.removeAll();
                                        coatDropdownPanel.add(new JLabel("Coat Length: "));
                                        coatDropdownPanel.add(coatDropdown);

                                        //colour
                                        ArrayList<String> colors = new ArrayList<>();
                                        colors.add("");
                                        colors.addAll(attributesList.get(2));
                                        colorDropdown = new JComboBox<>(colors.toArray(new String[0]));
                                        colorDropdownPanel.removeAll();
                                        colorDropdownPanel.add(new JLabel("Color: "));
                                        colorDropdownPanel.add(colorDropdown);

                                        //gender
                                        ArrayList<String> genders = new ArrayList<>();
                                        genders.add("");
                                        genders.addAll(attributesList.get(3));
                                        genderDropdown = new JComboBox<>(genders.toArray(new String[0]));
                                        genderDropdownPanel.removeAll();
                                        genderDropdownPanel.add(new JLabel("Gender: "));
                                        genderDropdownPanel.add(genderDropdown);

                                        mainPanel.revalidate();
                                    }
                                }
                        );
                    }
                }
        );

        //action listener for 'search' button
        searchBtn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(e.getSource().equals(searchBtn)) {

                            setParamController.execute(
                                    typeDropdown.getSelectedItem().toString(),
                                    breedDropdown.getSelectedItem().toString(),
                                    colorDropdown.getSelectedItem().toString(),
                                    coatDropdown.getSelectedItem().toString(),
                                    genderDropdown.getSelectedItem().toString()
                            );
                        }
                    }
                }
        );

        //adding things to the main panel
        mainPanel.add(title);

        mainPanel.add(buttons);

        this.add(mainPanel);
    }

    /**
     * React to a button click that results in e
     * @param e the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    public void propertyChange(PropertyChangeEvent e){
        final SetParamState state = (SetParamState) e.getNewValue();
        setFields(state);
        typeErrorField.setText(state.getSetParamError());
    }

    private void setFields(SetParamState state) {
        typeDropdown.setSelectedItem(state.getType());
        breedDropdown.setSelectedItem(state.getBreed());
        coatDropdown.setSelectedItem(state.getCoat());
        genderDropdown.setSelectedItem(state.getGender());
        colorDropdown.setSelectedItem(state.getColour());
    }

    public String getViewName() {
        return viewName;
    }

    public void setSetParamController(SetParamController setParamController) {
        this.setParamController = setParamController;
    }

}
