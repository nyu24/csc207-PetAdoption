package view;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

/**
 * Template view to be used by the other views.
 */
public abstract class AbstractView extends JPanel implements ActionListener, PropertyChangeListener {

    public AbstractView() {
        initializeView();
    }

    private void initializeView() {
        setupLayout();
        createComponents();
        addComponents();
        addListeners();

    }

    protected abstract void setupLayout();

    protected abstract void createComponents();

    protected abstract void addComponents();

    protected abstract void addListeners();

}
