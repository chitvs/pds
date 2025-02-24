import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FooterPanel extends JPanel {
	JButton startButton;
	JButton stopButton;
	JButton rivelaButton;
	
	public FooterPanel() {
		this(true);
	}

	public FooterPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		startButton = new JButton("Start");
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		startButton.setMnemonic(KeyEvent.VK_S);
		
		stopButton = new JButton("Stop");
		stopButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		stopButton.setMnemonic(KeyEvent.VK_T);
		
		rivelaButton = new JButton("Rivela");
		rivelaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		rivelaButton.setMnemonic(KeyEvent.VK_R);
		
		this.add(startButton);
		this.add(stopButton);
		this.add(rivelaButton);
	}
}
