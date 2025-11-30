package view;

import interface_adapter.set_parameters.SetParamController;
import interface_adapter.set_parameters.SetParamState;
import interface_adapter.set_parameters.SetParamViewModel;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import javax.swing.*;

/**
 * the View for when the user is determining the parameters
 */
public class SetParamView extends JPanel implements PropertyChangeListener {
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
                e -> {
                    //adding the parameter stuff
                    mainPanel.add(typeDropdownPanel);
                    mainPanel.add(typeErrorField);

                    //adding all to main panel
                    mainPanel.add(breedDropdownPanel);
                    mainPanel.add(coatDropdownPanel);
                    mainPanel.add(colorDropdownPanel);
                    mainPanel.add(genderDropdownPanel);

                    final ArrayList<String> typeOptions = setParamController.getTypes();

                    //removing certain animal types because we ran out of time to draw the other pets
                    //however other functions, like 'Horse' 'Barnyard' 'Bird' still function
                    //remove these lines to be able to search for them too.
                    typeOptions.remove("Barnyard");
                    typeOptions.remove("Horse");
                    typeOptions.remove("Bird");

                    ArrayList<String> types = new ArrayList<>();
                    types.add("");
                    types.addAll(typeOptions);
                    typeDropdown = new JComboBox<>(types.toArray(new String[0]));
                    typeDropdownPanel.removeAll();
                    typeDropdownPanel.add(new JLabel("Animal Type: "));
                    typeDropdownPanel.add(typeDropdown);

                    buttons.removeAll();
                    buttons.add(searchBtn);

                    //setting up empties for the other attributes
                    breedDropdown.addItem("");
                    coatDropdown.addItem("");
                    colorDropdown.addItem("");
                    genderDropdown.addItem("");

                    mainPanel.revalidate();

                    //action listener for the type, aka refresh the other params to follow this new selected type
                    typeDropdown.addActionListener(
                            e1 -> {
                                JComboBox comboBox = (JComboBox) e1.getSource();
                                String type = (String) comboBox.getSelectedItem();

                                //type attributes if type is NOT empty-----------------
                                assert type != null;
                                if(!type.isEmpty()){
                                    ArrayList<ArrayList<String>> attributesList = setParamController.getTypeAttributes(type);

                                    //breed
                                    ArrayList<String> breeds = new ArrayList<>();
                                    breeds.add("");
                                    breeds.addAll(attributesList.get(0));
                                    breedDropdown.removeAllItems();
                                    breedDropdown = new JComboBox<>(breeds.toArray(new String[0]));
                                    breedDropdownPanel.removeAll();
                                    breedDropdownPanel.add(new JLabel("Breed Type: "));
                                    breedDropdownPanel.add(breedDropdown);

                                    //coat
                                    ArrayList<String> coats = new ArrayList<>();
                                    coats.add("");
                                    coats.addAll(attributesList.get(1));
                                    coatDropdown.removeAllItems();
                                    coatDropdown = new JComboBox<>(coats.toArray(new String[0]));
                                    coatDropdownPanel.removeAll();
                                    coatDropdownPanel.add(new JLabel("Coat Length: "));
                                    coatDropdownPanel.add(coatDropdown);

                                    //colour
                                    ArrayList<String> colors = new ArrayList<>();
                                    colors.add("");
                                    colors.addAll(attributesList.get(2));
                                    colorDropdown.removeAllItems();
                                    colorDropdown = new JComboBox<>(colors.toArray(new String[0]));
                                    colorDropdownPanel.removeAll();
                                    colorDropdownPanel.add(new JLabel("Color: "));
                                    colorDropdownPanel.add(colorDropdown);

                                    //gender
                                    ArrayList<String> genders = new ArrayList<>();
                                    genders.add("");
                                    genders.addAll(attributesList.get(3));
                                    genderDropdown.removeAllItems();
                                    genderDropdown = new JComboBox<>(genders.toArray(new String[0]));
                                    genderDropdownPanel.removeAll();
                                    genderDropdownPanel.add(new JLabel("Gender: "));
                                    genderDropdownPanel.add(genderDropdown);

                                    mainPanel.revalidate();
                                }
                            }
                    );
                }
        );

        //action listener for 'search' button
        searchBtn.addActionListener(
                e -> {
                    if(e.getSource().equals(searchBtn)) {
                        setParamController.execute(
                                Objects.requireNonNull(typeDropdown.getSelectedItem()).toString(),
                                Objects.requireNonNull(breedDropdown.getSelectedItem()).toString(),
                                Objects.requireNonNull(colorDropdown.getSelectedItem()).toString(),
                                Objects.requireNonNull(coatDropdown.getSelectedItem()).toString(),
                                Objects.requireNonNull(genderDropdown.getSelectedItem()).toString()
                        );
                    }
                }
        );

        //adding things to the main panel
        mainPanel.add(title);

        mainPanel.add(buttons);

        this.add(mainPanel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SetParamState state = (SetParamState) evt.getNewValue();
        typeErrorField.setText(state.getSetParamError());
    }

    public String getViewName() {
        return viewName;
    }

    public void setSetParamController(SetParamController setParamController) {
        this.setParamController = setParamController;
    }
}
