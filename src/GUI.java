import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI implements ActionListener{
	
	private int brokers = 0;
	private JLabel label;
	private JFrame frame;
	private JPanel panel;
	
	public GUI() {
		
		frame = new JFrame();
		
		JButton button = new JButton("Add Broker");
		button.addActionListener(this);
		
		label = new JLabel("Number of brokers: 0");
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(70, 70, 25, 70));
		panel.setLayout(new GridLayout(0, 1));
		panel.add(button);
		panel.add(label);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("VMTraderIX");
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main ( String[] args ) {
		new GUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		brokers++;
		label.setText("Number of Brokers: " + brokers);
		
	}

}
