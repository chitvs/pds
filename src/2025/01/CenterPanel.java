import javax.swing.*;

public class CenterPanel extends JPanel {
	JTextArea artistiTextArea;
	JTextArea logTextArea;
	JTextArea classificaTextArea;
	JScrollPane artistiScrollPane;
	JScrollPane logScrollPane;
	JScrollPane classificaScrollPane;

	public CenterPanel() {
		this.classificaTextArea = new JTextArea(10,25);
		this.classificaTextArea.setEditable(false);
		this.classificaScrollPane = new JScrollPane(this.classificaTextArea);
		this.classificaScrollPane.setBorder(BorderFactory.createTitledBorder("Classifica"));

		this.artistiTextArea = new JTextArea(10,25);
		this.artistiTextArea.setEditable(false);
		this.artistiScrollPane = new JScrollPane(this.artistiTextArea);
		this.artistiScrollPane.setBorder(BorderFactory.createTitledBorder("Artisti"));

		this.logTextArea = new JTextArea(10,30);
		this.logTextArea.setEditable(false);
		this.logScrollPane = new JScrollPane(this.logTextArea);
		this.logScrollPane.setBorder(BorderFactory.createTitledBorder("Log"));
		
		this.add(this.logScrollPane);
		this.add(this.artistiScrollPane);
		this.add(this.classificaScrollPane);
	}
}