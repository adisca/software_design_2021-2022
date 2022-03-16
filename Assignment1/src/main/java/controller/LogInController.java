package controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.MainFrame;
import GUI.Util.PanelType;
import service.LogInService;

public class LogInController implements ActionListener {
    private final MainFrame mainFrame;
    private final JTextField textFieldUsername;
    private final JPasswordField passwordField;
    private final LogInService service = new LogInService();

    public LogInController(JTextField textFieldUsername, JPasswordField passwordField, MainFrame mainFrame) {
        this.textFieldUsername = textFieldUsername;
        this.passwordField = passwordField;
        this.mainFrame = mainFrame;
    }

    public void logIn() {
        String username = textFieldUsername.getText();
        // Security alert, but I store unencrypted passwords in the db, so who cares
        String password = String.valueOf(passwordField.getPassword());

        try {
            service.logIn(username, password);
            JOptionPane.showMessageDialog(null, "Success");
            mainFrame.changePanel(PanelType.CLIENT);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }

    public void register() {
        String username = textFieldUsername.getText();
        // Security alert, but I store unencrypted passwords in the db, so who cares
        String password = String.valueOf(passwordField.getPassword());

        try {
            service.register(username, password);
            JOptionPane.showMessageDialog(null, "Success");
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }

    public void agency() {
        mainFrame.changePanel(PanelType.AGENCY);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
    }
}
