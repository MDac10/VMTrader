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
	 * Strategy A:
	 * if coin is LOWER than yesterday, buy 100 qty
	 */	
	
	private SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
	private String currdate = dateformat.format(new Date());
	
//	private Object strategy;
//	private ArrayList<Object[]> strategyA = new ArrayList<Object[]>();
	
//	private static Object[][] coinTransactions;
	
//	@Override
	public TradeResult trade(String[] coinList, double[] coinPriceList) throws ParseException {
		String coin;
//		String[] coinNames = new String[coinList.length];
		String coinNames;
		
		// store prices for today and yesterday
		double coinPrice;
		double oldCoinPrice;
		
		// 
		Object traderName;
		double[] oldCoinPriceList;
//		Scanner reader = null;
		
		// will be the length of the coinList
		Object[][] todayCombinedList = new Object[coinList.length][2];
		Object[][] oldCombinedList = new Object[coinList.length][2];
		
		System.out.println("Strategy A");
//		System.out.println("Select crypto coin:");
//		double selectCrypto = reader.nextDouble();
//		String strSelect = Double.toString(selectCrypto);
		
//		String searchedValue = strSelect;
		
		DataFetcher df = new DataFetcher();
		
//		String ogdate = NewUI.getInstance().getDate();
//		String sdate = ogdate;
		
		Date date = dateformat.parse(currdate);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		
		
		
		
		String yesterday;
		
		Date newdate = cal.getTime();
		cal.add(Calendar.DATE, -1);			//decrement the date
		yesterday = dateformat.format(newdate);
	
//		cal.add(Calendar.DATE, -1); //decrement the date
		
		// then loop through the coinList
		for (int i = 0; i < Array.getLength(coinList); i++) {
			
			// for each coin, get today's price, store in coinPriceList
			coinPrice = df.getPriceForCoin(coinList[i], currdate);
		
			
			todayCombinedList[i][0] = coinList[i];
			todayCombinedList[0][i] = coinPrice;
			
	
			// for each coin, get yesterday's price, store in yesterday PriceList
//			oldCoinPrice = coinList[i].getPriceForCoin("bitcoin", "02-04-2022");
						
			
			oldCoinPrice = df.getPriceForCoin(coinList[i], yesterday);
			
			oldCombinedList[i][0] = coinList[i];
			oldCombinedList[0][i] = coinPrice;
			
			
//			oldCoinPriceList[i] = oldCoinPrice;
		}
		
		// now we compare today's prices (todayCombinedList) vs yesterday's prices (oldCombinedList)
		for (int j = 0; j < Array.getLength(todayCombinedList); j++) {
			
			double todayPrice;
			double oldPrice;
			
			todayPrice = (double) todayCombinedList[0][j];
			oldPrice = (double) oldCombinedList[0][j];
					
			while (todayPrice < oldPrice) {
				System.out.println("Performing stratA on" + todayCombinedList[j][0]);
				
				
			//increase quantity by 100
		}
		
		
			
			
//			searchedValue.getPriceForCoin(searchedValue, currentDate) {
//				
//			}
			// for each coin, get 
		
		
		
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
			
			
			
			
			
//			coinList[i] = coinTransactions[i][i];
			
			
//			clientInfo[count][1];
//			coinList[i] = coin;
//			coin.getPriceForCoin(currCoin,date);
//			coin
//			
//			if (coin.price <= coin.price.date-1) {
//				buy100
//			}
		}	
			
			
//		}
//		if (coinList)
		return null;
	}

}
