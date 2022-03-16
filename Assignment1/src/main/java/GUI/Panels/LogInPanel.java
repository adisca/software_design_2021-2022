package GUI.Panels;

import GUI.MainFrame;
import controller.LogInController;

import javax.swing.*;
import java.awt.*;

public class LogInPanel extends JPanel {

    public LogInPanel(MainFrame mainFrame) {
        JTextField textFieldUsername = new JTextField(10);
        JPasswordField passwordField = new JPasswordField(10);

        JButton buttonLogIn = new JButton("Log in");
        JButton buttonRegister = new JButton("Register");
        JButton buttonAgency = new JButton("Agency");

        setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        ///// Layout ////////////////////////////////////////////////

        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;

        // 1st row

        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(new JLabel("Username: "), gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        add(textFieldUsername, gridBagConstraints);

        // 2nd row

        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(new JLabel("Password: "), gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        add(passwordField, gridBagConstraints);

        // 3rd row

        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        add(buttonLogIn, gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        add(buttonRegister, gridBagConstraints);

        // 4rd row

        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        add(buttonAgency, gridBagConstraints);

        ///// Controllers /////////////////////////////////////////

        LogInController controller = new LogInController(textFieldUsername, passwordField, mainFrame);

        buttonLogIn.addActionListener(e -> controller.logIn());
        buttonRegister.addActionListener(e ->controller.register());
        buttonAgency.addActionListener(e -> controller.agency());
    }
}
