package controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import GUI.Util.LogInActionCommand;
import service.LogInService;

public class LogInController implements ActionListener {
    private final JTextField textFieldUsername;
    private final JPasswordField passwordField;
    private LogInService service;

    public LogInController(JTextField textFieldUsername, JPasswordField passwordField) {
        this.textFieldUsername = textFieldUsername;
        this.passwordField = passwordField;
        this.service = new LogInService();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String username = textFieldUsername.getText();
        // Security alert, but I store unencrypted passwords in the db, so who cares
        String password = String.valueOf(passwordField.getPassword());

        if (Objects.equals(command, LogInActionCommand.LOG_IN.name())) {
            JOptionPane.showMessageDialog(null, "Logged in");
        } else if (Objects.equals(command, LogInActionCommand.REGISTER.name())) {
            JOptionPane.showMessageDialog(null, "Registered");
        }
        else if (Objects.equals(command, LogInActionCommand.AGENCY.name())) {
            JOptionPane.showMessageDialog(null, "Agency");
        }
    }
}
