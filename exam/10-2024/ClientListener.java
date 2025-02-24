import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ClientListener implements ActionListener {
	public static final String CONNECT = "CONNECT";
	public static final String DISCONNECT = "DISCONNECT";
	public static final String START = "START";
	public static final String STOP = "STOP";

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

	private void setupConnection() throws UnknownHostException, IOException {
		String a = frame.headerPanel.serverField.getText();
		int p = Integer.parseInt(frame.headerPanel.portField.getText());

		sock = new Socket(a, p);
		sockScanner = new Scanner(sock.getInputStream());
		sockWriter = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch (cmd) {
		case ClientListener.CONNECT:
			try {
				setupConnection();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			connected = true;
			frame.centerPanel.usaTextArea.setText("");
			frame.centerPanel.italiaTextArea.setText("");
			frame.centerPanel.logTextArea.setText("");
			break;
		case ClientListener.START:
			transmitting = true;
			sockWriter.println(ClientListener.START);
			sockWriter.flush();
			downloadThread = new DownloadThread(frame, this, sockScanner);
			Thread tr = new Thread(downloadThread);
			tr.start();
			break;
		case ClientListener.STOP:
			sockWriter.println(ClientListener.STOP);
			sockWriter.flush();
			downloadThread.stop();
			transmitting = false;
			break;
		case ClientListener.DISCONNECT:
			sockWriter.println(ClientListener.DISCONNECT);
			downloadThread.stop();
			transmitting = false;
			connected = false;
			sockWriter.close();
			sockScanner.close();
			try {
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
