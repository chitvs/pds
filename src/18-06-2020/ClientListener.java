import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class ClientListener implements ActionListener {
	public static final String CONNECT = "CONNECT";
	public static final String DISCONNECT = "disconnect";
	public static final String START = "start";
	public static final String STOP = "stop";
	public static final String RIVELA = "rivela";

	private DownloadThread downloadThread;
	public boolean connected;
	public boolean transmitting;
	private ClientFrame frame;

	private Socket sock;
	private Scanner sockScanner;
	private PrintWriter sockWriter;
	private JPanel footerPanel;

	public ClientListener(ClientFrame frame) {
		this.frame = frame;
	}

	private void setupConnection() throws IOException {
		try {
			String serverAddress = frame.headerPanel.serverField.getText();
			int serverPort = Integer.parseInt(frame.headerPanel.portField.getText());
			sock = new Socket(serverAddress, serverPort);
			sockScanner = new Scanner(sock.getInputStream());
			sockWriter = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
		} catch (NumberFormatException e) {
			throw new IOException("Formato porta errato");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch (cmd) {
		case ClientListener.CONNECT:
			try {
				setupConnection();
				connected = true;
				frame.board.resetGame();
				frame.board.setGameActive(false);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		case ClientListener.START:
			transmitting = true;
			sockWriter.println(ClientListener.START);
			sockWriter.flush();
			frame.board.resetGame();
			downloadThread = new DownloadThread(frame, this, sockScanner);
			Thread tr1 = new Thread(downloadThread);
			tr1.start();
			break;
		case ClientListener.STOP:
			sockWriter.println(ClientListener.STOP);
			sockWriter.flush();
			downloadThread.stop();
			transmitting = false;
			break;
		case ClientListener.RIVELA:
			frame.board.revealBoard();
			frame.board.setGameActive(false);
			break;
		case ClientListener.DISCONNECT:
			sockWriter.println(ClientListener.DISCONNECT);
			downloadThread.stop();
			transmitting = false;
			connected = false;
			try {
				sock.close();
				sockWriter.close();
				sockScanner.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		}
		frame.updateUI(connected, transmitting);
	}

	protected void downloadComplete() {
		transmitting = false;
		frame.updateUI(connected, transmitting);
	}

	protected void disconnect() {
		// Simulate disconnect button press
		frame.headerPanel.disconnectButton.doClick();
	}
}
