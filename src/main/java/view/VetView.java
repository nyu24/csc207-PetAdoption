package view;

import entities.User;
import interface_adapter.vet_score.VetScoreState;
import interface_adapter.vet_score.VetScoreViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class VetView extends JPanel {

    private final String viewName = "VetView";
    private final VetScoreViewModel vetScoreViewModel;
    private final JLabel requirements;

    public static void main(String[] args) {

        VetScoreViewModel vetViewModel  = new VetScoreViewModel();
        SwingUtilities.invokeLater(() -> new VetView(vetViewModel));

    }




    public VetView(VetScoreViewModel vetScoreViewModel) {

        JFrame frame = new JFrame("Table in JPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);


        this.vetScoreViewModel = vetScoreViewModel;
//        this.vetScoreViewModel.addPropertyChangeListener(this);

        requirements = new JLabel("Requirements:");

        final VetScoreState vetScoreState = this.vetScoreViewModel.getState();
        User user = vetScoreState.getUser();

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


        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        this.add(scrollPane, BorderLayout.CENTER);

        frame.add(this);
        frame.setVisible(true);
    }

}
