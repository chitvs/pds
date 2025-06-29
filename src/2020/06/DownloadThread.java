import java.util.*;
import javax.swing.*;

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
		try {
			while (running) {
				if (Thread.currentThread().isInterrupted()) {
					throw new InterruptedException();
				}
				String cmd = scanner.nextLine();
				System.out.println("Ricevuto: " + cmd);
				if (cmd.equalsIgnoreCase("done")) {
					running = false;
					frame.board.setGameActive(true);
					JOptionPane.showMessageDialog(frame, "Configurazione Terminata. Puoi iniziare a giocare.");

				} else if (cmd.equalsIgnoreCase("interrupted")) {
					running = false;
					JOptionPane.showMessageDialog(frame, "Configurazione Interrotta");
				} else {
					String[] rcn = cmd.split(":");
					int riga = Integer.parseInt(rcn[0]);
					int colonna = Integer.parseInt(rcn[1]);
					int n = Integer.parseInt(rcn[2]);

					if (n >= 0) {
						frame.board.getButton(riga, colonna).setAdjacentMinesCount(n);
					} else {
						frame.board.getButton(riga, colonna).setMine(true);
					}
				}

			}
		} catch (InterruptedException e) {
			running = false;
		} finally {
			listener.downloadComplete();
			if (errored) {
				listener.disconnect();
			}
		}
	}

	public void stop() {
		if (!running)
			return;
		running = false;
	}
}
