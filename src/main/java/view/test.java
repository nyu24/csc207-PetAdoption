package view;
import interface_adapter.buttons.ButtonsViewModel;

import javax.swing.*;

public class test {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                buttons_View buttons = new buttons_View(new ButtonsViewModel());
                buttons.setVisible(true);
            }
        });
    }
}
