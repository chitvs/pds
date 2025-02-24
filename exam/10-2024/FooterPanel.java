import java.awt.*;
import javax.swing.*;

public class FooterPanel extends JPanel {
	JButton startButton;
	JButton stopButton;
	
	public FooterPanel() {
		this(true);
	}

	public FooterPanel(boolean b) {
		super(b);
		startButton = new JButton("Start");
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		stopButton = new JButton("Stop");
		stopButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.add(startButton);
		this.add(stopButton);
	}
}
