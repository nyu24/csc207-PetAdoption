package view;

/**
 * View for the vet use case of the program.
 */

import interface_adapter.vet_score.VetScoreController;
import interface_adapter.vet_score.VetScoreState;
import interface_adapter.vet_score.VetScoreViewModel;

import javax.swing.*;
import java.awt.*;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import interface_adapter.vet_score.VetScoreController;
import interface_adapter.vet_score.VetScoreState;
import interface_adapter.vet_score.VetScoreViewModel;

public class VetView extends JPanel implements PropertyChangeListener {

    private final String viewName = "vetView";
    private final VetScoreViewModel vetScoreViewModel;
    private VetScoreController vetScoreController;
    private final JLabel resultLabel;
    private final JLabel centerMessageLabel;
    private final JButton actionButton;
    private String redirectUrl = "https://www.petfinder.com/";
    private DefaultTableModel model;
    private JTable table;
    private JButton toScore;

    public VetView(VetScoreViewModel vetScoreViewModel) {
        this.vetScoreViewModel = vetScoreViewModel;
        this.vetScoreViewModel.addPropertyChangeListener(this);

        vetScoreViewModel.printListeners();

        setLayout(new BorderLayout());

        final String[] columnNames = {"Requirement", "Status"};
        model = new DefaultTableModel(columnNames, 0);

        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

        final JPanel buttons = new JPanel();
        toScore = new JButton("To Score");
        buttons.add(toScore);
        toScore.addActionListener(e -> {
            if (this.vetScoreController != null) {
                final VetScoreState vetScoreState = this.vetScoreViewModel.getState();
                this.vetScoreController.switchToScoreView(vetScoreState.getScore());
            }
            else {
                System.err.println(
                        "VetView: vetScoreController is null. Call setVetScoreController(...) before using this view.");
            }
        });

        // Status renderer
        table.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable resultTable, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(resultTable, value,
                        isSelected, hasFocus, row, column);

                String status = value == null ? "" : value.toString();
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
        final DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        centerMessageLabel = new JLabel("", SwingConstants.CENTER);
        centerMessageLabel.setFont(new Font("Arial", Font.BOLD, 18));

        actionButton = new JButton("Proceed");
        actionButton.setVisible(false);
        actionButton.addActionListener(e -> {
            if (redirectUrl != null && !redirectUrl.isBlank()) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(new URI(redirectUrl));
                    }
                    catch (IOException | URISyntaxException ex) {
                        // minimal error handling: print stack for debugging
                        ex.printStackTrace();
                    }
                }
            }
        });

        final JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        messagePanel.add(centerMessageLabel, BorderLayout.CENTER);

        JPanel buttonWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonWrapper.add(actionButton);
        messagePanel.add(buttonWrapper, BorderLayout.SOUTH);

        // Use a split pane so the message area appears between the table and south area
        final JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                new JScrollPane(table), messagePanel);
        splitPane.setResizeWeight(0.8);
        splitPane.setContinuousLayout(true);
        splitPane.setOneTouchExpandable(false);

        // ONLY add the split pane once into CENTER
        add(splitPane, BorderLayout.CENTER);

        // Result label at bottom
        resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        southPanel.add(resultLabel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        // Put the small "toScore" button panel in the NORTH (or another region)
        add(buttons, BorderLayout.NORTH);

        // Initialize table once on creation
        updateTable(this.vetScoreViewModel.getState().getRequirements());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("VetView: Property change received! Property: " + evt.getPropertyName());

        if ("state".equals(evt.getPropertyName())) {
            final VetScoreState state = (VetScoreState) evt.getNewValue();
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
            final String status = (row.size() > 1 && row.get(1) != null) ? row.get(1).trim() : "";
            if (!status.equalsIgnoreCase("Passed")) {
                allPassed = false;
                break;
            }
        }

        if (allPassed) {
            resultLabel.setText("Result: Passed");
            resultLabel.setForeground(new Color(0, 150, 0));

            // Set to correct URL
            if (vetScoreViewModel.getState().getCurrPet() != null) {
                setRedirectUrl(vetScoreViewModel.getState().getCurrPet().getApiPet().getUrl());
            }

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
            }
            else {
                actionButton.setVisible(false);
                actionButton.setEnabled(false);
            }
            revalidate();
            repaint();
        });
    }

    public String getViewName() {
        return viewName;
    }

    public void setVetScoreController(VetScoreController vetScoreController) {
        this.vetScoreController = vetScoreController;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}