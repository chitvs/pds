import javax.swing.*;

public class CenterPanel extends JPanel {
	JTextArea consoleTextArea;
	JScrollPane consoleScrollPane;
	
	public CenterPanel() {
		this.consoleTextArea = new JTextArea(25,80);
		this.consoleTextArea.setEditable(false);
		this.consoleScrollPane = new JScrollPane(this.consoleTextArea);
		this.consoleScrollPane.setBorder(BorderFactory.createTitledBorder("Console"));
		
		this.add(consoleScrollPane);
	}
}
