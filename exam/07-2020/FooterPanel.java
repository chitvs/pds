import java.awt.event.*;
import javax.swing.*;

public class FooterPanel extends JPanel {
	JLabel commandLabel;
	JTextField commandField;
	JButton executeButton;
	JButton interruptButton;
	
	public FooterPanel() {
		this(true);
	}

	public FooterPanel(boolean b) {
		super(b);
		commandLabel = new JLabel("Command");
		commandField = new JTextField("", 20);
		commandLabel.setLabelFor(commandField);
		executeButton = new JButton("Execute");
		interruptButton = new JButton("Interrupt");
		commandLabel.setDisplayedMnemonic(KeyEvent.VK_M);
		interruptButton.setMnemonic(KeyEvent.VK_T);
		
		add(commandLabel);
		add(commandField);
		add(executeButton);
		add(interruptButton);
	}
}