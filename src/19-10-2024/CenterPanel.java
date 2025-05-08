import javax.swing.*;

public class CenterPanel extends JPanel {
	JTextArea usaTextArea;
	JTextArea italiaTextArea;
	JTextArea logTextArea;
	JScrollPane usaScrollPane;
	JScrollPane italiaScrollPane;
	JScrollPane logScrollPane;

	public CenterPanel() {
		this.usaTextArea = new JTextArea(10,25);
		this.usaTextArea.setEditable(false);
		this.usaScrollPane = new JScrollPane(this.usaTextArea);
		this.usaScrollPane.setBorder(BorderFactory.createTitledBorder("USA"));
		
		this.italiaTextArea = new JTextArea(10,25);
		this.italiaTextArea.setEditable(false);
		this.italiaScrollPane = new JScrollPane(this.italiaTextArea);
		this.italiaScrollPane.setBorder(BorderFactory.createTitledBorder("Italia"));
		
		this.logTextArea = new JTextArea(10,25);
		this.logTextArea.setEditable(false);
		this.logScrollPane = new JScrollPane(this.logTextArea);
		this.logScrollPane.setBorder(BorderFactory.createTitledBorder("Log"));
		
		this.add(usaScrollPane);
		this.add(italiaScrollPane);
		this.add(logScrollPane);
	}
}
