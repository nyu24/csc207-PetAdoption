package view;

import javax.swing.*;

public class MainGameScreen extends JPanel {
    private JProgressBar foodProgressBar;
    private JProgressBar cleanProgressBar;
    private JProgressBar thirstProgressBar;
    private JButton feedButton;
    private JButton drinkButton;
    private JButton cleanButton;
    private int startFood;
    private int startClean;
    private int startThirst;


    public MainGameScreen(int startFood, int startClean, int startThirst) {
        this.startFood = startFood;
        this.startClean = startClean;
        this.startThirst = startThirst;
        this.foodProgressBar = new JProgressBar(0, 100);
        this.foodProgressBar.setValue(startFood);
        this.cleanProgressBar = new JProgressBar(0, 100);
        this.cleanProgressBar.setValue(startClean);
        this.thirstProgressBar = new JProgressBar(0, 100);
        this.thirstProgressBar.setValue(startThirst);
        this.feedButton = new JButton("Food");
        this.drinkButton = new JButton("Water");
        this.cleanButton = new JButton("Clean");


    }
    public void Main()
    {
        JPanel meterPanel = new JPanel();
        meterPanel.setLayout(new BoxLayout(meterPanel, BoxLayout.Y_AXIS));
        meterPanel.add(feedButton);
        meterPanel.add(new JLabel("Hunger"));
        meterPanel.add(foodProgressBar);

    }
}