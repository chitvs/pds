import java.awt.*;
import javax.swing.*;

public class FooterPanel extends JPanel{
	JButton artistiButton;
	JButton classificaButton;
	JButton stopButton;
	
	public FooterPanel() {
		this(true);
	}

	public FooterPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		artistiButton = new JButton("Artisti");
		artistiButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		classificaButton = new JButton("Classifica");
		classificaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	
		stopButton = new JButton("Stop");
		stopButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.add(artistiButton);
		this.add(classificaButton);
		this.add(stopButton);
	}
}