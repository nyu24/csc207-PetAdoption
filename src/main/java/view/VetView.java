package view;

import entities.User;
import interface_adapter.vet_score.VetScoreState;
import interface_adapter.vet_score.VetScoreViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;


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
        List<List<String>> requirements = vetScoreState.getRequirements();

        String[] columnNames = {"Requirement", "Status"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (List<String> row : requirements) {
            model.addRow(row.toArray());
        }

//        // Example requirement data
//        Object[][] data = {
//                {"Hunger", "PASSED"},
//                {"Thirst", "FAILED"},
//                {"Happiness", "PASSED"},
//                {"Comfort", "FAILED"}
//        };



        JTable table = new JTable(model);


        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

        // Custom renderer for the Status column
        table.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);

                String status = value.toString();
                if (status.equalsIgnoreCase("Passed")) {
                    c.setForeground(new Color(0, 150, 0)); // Dark green
                    setText("Passed");
                } else if (status.equalsIgnoreCase("Failed")) {
                    c.setForeground(new Color(200, 0, 0)); // Dark red
                    setText("Failed");
                }

                setHorizontalAlignment(SwingConstants.CENTER);
                return c;
            }
        });

        // Center align the Requirement column too
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);


//        table.setBorder(null);

        JScrollPane scrollPane = new JScrollPane(table);
//        table.setFillsViewportHeight(true);
//        scrollPane.setBorder(null);

//        this.setBorder(null);
//        scrollPane.setViewportBorder(null);

        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);

        frame.add(this);
        frame.setVisible(true);

    }

}
