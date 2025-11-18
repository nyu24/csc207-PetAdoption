package view;

import interface_adapter.vet_score.VetScoreViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class VetView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "VetView";
    private final VetScoreViewModel vetScoreViewModel;
    private final JLabel requirements;


    public VetView(VetScoreViewModel vetScoreViewModel) {
        this.vetScoreViewModel = vetScoreViewModel;
        this.vetScoreViewModel.addPropertyChangeListener(this);

        String[] columnNames = {"Requirement", "Status"};

        // Example requirement data
        Object[][] data = {
                {"Hunger", "PASSED"},
                {"Thirst", "FAILED"},
                {"Happiness", "PASSED"},
                {"Comfort", "FAILED"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false; // Make table read-only
            }
        };

    }

}
