package GUI.Panels;

import GUI.MainFrame;
import GUI.Util.LogInActionCommand;
import controller.LogInController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AgencyPanel extends JPanel {
    public AgencyPanel(MainFrame mainFrame) {
        JTable tableDestinations = new JTable();
        tableDestinations.setModel(new DefaultTableModel());
        JTable tablePackages = new JTable();
        tablePackages.setModel(new DefaultTableModel());



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

    }
}
