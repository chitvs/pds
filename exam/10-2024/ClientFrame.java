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
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

		ClientListener listener = new ClientListener(this);
		headerPanel.connectButton.setActionCommand(ClientListener.CONNECT);
		headerPanel.connectButton.addActionListener(listener);
		headerPanel.disconnectButton.setActionCommand(ClientListener.DISCONNECT);
		headerPanel.disconnectButton.addActionListener(listener);
		footerPanel.startButton.setActionCommand(ClientListener.START);
		footerPanel.startButton.addActionListener(listener);
		footerPanel.stopButton.setActionCommand(ClientListener.STOP);
		footerPanel.stopButton.addActionListener(listener);
	}

	protected void updateUI(boolean connected, boolean transmitting) {
		if (connected) {
			headerPanel.connectButton.setEnabled(false);
			headerPanel.disconnectButton.setEnabled(true);
			footerPanel.startButton.setEnabled(true);
			if (transmitting) {
				footerPanel.startButton.setEnabled(false);
				footerPanel.stopButton.setEnabled(true);
				headerPanel.disconnectButton.setEnabled(false);
			} else {
				headerPanel.disconnectButton.setEnabled(true);
				footerPanel.startButton.setEnabled(true);
				footerPanel.stopButton.setEnabled(false);
			}
		} else {
			headerPanel.connectButton.setEnabled(true);
			headerPanel.disconnectButton.setEnabled(false);
			footerPanel.startButton.setEnabled(false);
			footerPanel.stopButton.setEnabled(false);
		}
	}
}
