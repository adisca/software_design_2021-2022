package controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import GUI.MainFrame;
import GUI.Util.LogInActionCommand;
import GUI.Util.PanelType;
import service.LogInService;

public class LogInController implements ActionListener {
    private MainFrame mainFrame;

    private final JTextField textFieldUsername;
    private final JPasswordField passwordField;
    private final LogInService service;

    public LogInController(JTextField textFieldUsername, JPasswordField passwordField, MainFrame mainFrame) {
        this.textFieldUsername = textFieldUsername;
        this.passwordField = passwordField;
        this.service = new LogInService();
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        String username = textFieldUsername.getText();
        // Security alert, but I store unencrypted passwords in the db, so who cares
        String password = String.valueOf(passwordField.getPassword());

        if (Objects.equals(command, LogInActionCommand.LOG_IN.name())) {
            try {
                service.logIn(username, password);
                JOptionPane.showMessageDialog(null, "Success");
                mainFrame.changePanel(PanelType.CLIENT);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }

        } else if (Objects.equals(command, LogInActionCommand.REGISTER.name())) {
            try {
                service.register(username, password);
                JOptionPane.showMessageDialog(null, "Success");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }

        } else if (Objects.equals(command, LogInActionCommand.AGENCY.name())) {
            mainFrame.changePanel(PanelType.AGENCY);
        }
    }
}
