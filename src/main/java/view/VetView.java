package view;

import interface_adapter.vet_score.VetScoreState;
import interface_adapter.vet_score.VetScoreViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class VetView extends JPanel implements PropertyChangeListener {

    private final String viewName = "vetView";
    private final VetScoreViewModel vetScoreViewModel;

    private DefaultTableModel model;
    private JTable table;
//    public void testPropertyChange() {
//        System.out.println("Creating test listener...");
//
//        PropertyChangeListener testListener = new PropertyChangeListener() {
//            @Override
//            public void propertyChange(PropertyChangeEvent evt) {
//                System.out.println("TEST LISTENER FIRED: " + evt.getPropertyName());
//            }
//        };
//
//        vetScoreViewModel.addPropertyChangeListener(testListener);
//
//        VetScoreState testState = new VetScoreState();
//        vetScoreViewModel.setState(testState);
//        vetScoreViewModel.firePropertyChanged();
//
//        System.out.println("Test complete");
//    }
    public VetView(VetScoreViewModel vetScoreViewModel) {
        this.vetScoreViewModel = vetScoreViewModel;
        System.out.println("VetView: Adding listener to viewModel: " + System.identityHashCode(vetScoreViewModel));
        this.vetScoreViewModel.addPropertyChangeListener(this);

        // Verify it was added
        System.out.println("VetView: Listener added, checking...");
        vetScoreViewModel.printListeners();

        setLayout(new BorderLayout());

        String[] columnNames = {"Requirement", "Status"};
        model = new DefaultTableModel(columnNames, 0);

        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

        // Status renderer
        table.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);

                String status = value.toString();
                if (status.equalsIgnoreCase("Passed")) {
                    c.setForeground(new Color(0, 150, 0));
                } else {
                    c.setForeground(new Color(200, 0, 0));
                }

                setHorizontalAlignment(SwingConstants.CENTER);
                return c;
            }
        });

        // Requirement column renderer
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        add(new JScrollPane(table), BorderLayout.CENTER);

        // Initialize table once on creation
        updateTable(this.vetScoreViewModel.getState().getRequirements());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("VetView: Property change received! Property: " + evt.getPropertyName()); // DEBUG

        if ("state".equals(evt.getPropertyName())) {
            VetScoreState state = (VetScoreState) evt.getNewValue();
            updateTable(state.getRequirements());
        }
    }

    private void updateTable(List<List<String>> requirements) {
        // Clear existing rows
        model.setRowCount(0);

        // Refill table
        for (List<String> row : requirements) {
            model.addRow(row.toArray());
        }

        model.fireTableDataChanged();
    }

    public String getViewName() {
        return viewName;
    }
}
