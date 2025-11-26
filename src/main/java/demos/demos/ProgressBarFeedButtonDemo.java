package demos.demos;

import demos.entities.TestingPet;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgressBarFeedButtonDemo {
    public static void main(String[] args) {

        TestingPet p = new TestingPet(100, 100, 100, 100);



        SwingUtilities.invokeLater(() -> {

            // Creating panel and labels

            // timer
            JPanel timerPanel = new JPanel();
            JLabel timerText = new JLabel("Time Left:");
            JLabel timerLabel = new JLabel("...");
            timerPanel.add(timerText);
            timerPanel.add(timerLabel);

            // progressbar
            JPanel progressbarPanel = new JPanel();
            JProgressBar progressBar = new JProgressBar();
            progressbarPanel.add(progressBar);
            progressBar.setMaximum((int)(p.getMaxHunger()));
            progressBar.setStringPainted(true);


            // button
            JPanel buttonPanel = new JPanel();
            JButton button = new JButton("Feed");
            buttonPanel.add(button);
            // check button press
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    p.setHunger(p.getHunger() + 10);
                }
            });


            int delay = 16; // 62.5Hz

            // main loop

            ActionListener mainLoop = new ActionListener() {
                int counter = 10;
                int secondDelay = 1;
                public void actionPerformed(ActionEvent e) {

                    // logic inside loop goes here

                    // print to console
                    // System.out.println(counter);
                    // change text

                    // progressbar logic
                    progressBar.setValue((int)p.getHunger());

                    // timer
                    timerLabel.setText(Integer.toString(counter));


                    // game end screen
                    if(counter == 0) {
                        // note that popup stops main loop until closed
                        String message = "Time's up! \n Hunger is " + (int)p.getHunger();
                        if(p.getHunger() >= p.getMaxHunger()/2) {
                            message += "\n You Win :)";
                        }
                        else{
                            message += "\n You Lose :(";
                        }
                        JOptionPane.showMessageDialog(null, message);
                        counter = -1;
                    }

                    // timer logic
                    if(counter > 0 && secondDelay % 60 == 0){
                        counter--;
                    }
                    if (secondDelay % 60 == 0){
                        secondDelay -= 60;
                    }
                    secondDelay++;

                    // tick hunger
                    p.setHunger(p.getHunger() - 1);
                    if(p.getHunger() > 100) {
                        p.setHunger(100);
                    }
                    if(p.getHunger() < 0) {
                        p.setHunger(0);
                    }
                }
            };

            // timer object
            new Timer(delay, mainLoop).start();

            // add stuff to main panel
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.add(timerPanel);
            mainPanel.add(progressbarPanel);
            mainPanel.add(buttonPanel);

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
