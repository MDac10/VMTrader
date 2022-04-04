/**
 * @author Megan Da Costa, Vito Wong, Xiaoyun Bonato, Sin Hong Ching Ingrid**/

package cryptoTrader.utils;

import java.text.ParseException;

import javax.swing.*;

import cryptoTrader.gui.NewUI;

public class DataViewer extends JFrame{
	
	private static DataViewer instance;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @return creates a new instance of the DataViewer window**/
	public static DataViewer getInstance() throws ParseException {
		if (instance == null)
			instance = new DataViewer();

		return instance;
	}

	/**
	 * Makes the viewer portion of the MVC design pattern, allowing the live data graphs to be seen in tabs
	 * @throws ParseException **/
	DataViewer() throws ParseException{
	
		JTabbedPane dataPanel = new JTabbedPane();
		
		//This tab contains the timeSeries
		JPanel timeSeries = new JPanel();
		timeSeries.add(new DataModels().createTimeSeries());
		
		//This tab contains the scatter plot
		JPanel scatterPlot = new JPanel();
		scatterPlot.add(new DataModels().createScatter());
		
		//This tab contains the bar graph
		JPanel barGraph = new JPanel();
		barGraph.add(new DataModels().createBar());
		
		dataPanel.add("Bar Graph", barGraph);
		dataPanel.add("Time Series", timeSeries);
		dataPanel.add("Scatter Plot", scatterPlot);
		
		//The UI is updated to accommodate the different tabs with the graphs
		NewUI.getInstance().updateStats(dataPanel);
	}
}
