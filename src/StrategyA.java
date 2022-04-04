/**
 * @author Megan Da Costa, Vito Wong, Xiaoyun Bonato, Sin Hong Ching Ingrid
 * @course 2212package cryptoTrader.utils;
*/

package cryptoTrader.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import cryptoTrader.gui.NewUI;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
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

public class StrategyA extends StrategyInterface {

	/*
	 * Strategy A: if coin is LOWER than yesterday, buy 100 quantiddy
	 */

	private SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
	private String currdate = dateformat.format(new Date());

//	private Object strategy;
//	private ArrayList<Object[]> strategyA = new ArrayList<Object[]>();

//	private static Object[][] coinTransactions;

//	@Override
	public TradeResult trade(String[] coinList, double[] coinPriceList) throws ParseException {
		String coin;
		String coinNames;

		// store prices for today and yesterday
		double coinPrice;
		double oldCoinPrice;
		
		
		int quantity;

		//
//		Object traderName;
//		double[] oldCoinPriceList;
//		Scanner reader = null;

		// will be the length of the coinList
		Object[][][][] todayCombinedList = new Object[coinList.length][][][];
		Object[][][][] oldCombinedList = new Object[coinList.length][][][];

		System.out.println("Strategy A");

		DataFetcher df = new DataFetcher();

		Date date = dateformat.parse(currdate);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		String yesterday;

		Date newdate = cal.getTime();
		cal.add(Calendar.DATE, -1); // decrement the date
		yesterday = dateformat.format(newdate);



		// then loop through the coinList
		for (int i = 0; i < Array.getLength(coinList); i++) {

			// for each coin, get today's price, store in coinPriceList
			coinPrice = df.getPriceForCoin(coinList[i], currdate);

			todayCombinedList[i][0][0][0] = coinList[i];
			todayCombinedList[0][i][0][0] = coinPrice;

			// for each coin, get yesterday's price, store in yesterday PriceList
//			oldCoinPrice = coinList[i].getPriceForCoin("bitcoin", "02-04-2022");
			oldCoinPrice = df.getPriceForCoin(coinList[i], yesterday);

			oldCombinedList[i][0][0][0] = coinList[i];
			oldCombinedList[0][i][0][0] = coinPrice;
		}

		// now we compare today's prices (todayCombinedList) vs yesterday's prices (oldCombinedList)
		// (oldCombinedList)
		for (int j = 0; j < Array.getLength(todayCombinedList); j++) {

			double todayPrice;
			double oldPrice;

			todayPrice = (double) todayCombinedList[0][j][0][0];
			oldPrice = (double) oldCombinedList[0][j][0][0];

			while (todayPrice < oldPrice) {
				System.out.println("Performing Strategy A on" + todayCombinedList[j][0]);

				for ()
				// increase quantity by 100
					
					
//				[name of coin][price of coin yesterday][action performed][qty]	
			}

			
		// Strategy A method
		/*
		 * for the coinList[i] ... increase the quantity to 100
		 * thats it?
		 */
		
//		int strategyA (String[] coinList) {
//			
//		}
//			
			

		/*
		 * for example if u wanted the bitcoin price for yesterday (02-04-2022)
		 * https://api.coingecko.com/api/v3/coins/bitcoin/history?date=02-04-2022&localization=false 
		 * tokenID is 'bitcoin', date is 'DD-MM-YYYY', and keep 'localization=false'
		 */

			// Iterate thru the list of coins and perform stratA on each
			for (int i = 0; i < Array.getLength(coinList); i++) {
//			coin = coinList[i][0];

//			if (coin.getPriceForCoin is less than previous day's lowest) {
//					buy 100qty
			}

		}

		return null;
	}

}
