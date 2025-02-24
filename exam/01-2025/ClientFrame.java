import java.awt.*;
import javax.swing.*;

public class ClientFrame extends JFrame {
	HeaderPanel headerPanel;
	CenterPanel centerPanel;
	FooterPanel footerPanel;
	boolean artistiReceived;
	boolean classificaReceived;

	public ClientFrame(String title) {
		super(title);

		headerPanel = new HeaderPanel();
		centerPanel = new CenterPanel();
		footerPanel = new FooterPanel();

		add(headerPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(footerPanel, BorderLayout.SOUTH);

		updateUI(false, false);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

		ClientListener listener = new ClientListener(this);
		headerPanel.connectButton.setActionCommand(ClientListener.CONNECT);
		headerPanel.connectButton.addActionListener(listener);
		headerPanel.disconnectButton.setActionCommand(ClientListener.DISCONNECT);
		headerPanel.disconnectButton.addActionListener(listener);
		footerPanel.artistiButton.setActionCommand(ClientListener.ARTISTI);
		footerPanel.artistiButton.addActionListener(listener);
		footerPanel.classificaButton.setActionCommand(ClientListener.CLASSIFICA);
		footerPanel.classificaButton.addActionListener(listener);
		footerPanel.stopButton.setActionCommand(ClientListener.STOP);
		footerPanel.stopButton.addActionListener(listener);
	}

	protected void updateUI(boolean connected, boolean transmitting) {
		if (connected) {
			headerPanel.connectButton.setEnabled(false);
			if (transmitting) {
				headerPanel.disconnectButton.setEnabled(false);
				footerPanel.artistiButton.setEnabled(false);
				footerPanel.classificaButton.setEnabled(false);
				footerPanel.stopButton.setEnabled(true);
			} else {
				headerPanel.disconnectButton.setEnabled(true);
				footerPanel.stopButton.setEnabled(false);
				if (artistiReceived && !classificaReceived) {
					footerPanel.artistiButton.setEnabled(false);
					footerPanel.classificaButton.setEnabled(true);
				} else {
					if (artistiReceived && classificaReceived) {
						footerPanel.artistiButton.setEnabled(false);
						footerPanel.classificaButton.setEnabled(false);
					} else {
						footerPanel.artistiButton.setEnabled(true);
						footerPanel.classificaButton.setEnabled(false);
					}
				}
			}
		} else {
			headerPanel.connectButton.setEnabled(true);
			headerPanel.disconnectButton.setEnabled(false);
			footerPanel.artistiButton.setEnabled(false);
			footerPanel.classificaButton.setEnabled(false);
			footerPanel.stopButton.setEnabled(false);
		}
	}
}
