import java.util.*;

public class DownloadThread implements Runnable {
	private boolean running;
	private boolean errored;
	private ClientFrame frame;
	private ClientListener listener;
	private Scanner scanner;
	private boolean dopp;

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
			dopp = false;
			String cmd = scanner.nextLine();
			if (cmd.equalsIgnoreCase("START")) {
				running = true;
			} else if (cmd.equalsIgnoreCase("END") || cmd.equalsIgnoreCase("INTERRUPTED")) {
				running = false;
				frame.centerPanel.logTextArea.append(cmd + "\n");
			} else if (cmd.equalsIgnoreCase("ERROR")) {
				running = false;
				errored = true;
				frame.centerPanel.logTextArea.append(cmd + "\n");
			} else {
				String[] m = cmd.split(":");
				String[] past = frame.centerPanel.logTextArea.getText().split("\n");
				for (String elem : past) {
					if (cmd.equalsIgnoreCase(elem)) {
						dopp = true;
						break;
					}
				}
				if (m[0].equalsIgnoreCase("USA")) {
					if (dopp) {
						frame.centerPanel.usaTextArea.append(m[1] + " dopp.\n");
					} else {
						frame.centerPanel.usaTextArea.append(m[1] + "\n");
					}
				} else if (m[0].equalsIgnoreCase("ITALIA")) {
					if (dopp) {
						frame.centerPanel.italiaTextArea.append(m[1] + " dopp.\n");
					} else {
						frame.centerPanel.italiaTextArea.append(m[1] + "\n");
					}
				}
				frame.centerPanel.logTextArea.append(cmd + "\n");
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
