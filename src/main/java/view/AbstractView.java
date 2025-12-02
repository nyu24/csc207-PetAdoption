package view;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

public abstract class AbstractView extends JPanel implements ActionListener, PropertyChangeListener {

    public AbstractView() {
        initalizeView();
    }

    private void initalizeView() {
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
