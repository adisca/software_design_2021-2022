package GUI;

import GUI.Panels.AgencyPanel;
import GUI.Panels.ClientPanel;
import GUI.Panels.LogInPanel;
import GUI.Util.PanelType;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final LogInPanel logInPanel = new LogInPanel(this);
    private final ClientPanel clientPanel = new ClientPanel(this);
    private final AgencyPanel agencyPanel = new AgencyPanel(this);

    public MainFrame(String title) {
        super(title);

        setLayout(new BorderLayout());

        Container container = getContentPane();
        container.add(logInPanel, BorderLayout.CENTER);
    }

    public void changePanel(PanelType type) {
        Container container = getContentPane();
        container.removeAll();

        switch (type) {
            case LOG_IN:
                container.add(logInPanel, BorderLayout.CENTER);
                break;
            case AGENCY:
                container.add(agencyPanel, BorderLayout.CENTER);
                break;
            case CLIENT:
                container.add(clientPanel, BorderLayout.CENTER);
                break;
        }
    }
}
