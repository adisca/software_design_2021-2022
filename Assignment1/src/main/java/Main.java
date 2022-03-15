import GUI.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            /**
             * Starts a thread for the Swing graphical interface and completes the setup for the frame.
             */
            @Override
            public void run() {
                JFrame frame = new MainFrame("Food delivery management system");
                frame.setSize(500, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
