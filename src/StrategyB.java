/**
 * @author Megan Da Costa, Vito Wong, Xiaoyun Bonato, Sin Hong Ching Ingrid
 * @course 2212package cryptoTrader.utils;
**/

package cryptoTrader.utils;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Strategy B: if specific coin is HIGHER than it was yesterday, sell 100 quantity
 */

public class StrategyB extends StrategyInterface {

	/**
	 * Setting up date (to be used later)
	 */
	private SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
	private String currdate = dateformat.format(new Date());
	private int numRows;

	/**
	 * Using a 2D array with 4 columns, we still store the following parameters
	 * 
	 * column 0: Name 
	 * column 1: Price 
	 * column 2: Action on coin 
	 * column 3: Quantity of coin
	 * 
	 * Name and price will be taken from coinlist and coinPriceList, respectively
	 * Action will be either buy, or nothing Quantity of the coin will increase in
	 * increments of 100
	 */
	public TradeResult trade(String[] coinList, double[] coinPriceList) throws ParseException {

		String yesterday;
		numRows = coinList.length;

		int quantity = 0;

		/** store prices for today (coinPrice) and yesterday (oldCoinPrice) **/
		
		double today_coinPrice;
		double yday_coinPrice;

		Object[][] today_combinedList = new Object[coinList.length][4];
		Object[][] yday_combinedList = new Object[coinList.length][4];

		DataFetcher df = new DataFetcher();

		Date date = dateformat.parse(currdate);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		Date newdate = cal.getTime();
		cal.add(Calendar.DATE, -1); // decrement the date
		yesterday = dateformat.format(newdate);
		
		double todayPrice;
		double ydayPrice;

		/** Loop through the coinList **/
		
		for (int i = 0; i < Array.getLength(coinList); i++) {
			

			/** For each coin, get today's price, store data in combined list **/
			
			today_coinPrice = df.getPriceForCoin(coinList[i], currdate);
			yday_coinPrice = df.getPriceForCoin(coinList[i], yesterday);

			today_combinedList[i][0] = coinList[i]; // the name
			yday_combinedList[i][0] = coinList[i]; // the name
			
			
			today_combinedList[i][1] = today_coinPrice;
			yday_combinedList[i][1] = yday_coinPrice;
			
			
			todayPrice = (double) today_combinedList[i][1];
			ydayPrice = (double) yday_combinedList[i][1];
			
			
			/** If today's price is less, execute strategy **/
			
			if (todayPrice > ydayPrice) {
				System.out.println("Performing Strategy B on " + today_combinedList[i][0]);

				/** Action is to buy **/
				today_combinedList[i][2] = "Sell";

				/** Decrease the quantity by 100 **/
				quantity = -100;
				today_combinedList[i][3] = quantity;

			} else {
				today_combinedList[i][2] = "Hold";		// Hold from doing anything
				today_combinedList[i][3] = quantity;
			}
			
		}
		
		TradeResult tr = new TradeResult();
		
		tr.trade(numRows, today_combinedList);
		
		return tr;
	}

}
