import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HeaderPanel extends JPanel {
	JButton connectButton;
	JButton disconnectButton;
	JLabel serverLabel;
	JLabel portLabel;
	JTextField serverField;
	JTextField portField;
	
	public HeaderPanel() {
		this(new FlowLayout(FlowLayout.CENTER), true);
	}

	public HeaderPanel(LayoutManager layout, boolean b) {
		super(layout,b);
		connectButton = new JButton("Connect");
		connectButton.setMnemonic(KeyEvent.VK_C);
		disconnectButton = new JButton("Disconnect");
		disconnectButton.setMnemonic(KeyEvent.VK_D);
		serverLabel = new JLabel("Server Address");
		serverField = new JTextField("localhost", 20);
		serverLabel.setDisplayedMnemonic(KeyEvent.VK_A);
		serverLabel.setLabelFor(serverField);
		portLabel = new JLabel("Port");
		portField = new JTextField("4400", 8);
		portLabel.setLabelFor(portField);
		portLabel.setDisplayedMnemonic(KeyEvent.VK_P);
		
		add(serverLabel);
		add(serverField);
		add(portLabel);
		add(portField);
		add(connectButton);
		add(disconnectButton);	
	}
}
