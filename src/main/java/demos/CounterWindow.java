package demos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CounterWindow {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            // Creating panel and labels

            JPanel timerPanel = new JPanel();
            JLabel timerText = new JLabel("Time Left:");
            JLabel timerLabel = new JLabel("...");
            timerPanel.add(timerText);
            timerPanel.add(timerLabel);

            int delay = 1000; // milliseconds

            // main loop

            ActionListener mainLoop = new ActionListener() {
                int counter = 10;
                public void actionPerformed(ActionEvent e) {

                    // logic inside loop goes here

                    // print to console
                    System.out.println(counter);
                    // change text
                    timerLabel.setText(Integer.toString(counter));

                    if(counter == 0){

                        // note that popup stops main loop until closed
                        JOptionPane.showMessageDialog(null, "Time's up");
                        counter = -1;
                    }
                    if(counter > 0){
                        counter--;
                    }
                }
            };

            // timer object
            new Timer(delay, mainLoop).start();

            // add stuff to main panel
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.add(timerPanel);

            // display main frame

            JFrame frame = new JFrame("pet game");
            frame.setContentPane(mainPanel);

            frame.setMinimumSize(new java.awt.Dimension(300, 50));
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            });
    }
}
