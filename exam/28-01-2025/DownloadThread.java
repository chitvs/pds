import java.util.*;

public class DownloadThread implements Runnable {

	private boolean running;
	private boolean errored;
	private ClientFrame frame;
	private ClientListener listener;
	private Scanner scanner;
	private TreeSet nuoviElementi = new TreeSet<>();

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

			if (cmd.equalsIgnoreCase("END") || cmd.equalsIgnoreCase("INTERRUPTED")) {
				running = false;
			} else if (cmd.equalsIgnoreCase("ERROR")) {
				running = false;
				errored = true;
			} else {
				String[] tokens = cmd.split(":");
				if (tokens[0].equalsIgnoreCase("ARTISTA")) {
					frame.centerPanel.artistiTextArea.append(tokens[1] + "\n");
				} else if (tokens[0].equalsIgnoreCase("CLASSIFICA")) {
					String artista = tokens[1];
					Integer classifica = Integer.parseInt(tokens[2]);
					String[] elementi = frame.centerPanel.artistiTextArea.getText().split("\n");
					for (int i = 0; i < elementi.length; i++) {
						String[] artNomi = elementi[i].split(" ");
						String iniziali = String.valueOf(artNomi[0].charAt(0)) + String.valueOf(artNomi[1].charAt(0));
						if (iniziali.equals(artista)) {
							nuoviElementi.add(classifica + ": " + elementi[i]);
						}
					}
					frame.centerPanel.classificaTextArea.setText("");
					for (Object elem : nuoviElementi) {
						frame.centerPanel.classificaTextArea.append(elem + "\n");
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
