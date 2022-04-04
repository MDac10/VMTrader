/**
 * @author Megan Da Costa, Vito Wong, Xiaoyun Bonato, Sin Hong Ching Ingrid
 * @course 2212package cryptoTrader.utils;
**/

package cryptoTrader.utils;

//import java.io.IOException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;

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

	/*
	 * Using a 2D array with 4 columns, we still store the following parameters (in
	 * order): Coin name, coin price, action on coin, quantity of coin Name and
	 * price will be taken from coinlist and coinPriceList, respectively Action will
	 * be either buy, or nothing Quantity of the coin will increase in increments of
	 * 100
	 */
	public TradeResult trade(String[] coinList, double[] coinPriceList) throws ParseException {
//		String coin;
//		String coinNames;
		String yesterday;

		int quantity = 0;

		// store prices for today (coinPrice) and yesterday (oldCoinPrice)
		double today_coinPrice;
		double yday_coinPrice;

		// will be the length of the coinList
		Object[][][][] today_combinedList = new Object[coinList.length][][][];
		Object[][][][] yday_combinedList = new Object[coinList.length][][][];

		DataFetcher df = new DataFetcher();

		Date date = dateformat.parse(currdate);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		Date newdate = cal.getTime();
		cal.add(Calendar.DATE, -1); // decrement the date
		yesterday = dateformat.format(newdate);

		System.out.println("Strategy A");

		// then loop through the coinList
		for (int i = 0; i < Array.getLength(coinList); i++) {

			// for each coin, get today's price, store data in combined list
			today_coinPrice = df.getPriceForCoin(coinList[i], currdate);

			today_combinedList[i][0][0][0] = coinList[i]; // the name
			today_combinedList[0][i][0][0] = today_coinPrice; // the price

			// for each coin, get yesterday's price, store data in combined list
			yday_coinPrice = df.getPriceForCoin(coinList[i], yesterday);

			yday_combinedList[i][0][0][0] = coinList[i]; // the name
			yday_combinedList[0][i][0][0] = yday_coinPrice; // the price
		}

		// Compare today's prices (today_combinedList) to yesterday's prices
		// (yday_combinedList)
		for (int j = 0; j < Array.getLength(today_combinedList); j++) {

			double todayPrice;
			double ydayPrice;

			todayPrice = (double) today_combinedList[0][j][0][0];
			ydayPrice = (double) yday_combinedList[0][j][0][0];

			// If today's price is less, execute strategy
			if (todayPrice < ydayPrice) {
				System.out.println("Performing Strategy A on " + today_combinedList[j][0]);

				// Action is to buy
				today_combinedList[0][0][j][0] = "Buy";

				// Increase the quantity by 100
				quantity += 100;
				today_combinedList[0][0][0][j] = quantity;

			} else {
				today_combinedList[0][0][j][0] = "Nothing";
			}

		}

		// Shouldn't be null
		// should return ummm? all coins that have been bought? aka qty > 0?
		return null;
	}

}
