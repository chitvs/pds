import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ClientListener implements ActionListener {
	public static final String CONNECT = "CONNECT";
	public static final String DISCONNECT = "DISCONNECT";
	public static final String EXECUTE = "EXECUTE";
	public static final String INTERRUPT = "INTERRUPT";
	public boolean connected;
	public boolean executing;
	private ClientFrame frame;
	private DownloadThread downloadThread;
	private Socket sock;
	private Scanner sockScanner;
	private PrintWriter sockWriter;

	public ClientListener(ClientFrame frame) {
		this.frame = frame;
	}

	private void setupConnection() throws UnknownHostException, IOException {
		String serverAddress = frame.headerPanel.serverField.getText();
		int serverPort = Integer.parseInt(frame.headerPanel.portField.getText());
		sock = new Socket(serverAddress, serverPort);
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
				connected = true;
				frame.centerPanel.consoleTextArea.setText("");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		case ClientListener.DISCONNECT:
			sockWriter.println(ClientListener.DISCONNECT);
			downloadThread.stop();
			executing = false;
			connected = false;
			try {
				sockWriter.close();
				sockScanner.close();
				sock.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;		
			
		case ClientListener.EXECUTE:
			executing = true;
			sockWriter.println(frame.footerPanel.commandField.getText());
			sockWriter.flush();
			downloadThread = new DownloadThread(frame, this, sockScanner);
			Thread tr1 = new Thread(downloadThread);
			tr1.start();
			break;

		case ClientListener.INTERRUPT:
			sockWriter.println(ClientListener.INTERRUPT);
			sockWriter.flush();
			downloadThread.stop();
			executing = false;
			break;		
		}
		frame.updateUI(connected, executing);
	}
	
	protected void downloadComplete() {
		executing = false;
		frame.updateUI(connected, executing);
	}
}
