import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class ClientListener implements ActionListener {

	public static final String ARTISTI = "ARTISTI";
	public static final String CLASSIFICA = "CLASSIFICA";
	public static final String STOP = "STOP";
	public static final String CONNECT = "CONNECT";
	public static final String DISCONNECT = "DISCONNECT";

	public boolean connected;
	public boolean transmitting;
	private ClientFrame frame;
	private DownloadThread downloadThread;
	private Socket sock;
	private Scanner sockScanner;
	private PrintWriter sockWriter;

	public ClientListener(ClientFrame frame) {
		this.frame = frame;
	}

	private void setupConnection() {
		String serverAddress = frame.headerPanel.serverField.getText();
		int serverPort = Integer.parseInt(frame.headerPanel.portField.getText());
		try {
			sock = new Socket(serverAddress, serverPort);
			sockScanner = new Scanner(sock.getInputStream());
			sockWriter = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch (cmd) {
		case ClientListener.CONNECT:
			setupConnection();
			connected = true;
			frame.centerPanel.logTextArea.setText("");
			frame.centerPanel.classificaTextArea.setText("");
			frame.centerPanel.artistiTextArea.setText("");
			break;
		case ClientListener.ARTISTI:
			frame.centerPanel.logTextArea.setText("");
			frame.centerPanel.classificaTextArea.setText("");
			frame.centerPanel.artistiTextArea.setText("");
			transmitting = true;
			sockWriter.println(ClientListener.ARTISTI);
			sockWriter.flush();
			downloadThread = new DownloadThread(frame, this, sockScanner);
			Thread tr1 = new Thread(downloadThread);
			tr1.start();
			frame.artistiReceived = true;
			break;
		case ClientListener.CLASSIFICA:
			transmitting = true;
			sockWriter.println(ClientListener.CLASSIFICA);
			sockWriter.flush();
			downloadThread = new DownloadThread(frame, this, sockScanner);
			Thread tr2 = new Thread(downloadThread);
			tr2.start();
			frame.classificaReceived = true;
			break;
		case ClientListener.STOP:
			sockWriter.println(ClientListener.STOP);
			sockWriter.flush();
			downloadThread.stop();
			transmitting = false;
			frame.artistiReceived = false;
			frame.classificaReceived = false;
			break;
		case ClientListener.DISCONNECT:
			sockWriter.println(ClientListener.DISCONNECT);
			downloadThread.stop();
			transmitting = false;
			connected = false;
			frame.artistiReceived = false;
			frame.classificaReceived = false;
			try {
				sockWriter.close();
				sockScanner.close();
				sock.close();
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
}
