package controller;

import model.VacationDestination;
import org.jdatepicker.impl.JDatePickerImpl;
import service.AgencyService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Vector;

public class AgencyController implements ActionListener {
    private final JTable tableDestinations;
    private final JTable tablePackages;
    private final JTextField destinationName;
    private final JTextField packageName;
    private final JTextField packagePrice;
    private final JDatePickerImpl packagePeriodStart;
    private final JDatePickerImpl packagePeriodEnd;
    private final JTextArea packageDetails;
    private final JTextField packageMaxPeople;
    private final AgencyService service = new AgencyService();

    public AgencyController(JTable tableDestinations, JTable tablePackages, JTextField destinationName,
                            JTextField packageName, JTextField packagePrice, JDatePickerImpl packagePeriodStart,
                            JDatePickerImpl packagePeriodEnd, JTextArea packageDetails, JTextField packageMaxPeople) {
        this.tableDestinations = tableDestinations;
        this.tablePackages = tablePackages;
        this.destinationName = destinationName;
        this.packageName = packageName;
        this.packagePrice = packagePrice;
        this.packagePeriodStart = packagePeriodStart;
        this.packagePeriodEnd = packagePeriodEnd;
        this.packageDetails = packageDetails;
        this.packageMaxPeople = packageMaxPeople;
        updateDestinationsTable();
        viewAllPackages();
    }

    public void updateDestinationsTable() {
        DefaultTableModel model = (DefaultTableModel) tableDestinations.getModel();
        model.setRowCount(0);
        for (Vector<String> destination : service.viewAllDestinations()) {
            model.addRow(destination);
        }
    }

    public void filterByDestination(String destID) {
        DefaultTableModel model = (DefaultTableModel) tablePackages.getModel();
        model.setRowCount(0);
        for (Vector<String> aPackage : service.viewPackageOfDestination(destID)) {
            model.addRow(aPackage);
        }
    }

    public void addDestination() {
        String dstName = destinationName.getText();
        service.addDestination(dstName);
        updateDestinationsTable();
    }

    public void deleteDestination() {
        if (tableDestinations.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Select destination from table");
            return;
        }
        service.deleteDestination((String) tableDestinations.getValueAt(tableDestinations.getSelectedRow(), 0));
        updateDestinationsTable();
        viewAllPackages();
    }

    public void addPackage() {
        if (tableDestinations.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Select destination from table");
            return;
        }
        String pkgName = packageName.getText();
        String pkgPrice = packagePrice.getText();
        String pkgPeriodS = packagePeriodStart.getModel().getValue().toString();
        String pkgPeriodE = packagePeriodEnd.getModel().getValue().toString();
        String pkgDetails = packageDetails.getText();
        String pkgMaxPeople = packageMaxPeople.getText();

        service.addPackage(pkgName, pkgPrice, pkgPeriodS, pkgPeriodE, pkgDetails, pkgMaxPeople,
                (String) tableDestinations.getValueAt(tableDestinations.getSelectedRow(), 0));
        viewAllPackages();
    }

    public void updatePackage() {
        if (tableDestinations.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Select destination from table");
            return;
        }
        if (tablePackages.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Select package from table");
            return;
        }
        String pkgName = packageName.getText();
        String pkgPrice = packagePrice.getText();
        String pkgPeriodS = packagePeriodStart.getModel().getValue().toString();
        String pkgPeriodE = packagePeriodEnd.getModel().getValue().toString();
        String pkgDetails = packageDetails.getText();
        String pkgMaxPeople = packageMaxPeople.getText();

        service.updatePackage((String) tablePackages.getValueAt(tablePackages.getSelectedRow(), 0),
                pkgName, pkgPrice, pkgPeriodS, pkgPeriodE, pkgDetails, pkgMaxPeople,
                (String) tableDestinations.getValueAt(tableDestinations.getSelectedRow(), 0));
        viewAllPackages();
    }

    public void deletePackage() {
        if (tablePackages.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Select package from table");
            return;
        }
        service.deletePackage((String) tablePackages.getValueAt(tablePackages.getSelectedRow(), 0));
        viewAllPackages();
    }

    public void viewAllPackages() {
        DefaultTableModel model = (DefaultTableModel) tablePackages.getModel();
        model.setRowCount(0);
        for (Vector<String> aPackage : service.viewAllPackages()) {
            model.addRow(aPackage);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
    }

}
