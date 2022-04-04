package cryptoTrader.utils;

import java.lang.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonObject;

public class StrategyD extends StrategyInterface{
	
	public String getIDbySymbol(String Symbol) {
		
		AvailableCryptoList AC = new AvailableCryptoList();
		String id;
		id = AC.getCryptoID(Symbol);
		return id;
	}

	//you will get the action necessary for each coin from strategy D
	//"Play Small" Strategy, all prices under 10CAD
	@Override
	public TradeResult trade(String[] coinList, double[] coinPriceList) {
		
		int numOfCoins = coinList.length;
		boolean status;  //whether the transaction succeeded
		final String[] inCoinArray = new String[] 
				{ "usdt", "usdc", "xrp", "ada", "doge", "busd", "ust", "shib", "cro", "matic",
						"dai", "trx", "algo", "xlm", "leo", "vet", "hbar", "sand", "mana", 
						"ftm", "theta", "xtz", "grt", "klay", "eos", "mim", "frax", "flow", 
						"miota", "dfi", "zil", "xec", "btt", "gala", "one", "cdai", "tfuel", 
						"cusdc", "celo", "enj", "stx", "snx", "xrd", "chz", "nexo", "lrc", 
						"gmt", "tusd", "amp", "cel", "ldo", "bat", "heart", "mina", "kda"};
		
		List<String> inCoinList = new ArrayList<>(Arrays.asList(inCoinArray));
		
		for ( String i : coinList) {
			if ( inCoinList.contains(i.toLowerCase()) == false ) {
				System.out.println("Fail transaction due to invalid cryptocoin.");
				status = false;
				return null;
			}
		}
		
		double[] reverseCoinRatio = new double[numOfCoins]; // reversed ratio for buy/sell quantity
		double sumOfRevCRatio = 0;	//sum of reversed ratio
		double[] finRatio = new double[numOfCoins];		//final ratio for buy/sell
		int[] finQty = new int[numOfCoins]; // final quantity for each coin
		int[] action = new int[numOfCoins]; // buy, sell,  hold
		double[] trend = new double[numOfCoins];	//slope of trends of coins
		
		String[] coinIDList = new String[numOfCoins];
		double[] prevPrices = new double[numOfCoins];
		
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal = Calendar.getInstance();
		Date newdate = cal.getTime();
		cal.add(Calendar.DATE, -1); // decrement the date
		String ytd = dateformat.format(newdate);
		
		DataFetcher DF = new DataFetcher();
		
		int totalQty = 1500;
		int sum = 0;	//sum of all coin prices
		
		for ( double p : coinPriceList ) {
			sum += p;
		}
		
		for (int i = 0; i < numOfCoins; i++) {
			reverseCoinRatio[i] = 1/(coinPriceList[i]/sum);	
		}
		
		for (double c : reverseCoinRatio) {
			sumOfRevCRatio += c;
		}
		
		for (int i = 0; i < numOfCoins; i++) {
			finRatio[i] = (reverseCoinRatio[i]/sumOfRevCRatio);
		}
		
		for (int i = 0; i < numOfCoins; i++) {
			finQty[i] = (int) Math.ceil(totalQty * finRatio[i]);
		}
		
		for (int i = 0; i < numOfCoins; i++) {
			coinIDList[i] = getIDbySymbol(coinList[i]); //rmb to lower case the symbol 
		}
		
		for (int i = 0; i < numOfCoins; i++) {
			prevPrices[i] = DF.getPriceForCoin(coinIDList[i], ytd);
			
		}
		
		for (int i = 0; i < numOfCoins; i++) {
			trend[i] = prevPrices[i] - coinPriceList[i];
		}
		
		for (int i = 0; i < numOfCoins; i++) {
			if (trend[i] < 0) {
				action[i] = "Sell";
			}
			else if (trend[i] > 0) {
				action[i] = "Buy";
			}
			
		}
		
		Object[][] result;
		TradeResult TR = new TradeResult();
		for (int i = 0; i < numOfCoins; i++) {
			result[i][0]= coinList[i];
			result[i][1]= coinPriceList[i];
			result[i][2]= action[i];
			result[i][3]= finQty[i];
		}
		
		TR.trade(numOfCoins, result);
		
		
		return TR;
	}
	
}
