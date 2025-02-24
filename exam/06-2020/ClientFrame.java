import java.awt.*;
import javax.swing.*;

public class ClientFrame extends JFrame {
	HeaderPanel headerPanel;
	Board board;
	FooterPanel footerPanel;
	boolean connected;
	boolean transmitting;

	public ClientFrame(String title) {
		super(title);

		headerPanel = new HeaderPanel();
		board = new Board();
		footerPanel = new FooterPanel();

		ClientListener listener = new ClientListener(this);
		headerPanel.connectButton.setActionCommand(ClientListener.CONNECT);
		headerPanel.connectButton.addActionListener(listener);
		headerPanel.disconnectButton.setActionCommand(ClientListener.DISCONNECT);
		headerPanel.disconnectButton.addActionListener(listener);
		footerPanel.startButton.setActionCommand(ClientListener.START);
		footerPanel.startButton.addActionListener(listener);
		footerPanel.stopButton.setActionCommand(ClientListener.STOP);
		footerPanel.stopButton.addActionListener(listener);
		footerPanel.rivelaButton.setActionCommand(ClientListener.RIVELA);
		footerPanel.rivelaButton.addActionListener(listener);

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				BoardButton button = board.getButton(i, j);
				button.setActionCommand(Board.BUTTON);
				button.addActionListener(board);
			}
		}

		add(headerPanel, BorderLayout.NORTH);
		add(board, BorderLayout.CENTER);
		add(footerPanel, BorderLayout.SOUTH);

		updateUI(false, false);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	protected void updateUI(boolean connected, boolean transmitting) {
		if (connected) {
			headerPanel.connectButton.setEnabled(false);
			if (transmitting) {
				headerPanel.disconnectButton.setEnabled(false);
				footerPanel.startButton.setEnabled(false);
				footerPanel.rivelaButton.setEnabled(false);
				footerPanel.stopButton.setEnabled(true);
			} else {
				headerPanel.disconnectButton.setEnabled(true);
				footerPanel.startButton.setEnabled(true);
				footerPanel.rivelaButton.setEnabled(true);
				footerPanel.stopButton.setEnabled(false);
			}
		} else {
			headerPanel.connectButton.setEnabled(true);
			headerPanel.disconnectButton.setEnabled(false);
			footerPanel.startButton.setEnabled(false);
			footerPanel.stopButton.setEnabled(false);
			footerPanel.rivelaButton.setEnabled(false);
		}
	}
}
