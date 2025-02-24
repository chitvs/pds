/*
 * Board.java
 *
 * NOTE: Inserire qui la direttiva package se la classe viene spostata
 * in un package che non è quello di default.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * <p>
 * La classe {@code Board} realizza il pannello centrale dell'interfaccia
 * grafica del client, che rappresenta il tabellone di gioco del campo minato.
 *
 * Lo studente può, a sua discrezione, completare il codice seguendo i
 * suggerimenti inseriti ed utilizzare questa classe, oppure può fornire una sua
 * implementazione, ignorando completamente questa classe se lo ritiene
 * appropriato.
 *
 * Qualora non venga utilizzata, questa classe può comunque essere consegnata
 * insieme al resto del codice, a patto che le eventuali modifiche apportate non
 * comportino errori di compilazione.
 * </p>
 */

public class Board extends JPanel implements ActionListener {
	/*
	 * Inserire qui una struttura dati per la memorizzazione delle istanze di {@code
	 * BoardButton}. Si suggerisce di utilizzare una matrice, ma è possibile
	 * utilizzare qualsiasi altra struttura dati se lo si ritiene appropriato (lista
	 * di liste, array {row,column}-major, ecc).
	 */
	private ClientFrame frame;
	JPanel boardPanel;
	public static final String BUTTON = "button";

	public Board() {
		boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(10, 10));
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				BoardButton button = new BoardButton();
				button.setEnabled(false);
				boardPanel.add(button);
			}
		}
		this.setLayout(new BorderLayout());
		this.add(boardPanel, BorderLayout.CENTER);
	}

	/**
	 * Restituisce l'istanza di {@code BoardButton} all'indice ({@code row},
	 * {@code column}).
	 * 
	 * @param row    l'indice di riga del pulsante
	 * @param column l'indice di colonna del pulsante
	 * @return l'istanza di {@code BoardButton} per l'indice ({@code row},
	 *         {@code column}).
	 */
	public BoardButton getButton(int row, int column) {
		int i = row * 10 + column;
		BoardButton button = (BoardButton) boardPanel.getComponent(i);
		return button;
	}

	/**
	 * Il compito di questo metodo è quello di attivare o disattivare l'interazione
	 * con le caselle del gioco, utilizzando il metodo {@code setEnabled(boolean)}
	 * sulle istanze di {@code BoardButton}. Se il parametro {@code active} è
	 * {@code true}, allora il gioco viene attivato (i.e. i pulsanti possono essere
	 * premuti), altrimenti viene disattivato (i.e. i pulsanti non possono essere
	 * premuti).
	 */

	public void setGameActive(boolean active) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				BoardButton button = getButton(i, j);
				button.setEnabled(active);
			}
		}
	}

	/**
	 * Reset del gioco allo stato iniziale, chiamando il metodo {@code reset()} per
	 * tutte le istanze di {@code BoardButton}.
	 */

	public void resetGame() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				BoardButton button = getButton(i, j);
				button.reset();
			}
		}
	}

	/**
	 * Rivela il contenuto di tutte le caselle del gioco (i.e. chiama
	 * {@code reveal()} su tutte le istanze di {@code BoardButton}.
	 */

	public void revealBoard() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				BoardButton button = getButton(i, j);
				button.reveal();
			}
		}
	}

	/**
	 * La classe {@code Board} aggisce da <i>listener</i> per le istanze di
	 * {@code BoardButton}. N.B. come sempre per fare ciò è necessario aggiungerla
	 * alla lista dei listener per ogni istanza di {@code BoardButton}.
	 *
	 * Suggerimento: è possibile ottenere un riferimento al componente che ha
	 * scatenato l'evento attraverso la chiamata a {@code e.getSource()}.
	 *
	 * @param e l'evento scatenante la chiamata
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO: inserire qui il codice che realizza la
		// reazione alla pressione dei pulsanti del tabellone
		// (es. cosa succede se il pulsante contiene una mina?)
		Boolean cont = false;
		Object cmd = e.getSource();
		BoardButton x = (BoardButton) cmd;
		if (x.hasMine()) {
			JOptionPane.showMessageDialog(frame, "HAI PERSO.");
			setGameActive(false);
			return;
		} else {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					BoardButton button = getButton(i, j);
					if (button.isSelected() == false && button.hasMine() == false) {
						cont = true;
					}
				}
			}
		}
		if (!cont) {
			revealBoard();
			setGameActive(false);
			JOptionPane.showMessageDialog(frame, "Complimenti player, HAI VINTO!");
		}
	}
}