import java.awt.*;
import javax.swing.*;

public class ClientFrame extends JFrame {
	HeaderPanel headerPanel;
	CenterPanel centerPanel;
	FooterPanel footerPanel;
	
	public ClientFrame(String title) {
		super(title);
		headerPanel = new HeaderPanel();
		centerPanel = new CenterPanel();
		footerPanel = new FooterPanel();
		
		this.add(headerPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(footerPanel, BorderLayout.SOUTH);
		
		updateUI(false, false);
		pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
		ClientListener listener = new ClientListener(this);
		headerPanel.connectButton.setActionCommand(ClientListener.CONNECT);
		headerPanel.connectButton.addActionListener(listener);
		headerPanel.disconnectButton.setActionCommand(ClientListener.DISCONNECT);
		headerPanel.disconnectButton.addActionListener(listener);
		footerPanel.executeButton.setActionCommand(ClientListener.EXECUTE);
		footerPanel.executeButton.addActionListener(listener);
		footerPanel.interruptButton.setActionCommand(ClientListener.INTERRUPT);
		footerPanel.interruptButton.addActionListener(listener);	
	}

	protected void updateUI(boolean connected, boolean executing) {
		if (connected) {
			headerPanel.connectButton.setEnabled(false);
			headerPanel.disconnectButton.setEnabled(true);
			footerPanel.executeButton.setEnabled(true);
			if (executing) {
				footerPanel.interruptButton.setEnabled(true);
				headerPanel.disconnectButton.setEnabled(false);
				footerPanel.executeButton.setEnabled(false);
			}
			else {
				footerPanel.interruptButton.setEnabled(false);
			}
		}
		else {
			headerPanel.connectButton.setEnabled(true);
			headerPanel.disconnectButton.setEnabled(false);
			footerPanel.executeButton.setEnabled(false);
			footerPanel.interruptButton.setEnabled(false);
		}
	}
}
