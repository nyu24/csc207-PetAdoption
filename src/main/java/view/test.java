package view;
import interface_adapter.buttons_viewModel;
import view.buttons_View;

import javax.swing.*;

public class test {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                buttons_View buttons = new buttons_View(new buttons_viewModel());
                buttons.setVisible(true);
            }
        });
    }
}
