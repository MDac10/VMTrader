/**
 * @authors: Megan Da Costa, Vito Wong, Xiaoyun Bonato, Sin Hoi Ching Ingrid
 * This is the main Login GUI used in the crypto trader platform
 * @course: 2212b
 * @project: Final Devliverable of VM Trader IX**/

package cryptoTrader.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class LoginUI extends JFrame implements ActionListener {
	
	private static LoginUI logInstance;
	
	public static LoginUI getLogInstance() {
		if (logInstance == null)
			logInstance = new LoginUI();

		return logInstance;
	}
	
	private LoginUI() {

		// Set window title
		super("Login Window");
	}
	
	public static void main(String[] args) {
		JPanel panel = new JPanel()
		JFrame frame = LoginUI.getLogInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);
		frame.add(panel);
	}
}
