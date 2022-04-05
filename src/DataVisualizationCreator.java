package cryptoTrader.utils;

import java.awt.Dimension;

import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

//import cryptoTrader.gui.MainUI;
import cryptoTrader.gui.NewUI;

public class DataVisualizationCreator {
	
	public void createCharts() throws ParseException {
//		createTextualOutput();
		createTableOutput();

		//DataViewer dv = new DataViewer();
		DataViewer.getInstance();
	}

	private void createTextualOutput() {
//		DefaultTableModel dtm = new  DefaultTableModel(new Object[] {"Broker Name", "Ticker List", "Strategy Name"}, 1);
//		JTable table = new JTable(dtm);
//		//table.setPreferredSize(new Dimension(600, 300));
//		dtm.e
//		JScrollPane scrollPane = new JScrollPane(table);
//		scrollPane.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
//                "Broker Actions",
//                TitledBorder.CENTER,
//                TitledBorder.TOP));
//		
//	
//		
//		scrollPane.setPreferredSize(new Dimension(800, 300));
//		table.setFillsViewportHeight(true);;
		
//		MainUI.getInstance().updateStats(scrollPane);
	}
	
	private void createTableOutput() throws ParseException {
		// Dummy dates for demo purposes. These should come from selection menu
		Object[] columnNames = {"Trader","Strategy","CryptoCoin","Action","Quantity","Price","Date"};
		
		TraderActions ta = new TraderActions();
		//Object[][] data = {}; //set as a default
		Object[][] data = ta.clientInfoTable();

		JTable table = new JTable(data, columnNames);
		//table.setPreferredSize(new Dimension(600, 300));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Trader Actions",
                TitledBorder.CENTER,
                TitledBorder.TOP));
		
		scrollPane.setPreferredSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);;
		
		//MainUI.getInstance().updateStats(scrollPane);
		NewUI.getInstance().updateStats(scrollPane);
	}

}
