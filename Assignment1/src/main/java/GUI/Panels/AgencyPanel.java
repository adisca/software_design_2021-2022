package GUI.Panels;

import GUI.MainFrame;
import GUI.Util.DateLabelFormatter;
import controller.AgencyController;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Properties;

public class AgencyPanel extends JPanel {
    public AgencyPanel(MainFrame mainFrame) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("name");
        JTable tableDestinations = new JTable(model);
        tableDestinations.setDefaultEditor(Object.class, null);

        model = new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("name");
        model.addColumn("price");
        model.addColumn("periodStart");
        model.addColumn("periodEnd");
        model.addColumn("extraDetails");
        model.addColumn("maxPeople");
        model.addColumn("destination");
        JTable tablePackages = new JTable(model);
        tablePackages.setDefaultEditor(Object.class, null);

        JPanel destinationPanel = new JPanel();
        destinationPanel.setLayout(new GridBagLayout());
        JPanel packagePanel = new JPanel();
        packagePanel.setLayout(new GridBagLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Destination", destinationPanel);
        tabbedPane.addTab("Package", packagePanel);


        JTextField destinationName = new JTextField(10);
        JButton addDestination = new JButton("Add");
        JButton deleteDestination = new JButton("Delete");

        JTextField packageName = new JTextField(10);
        JTextField packagePrice = new JTextField(10);

        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePickerImpl packagePeriodStart = new JDatePickerImpl(new JDatePanelImpl(new SqlDateModel(), p),
                new DateLabelFormatter());
        packagePeriodStart.setTextEditable(true);
        JDatePickerImpl packagePeriodEnd = new JDatePickerImpl(new JDatePanelImpl(new SqlDateModel(), p),
                new DateLabelFormatter());
        packagePeriodEnd.setTextEditable(true);

        JTextArea packageDetails = new JTextArea(3, 10);
        JTextField packageMaxPeople = new JTextField(10);
        JButton addPackage = new JButton("Add");
        JButton updatePackage = new JButton("Update");
        JButton deletePackage = new JButton("Delete");
        JButton viewAllPackage = new JButton("View all");

        setLayout(new BorderLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        ///// Layout ////////////////////////////////////////////////

        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;

        // destination panel
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        destinationPanel.add(new JLabel("Name: "), gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        destinationPanel.add(destinationName, gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        destinationPanel.add(addDestination, gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        destinationPanel.add(deleteDestination, gridBagConstraints);

        // package panel
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        packagePanel.add(new JLabel("Name: "), gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        packagePanel.add(packageName, gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        packagePanel.add(new JLabel("Price: "), gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        packagePanel.add(packagePrice, gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        packagePanel.add(new JLabel("Period Start: "), gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        packagePanel.add(packagePeriodStart, gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        packagePanel.add(new JLabel("Period End: "), gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        packagePanel.add(packagePeriodEnd, gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        packagePanel.add(new JLabel("Extra Details: "), gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        packagePanel.add(packageDetails, gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        packagePanel.add(new JLabel("Max People: "), gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        packagePanel.add(packageMaxPeople, gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        packagePanel.add(addPackage, gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        packagePanel.add(updatePackage, gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        packagePanel.add(deletePackage, gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        packagePanel.add(viewAllPackage, gridBagConstraints);


        add(new JScrollPane(tableDestinations), BorderLayout.WEST);
        add(new JScrollPane(tablePackages), BorderLayout.EAST);
        add(tabbedPane, BorderLayout.CENTER);

        ///// Controllers /////////////////////////////////////////

        AgencyController controller = new AgencyController(tableDestinations, tablePackages, destinationName,
                packageName, packagePrice, packagePeriodStart, packagePeriodEnd, packageDetails, packageMaxPeople);

        addDestination.addActionListener(e -> controller.addDestination());
        deleteDestination.addActionListener(e -> controller.deleteDestination());

        addPackage.addActionListener(e -> controller.addPackage());
        updatePackage.addActionListener(e -> controller.updatePackage());
        deletePackage.addActionListener(e -> controller.deletePackage());
        viewAllPackage.addActionListener(e -> controller.viewAllPackages());

        tableDestinations.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (tableDestinations.getSelectedRow() != -1) {
                        controller.filterByDestination(
                                (String) tableDestinations.getValueAt(tableDestinations.getSelectedRow(), 0));
                    }
                }
            }
        });
        tablePackages.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (tablePackages.getSelectedRow() != -1) {
                        packageName.setText((String) tablePackages.getValueAt(tablePackages.getSelectedRow(), 1));
                        packagePrice.setText((String) tablePackages.getValueAt(tablePackages.getSelectedRow(), 2));
                        Date date = Date.valueOf((String) tablePackages.getValueAt(tablePackages.getSelectedRow(), 3));
                        packagePeriodStart.getModel().setDate(date.getYear() + 1900, date.getMonth(), date.getDate());
                        date = Date.valueOf((String) tablePackages.getValueAt(tablePackages.getSelectedRow(), 4));
                        packagePeriodEnd.getModel().setDate(date.getYear() + 1900, date.getMonth(), date.getDate());
                        packageDetails.setText((String) tablePackages.getValueAt(tablePackages.getSelectedRow(), 5));
                        packageMaxPeople.setText((String) tablePackages.getValueAt(tablePackages.getSelectedRow(), 6));
                    }
                }
            }
        });
    }
}
