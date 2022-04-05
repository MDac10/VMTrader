/**
 * @author Megan Da Costa, Vito Wong, Xiaoyun Bonato, Sin Hoi Ching Ingrid**/
package cryptoTrader.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import cryptoTrader.gui.NewUI;

/**
 * This class is meant to act as the model for Data Models in the MVC design pattern**/
public class DataModels {
	
	private static double[] btcPrices = new double[5];
	private static double[] ethPrices = new double[5];
	private static double[] crdPrices = new double[5];
	private static DataFetcher df = new DataFetcher();
	private SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
	private String currdate = dateformat.format(new Date()); //retrieves today's date in the correct format that corresponds with the coinGecko API
	
	/**
	 * @return - ChartPanel with the live Time Series info for Bitcoin, Ethereum, and Cardano
	 * @throws ParseException 
	 * This function may take a few seconds to run as the data being fetched must be searched several times**/
	public ChartPanel createTimeSeries() throws ParseException{	
		
		String ogdate = NewUI.getInstance().getDate(); //retrieves the date selected or set by the user
		String sdate = ogdate;
		
		if(ogdate.isBlank() || dateformat.parse(currdate).before(dateformat.parse(sdate))) {
			sdate = currdate; //sets the default date as current date if nothing is selected
		} 
		Date date = dateformat.parse(sdate); //converts the string version of the date to a Date element
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		/**
		 * Creates the three instances of coin time series
		 * It was assumed that only these three crypto coins should be monitored because many brokers may include different cryptocoins
		 * and there was no indication of having an option to look at any other kinds of coins**/
		TimeSeries series1 = new TimeSeries("Bitcoin - Daily");
		TimeSeries series2 = new TimeSeries("Ethereum - Daily");
		TimeSeries series3 = new TimeSeries("Cardano - Daily");

		for(int i = 0; i < 5; i++) { //creates 5 data points of these respective cryptocoins over the past 5 days from the date selected 
			
			Date newdate = cal.getTime();
			sdate = dateformat.format(newdate);
			
			double btc = df.getPriceForCoin("bitcoin", sdate);
			btcPrices[i] = btc; //Saves the calculated values into an array to be used for the scatter plot
			
			double eth = df.getPriceForCoin("ethereum", sdate);
			ethPrices[i] = eth; //Saves the calculated values into an array to be used for the scatter plot
			
			double crd = df.getPriceForCoin("cardano", sdate);
			crdPrices[i] = crd;//Saves the calculated values into an array to be used for the scatter plot
			
			series1.add(new Day(cal.getTime()), btc);
			series2.add(new Day(cal.getTime()), eth);
			series3.add(new Day(cal.getTime()), crd);
			
			cal.add(Calendar.DATE, -1); //decrement the date
		}

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		dataset.addSeries(series3);

		XYPlot plot = new XYPlot();
		XYSplineRenderer splinerenderer1 = new XYSplineRenderer();
		
		plot.setDataset(0, dataset);
		plot.setRenderer(0, splinerenderer1);
		DateAxis domainAxis = new DateAxis("");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new LogAxis("Price(CAD)"));

		//plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		//plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis
		//plot.mapDatasetToRangeAxis(2, 2);// 3rd dataset to 3rd y-axis
		
		JFreeChart chart = new JFreeChart("Daily Price Line Chart", new Font("Serif", java.awt.Font.BOLD, 18), plot,
				true);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(800, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		
		return chartPanel;
	}
	
	/**
	 * @return - ChartPanel with the live scatter plot of the time series info for Bitcoin, Ethereum, and Cardano
	 * @throws ParseException 
	 * This function may take a few seconds to run**/
	public ChartPanel createScatter() throws ParseException {
		
		String ogdate = NewUI.getInstance().getDate();
		String sdate = ogdate;
		
		if(ogdate.isBlank() || dateformat.parse(currdate).before(dateformat.parse(sdate))) {
			sdate = currdate; //sets the default date as current date if nothing is selected
		} 
		Date date = dateformat.parse(sdate);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		/**
		 * Creates the three instances of coin time series
		 * It was assumed that only these three crypto coins should be monitored because many brokers may include different cryptocoins
		 * and there was no indication of having an option to look at any other kinds of coins**/
		TimeSeries series1 = new TimeSeries("Bitcoin - Daily");
		TimeSeries series2 = new TimeSeries("Ethereum - Daily");
		TimeSeries series3 = new TimeSeries("Cardano - Daily");

		for(int i = 0; i < 5; i++) { //creates 5 data points of these respective cryptocoins over the past 5 days from the date selected
			
			Date newdate = cal.getTime();
			sdate = new SimpleDateFormat("dd-MM-yyyy").format(newdate);
			
			//series1.add(new Day(cal.getTime()), df.getPriceForCoin("bitcoin", sdate));
			//series2.add(new Day(cal.getTime()), df.getPriceForCoin("ethereum", sdate));
			//series3.add(new Day(cal.getTime()), df.getPriceForCoin("cardano", sdate));
			
			series1.add(new Day(cal.getTime()), btcPrices[i]);
			series2.add(new Day(cal.getTime()), ethPrices[i]);
			series3.add(new Day(cal.getTime()), crdPrices[i]);
			
			cal.add(Calendar.DATE, -1); //decrement the date
		}

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		dataset.addSeries(series3);

		XYPlot plot = new XYPlot();
		XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);

		plot.setDataset(0, dataset);
		plot.setRenderer(0, itemrenderer1);
		DateAxis domainAxis = new DateAxis("");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new LogAxis("Price(CAD)"));

		//plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		//plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis
		
		JFreeChart scatterChart = new JFreeChart("Daily Price Scatter Chart",
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		ChartPanel chartPanel = new ChartPanel(scatterChart);
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		
		return chartPanel;
	}
	
	/**
	 * @return - ChartPanel with the live bar graph showing the traders actions performed**/
	public ChartPanel createBar() {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//		Those are hard-coded values!!!! 
//		You will have to come up with a proper datastructure to populate the BarChart with live data!
		dataset.setValue(6, "Trader-1", "Strategy-A");
		dataset.setValue(5, "Trader-2", "Strategy-B");
		dataset.setValue(0, "Trader-3", "Strategy-E");
		dataset.setValue(1, "Trader-4", "Strategy-C");
		dataset.setValue(10, "Trader-5", "Strategy-D");

		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();

		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Strategy");
		plot.setDomainAxis(domainAxis);
		LogAxis rangeAxis = new LogAxis("Actions(Buys or Sells)");
		rangeAxis.setRange(new Range(1.0, 20.0));
		plot.setRangeAxis(rangeAxis);

		plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

		JFreeChart barChart = new JFreeChart("Actions Performed By Traders So Far", new Font("Serif", java.awt.Font.BOLD, 18), plot,
				true);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		
		return(chartPanel);
	}
}
