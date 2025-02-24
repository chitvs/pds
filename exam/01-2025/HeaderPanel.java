import java.awt.event.*;
import java.awt.*;
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
	
	public HeaderPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		connectButton = new JButton("Connect");
		connectButton.setMnemonic(KeyEvent.VK_C);
		disconnectButton = new JButton("Disconnect");
		disconnectButton.setMnemonic(KeyEvent.VK_D);
		serverLabel = new JLabel("Server Address");
		serverField = new JTextField("127.0.0.1", 20);
		serverLabel.setLabelFor(serverField);
		serverLabel.setDisplayedMnemonic(KeyEvent.VK_A);
		portField = new JTextField("4400", 8);
		portLabel = new JLabel("Port");
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
