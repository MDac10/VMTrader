/**
 * @author Megan Da Costa, Vito Wong, Xiaoyun Bonato, Sin Hong Ching Ingrid
 * @course 2212package cryptoTrader.utils;
**/

package cryptoTrader.utils;

import cryptoTrader.gui.NewUI;

import java.lang.reflect.Array;
//import java.lang.reflect.Array;
//import java.util.Scanner;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.print.PrinterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

/*
 * Strategy A: if specific coin is LOWER than it was yesterday, buy 100 quantity
 */
public class StrategyA extends StrategyInterface {

	/*
	 * Setting up date (to be used later)
	 */
	private SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
	private String currdate = dateformat.format(new Date());
	private int numRows;

	/*
	 * Using a 2D array with 4 columns, we still store the following parameters
	 * 
	 * column 0: Name of coin 
	 * column 1: Price of coin 
	 * column 2: Action on coin
	 * column 3: Quantity of coin
	 * 
	 * Name and price will be taken from coinlist and coinPriceList, respectively
	 * Action will be either buy, or nothing 
	 * Quantity of the coin will increase in increments of 100
	 */
	public TradeResult trade(String[] coinList, double[] coinPriceList) throws ParseException {
		String yesterday;
		numRows = coinList.length;

		int quantity = 100;

		/*
		 * Variables to store the prices from today and yesterday
		 */
		double today_coinPrice;
		double yday_coinPrice;

		double todayPrice;
		double ydayPrice;

		/*
		 * Combined list has 4 columns, storing: name, price, action, qty, respectively,
		 * for today and yesterday
		 */
		Object[][] today_combinedList = new Object[coinList.length][4];
		Object[][] yday_combinedList = new Object[coinList.length][4];

		DataFetcher df = new DataFetcher();

		Date date = dateformat.parse(currdate);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		Date newdate = cal.getTime();
		cal.add(Calendar.DATE, -1); // decrement the date
		yesterday = dateformat.format(newdate);

		//System.out.println("Strategy A");

		/*
		 * Loop through coin list to populate the combined lists
		 */
		for (int i = 0; i < Array.getLength(coinList); i++) {

			/*
			 * Get the prices for each coin in the coin list
			 */
			today_coinPrice = df.getPriceForCoin(coinList[i], currdate);
			yday_coinPrice = df.getPriceForCoin(coinList[i], yesterday);

			/*
			 * Store name of coins in 1st column
			 */
			today_combinedList[i][0] = coinList[i];
			yday_combinedList[i][0] = coinList[i];

			/*
			 * Store price of coins in 2nd column
			 */
			today_combinedList[i][1] = today_coinPrice;
			yday_combinedList[i][1] = yday_coinPrice;

			todayPrice = (double) today_combinedList[i][1];
			ydayPrice = (double) yday_combinedList[i][1];

			/*
			 * If today's price is less than yesterday, execute strategy A, update combined list array
			 * Else, do nothing on the coin of interest
			 */
			if (todayPrice < ydayPrice) {
				//System.out.println("Performing Strategy A on " + today_combinedList[i][0]);

				today_combinedList[i][2] = "Buy";
				today_combinedList[i][3] = quantity;

			} else {
				
//				today_combinedList[i][1] = "NULL";
				today_combinedList[i][2] = "Trade failed"; // Hold from doing anything
				today_combinedList[i][3] = 0;
			}

		}

		TradeResult tr = new TradeResult();
		tr.trade(numRows, today_combinedList);

		return tr;
	}

}