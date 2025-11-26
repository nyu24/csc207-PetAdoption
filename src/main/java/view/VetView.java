package view;

import interface_adapter.vet_score.VetScoreState;
import interface_adapter.vet_score.VetScoreViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class VetView extends JPanel implements PropertyChangeListener {

    private final String viewName = "vetView";
    private final VetScoreViewModel vetScoreViewModel;
    private final JLabel resultLabel; // new label for pass/fail
    private final JLabel centerMessageLabel;
    private final JButton actionButton;
    private String redirectUrl = "https://www.petfinder.com/";
    private DefaultTableModel model;
    private JTable table;
    private JButton toScore;
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

        final JPanel buttons = new JPanel();

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


        centerMessageLabel = new JLabel("", SwingConstants.CENTER);
        centerMessageLabel.setFont(new Font("Arial", Font.BOLD, 18));

        actionButton = new JButton("Proceed");
        actionButton.setVisible(false);
        actionButton.addActionListener(e -> {
            if (redirectUrl != null && !redirectUrl.isBlank()) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(new URI(redirectUrl));
                    } catch (IOException | URISyntaxException ex) {
                        // minimal error handling: print stack for debugging
                        ex.printStackTrace();
                    }
                }
            }
        });

        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        messagePanel.add(centerMessageLabel, BorderLayout.CENTER);

        JPanel buttonWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonWrapper.add(actionButton);
        messagePanel.add(buttonWrapper, BorderLayout.SOUTH);

        // Use a split pane so the message area appears between the table and south area
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                new JScrollPane(table), messagePanel);
        splitPane.setResizeWeight(0.8);
        splitPane.setContinuousLayout(true);
        splitPane.setOneTouchExpandable(false);

        add(splitPane, BorderLayout.CENTER);


        // Result label at bottom
        resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        southPanel.add(resultLabel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

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

        updateResultLabel(requirements);
    }
    private void updateResultLabel(List<List<String>> requirements) {
        boolean allPassed = true;
        for (List<String> row : requirements) {
            // safe check: status is expected to be at index 1
            String status = (row.size() > 1 && row.get(1) != null) ? row.get(1).trim() : "";
            if (!status.equalsIgnoreCase("Passed")) {
                allPassed = false;
                break;
            }
        }

        if (allPassed) {
            resultLabel.setText("Result: Passed");
            resultLabel.setForeground(new Color(0, 150, 0));

            // Center message and enable button
            centerMessageLabel.setText("You passed, you can adopt the pet");
            centerMessageLabel.setForeground(new Color(0, 150, 0));
            actionButton.setVisible(true);
            actionButton.setEnabled(redirectUrl != null && !redirectUrl.isBlank());
            actionButton.setText("Adopt now");
        } else {
            resultLabel.setText("Result: Failed");
            resultLabel.setForeground(new Color(200, 0, 0));

            centerMessageLabel.setText("You failed, you cannot adopt the pet");
            centerMessageLabel.setForeground(new Color(200, 0, 0));
            actionButton.setVisible(false);
        }
        refreshActionButton(allPassed);

    }

    // Always update visibility/enabled state on the EDT
    private void refreshActionButton(final boolean allPassed) {
        SwingUtilities.invokeLater(() -> {
            if (allPassed) {
                actionButton.setVisible(true);
                actionButton.setEnabled(redirectUrl != null && !redirectUrl.isBlank());
            } else {
                actionButton.setVisible(false);
                actionButton.setEnabled(false);
            }
            revalidate();
            repaint();
            System.out.println("VetView: refreshActionButton -> visible=" + actionButton.isVisible() + " enabled=" + actionButton.isEnabled() + " url=" + redirectUrl);
        });
    }
    public String getViewName() {
        return viewName;
    }
    public void setRedirectUrl(String url) {
        this.redirectUrl = url;
        // enable the button if currently visible and url is valid
        if (actionButton != null && actionButton.isVisible()) {
            actionButton.setEnabled(url != null && !url.isBlank());
        }
    }
}
