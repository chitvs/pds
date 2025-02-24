import java.util.*;

public class DownloadThread implements Runnable {
	private boolean running;
	private boolean errored;
	private ClientFrame frame;
	private ClientListener listener;
	private Scanner scanner;	

	public DownloadThread(ClientFrame frame, ClientListener listener, Scanner scanner) {
		this.frame = frame;
		this.listener = listener;
		this.scanner = scanner;
	}

	@Override
	public void run() {
		if (running)
			return;
		running = true;
		while (running) {
			String cmd = scanner.nextLine();
			if (cmd.equalsIgnoreCase("END")) {
				frame.centerPanel.consoleTextArea.append("======DOWNLOAD COMPLETATO======\n");
				running = false;
			}
			else if (cmd.equalsIgnoreCase("INTERRUPTED")) {
				running = false;
				frame.centerPanel.consoleTextArea.append("======DOWNLOAD INTERROTTO======\n");
			}
			else if (cmd.equalsIgnoreCase("ERROR")) {
				errored = true;
				running = false;
			}
			else {
				frame.centerPanel.consoleTextArea.append(cmd + "\n");
			}
		}
		listener.downloadComplete();
	}

	public void stop() {
		if (!running)
			return;
		running = false;
	}
}
