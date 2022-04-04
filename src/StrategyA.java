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
	 * Strategy A: if coin is LOWER than yesterday, buy 100 quantity
	 */

	private SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
	private String currdate = dateformat.format(new Date());

	
	public TradeResult trade(String[] coinList, double[] coinPriceList) throws ParseException {
		String coin;
		String coinNames;
		String yesterday;

		// store prices for today (coinPrice) and yesterday (oldCoinPrice)
		double today_coinPrice;
		double yday_coinPrice;

		// how many coins have been dealt with
		int quantity;

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

			// for each coin, get today's price, store in coinPriceList
			today_coinPrice = df.getPriceForCoin(coinList[i], currdate);

			today_combinedList[i][0][0][0] = coinList[i];
			today_combinedList[0][i][0][0] = today_coinPrice;

			// for each coin, get yesterday's price, store in yesterday PriceList
//			oldCoinPrice = coinList[i].getPriceForCoin("bitcoin", "02-04-2022");
			yday_coinPrice = df.getPriceForCoin(coinList[i], yesterday);

			yday_combinedList[i][0][0][0] = coinList[i];
			yday_combinedList[0][i][0][0] = yday_coinPrice;
		}

		// now we compare today's prices (todayCombinedList) vs yesterday's prices (oldCombinedList)
		// (oldCombinedList)
		for (int j = 0; j < Array.getLength(today_combinedList); j++) {

			double todayPrice;
			double ydayPrice;

			todayPrice = (double) today_combinedList[0][j][0][0];
			ydayPrice = (double) yday_combinedList[0][j][0][0];

			while (todayPrice < ydayPrice) {
				System.out.println("Performing Strategy A on" + today_combinedList[j][0]);

//				for ()
				// increase quantity by 100
					
					
//				[name of coin][price of coin yesterday][action performed][qty]	
			}

			
		// Strategy A method
		/*
		 * for the coinList[i] ... increase the quantity to 100
		 * i think that's it?
		 * 
		 * for all other coins that dont meet the requirements, just leave quantity as is (maybe 0?)
		 * 
		 */
		
			

		/*
		 * for example if u wanted the bitcoin price for yesterday (02-04-2022)
		 * https://api.coingecko.com/api/v3/coins/bitcoin/history?date=02-04-2022&localization=false 
		 * tokenID is 'bitcoin', date is 'DD-MM-YYYY', and keep 'localization=false'
		 */


		}

		return null;
	}

}
