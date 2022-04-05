/**
 * @author Megan Da Costa, Vito Wong, Xiaoyun Bonato, Sin Hong Ching Ingrid
 */

package cryptoTrader.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
//import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
//import org.jfree.data.time.RegularTimePeriod;

import cryptoTrader.utils.AvailableCryptoList;
import cryptoTrader.utils.DataVisualizationCreator;
import cryptoTrader.utils.DateFormat;

public class NewUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private static NewUI instance;
	private JPanel stats, chartPanel, tablePanel;

	private DefaultTableModel dtm;
	private JTable table;
	
	private static Object[][] clientInfo;
	private static int numRows;
	private static JDatePickerImpl datePicker;

	public static NewUI getInstance() {
		if (instance == null)
			instance = new NewUI();

		return instance;
	}

	private NewUI() {

		// Set window title
		super("VMTrader IX");

		// Set top bar


		JPanel north = new JPanel();

//		north.add(strategyList);

		// Set bottom bar
		JLabel from = new JLabel("From: ");
		UtilDateModel dateModel = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, p);
		datePicker = new JDatePickerImpl(datePanel, new DateFormat());
		
		//Creates a date panel for the user to select their date of interest for trades
		JPanel date = new JPanel();
		date.setLayout(new BoxLayout(date, BoxLayout.X_AXIS));
		date.add(from);
		date.add(datePicker);

		JButton trade = new JButton("Perform Trade");
		trade.setActionCommand("trade");
		trade.addActionListener(this);


		JPanel south = new JPanel();
		
		south.add(trade);

		dtm = new DefaultTableModel(new Object[] { "Trading Client", "Coin List", "Strategy Name" }, 1);
		table = new JTable(dtm);
		// table.setPreferredSize(new Dimension(600, 300));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Trading Client Actions",
				TitledBorder.CENTER, TitledBorder.TOP));
		Vector<String> strategyNames = new Vector<String>();
		strategyNames.add("None");
		strategyNames.add("Strategy-A");
		strategyNames.add("Strategy-B");
		strategyNames.add("Strategy-C");
		strategyNames.add("Strategy-D");
		TableColumn strategyColumn = table.getColumnModel().getColumn(2);
		JComboBox comboBox = new JComboBox(strategyNames);
		strategyColumn.setCellEditor(new DefaultCellEditor(comboBox));
		JButton addRow = new JButton("Add Row");
		JButton remRow = new JButton("Remove Row");
		addRow.setActionCommand("addTableRow");
		addRow.addActionListener(this);
		remRow.setActionCommand("remTableRow");
		remRow.addActionListener(this);

		scrollPane.setPreferredSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);
		

		JPanel east = new JPanel();
//		east.setLayout();
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
//		east.add(table);
		east.add(scrollPane);
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.add(addRow);
		buttons.add(remRow);
		east.add(date);
		east.add(buttons);
//		east.add(selectedTickerListLabel);
//		east.add(selectedTickersScrollPane);

		// Set charts region
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(1250, 700));
		stats = new JPanel();
		stats.setLayout(new GridLayout(2, 2));

		west.add(stats);

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(west, BorderLayout.CENTER);
		getContentPane().add(south, BorderLayout.SOUTH);
//		getContentPane().add(west, BorderLayout.WEST);
	}

	public void updateStats(JComponent component) {
		stats.add(component);
		stats.revalidate();
	}
	
	/**
	 * @return the Object array containing the unorganized broker inputs**/
	public Object[][] getClientInfo() {
		return clientInfo;
	}
	
	/**
	 * @return number of rows in the table**/
	public int getNumRows() {
		return numRows;
	}
	
	/**
	 * @return a string version of the date selected by the user**/
	public String getDate() {
		return datePicker.getJFormattedTextField().getText();
	}

	public static void main(String[] args) {
		JFrame frame = NewUI.getInstance();
		frame.setSize(900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		if ("trade".equals(command)) {
			
			clientInfo = new Object[dtm.getRowCount()][3];
			numRows = dtm.getRowCount();
			
			for (int count = 0; count < dtm.getRowCount(); count++){
				
					Object traderObject = dtm.getValueAt(count, 0);
					
					if (traderObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in Trader name on line " + (count + 1) );
						return;
					}
					
					Object coinObject = dtm.getValueAt(count, 1);
					
					if (coinObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in cryptocoin list on line " + (count + 1) );
						return;
					}
					
					String[] coinSymbols = coinObject.toString().split(","); //Holds the symbols that the user input
					String[] coinNames = new String[coinSymbols.length]; //Creates a list to hold the proper lower case names of the crypto coins
					
					for(int i = 0; i < coinSymbols.length; i++) {
						coinNames[i] = AvailableCryptoList.getInstance().getCryptoID(coinSymbols[i].toLowerCase()); //Converts the crypto coin symbols to the actual name
					}
					
					Object strategyObject = dtm.getValueAt(count, 2);
					
					if (strategyObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in strategy name on line " + (count + 1) );
						return;
					}
					
					//the data from the user input table is placed inside the clientInfo object array
					clientInfo[count][0] = traderObject;
					clientInfo[count][1] = coinNames;
					clientInfo[count][2] = strategyObject;
					
	        }
			
			stats.removeAll();
			DataVisualizationCreator creator = new DataVisualizationCreator();
			try { //These functions can throw a parse exception so a try-catch is used
				creator.createCharts();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
		} else if ("addTableRow".equals(command)) {
			
			//adds a new row to the table on the right side of the window "Trader Actions"
			dtm.addRow(new String[3]);
			
		} else if ("remTableRow".equals(command)) {
			
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1)
				dtm.removeRow(selectedRow);
		}
	}

}
