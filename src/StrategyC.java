package cryptoTrader.utils;

import java.lang.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonObject;

public class StrategyC extends StrategyInterface{

	//you will get the action necessary for each coin from strategy D
	//"Play Large" Strategy, all prices Over 10CAD, the costlier the coin is, the more you buy / sell
	@Override
	public TradeResult trade(String[] coinList, double[] coinPriceList) {
		
		int numOfCoins = coinList.length;
		/**final String[] inCoinArray = new String[] 
				{ "btc", "eth", "bnb", "sol", "luna", "avax", "dot", "wbtc",
						"steth", "near", "atom", "ltc", "link", "bch", "ftt", 
						"etc", "okb", "uni", "axs", "icp", "fil", "egld", 
						"waves", "xmr", "ceth", "ape", "rune", "aave", "cake", 
						"osmo", "hnt", "zec", "mkr", "cvx", "fxs", "ar", "neo",
						"bsv", "hbtc", "qnt", "ksm", "kcs", "ht", "dash", "juno"};**/
		AvailableCryptoList ACL = new AvailableCryptoList();
		List<String> inCoinList = new ArrayList<>();
		String[] avaID = ACL.getAvailableCryptosID();
		for (String x : avaID) {
			if ((Double.parseDouble(ACL.getCurPrice(x))) >= 10) {
				inCoinList.add(x);
			}
		}
		
		Object[] finCoinList = inCoinList.toArray();
		
//		for ( String i : coinList) {
//			if ( finCoinList.contains(i) == false ) {
//				System.out.println("Fail transaction due to invalid cryptocoin.");
//				status = false;
//				return null;
//			}
//		}
		
		int[] status = new int[numOfCoins];  //whether the transaction succeeded, 0 = failed
		
		for (int i = 0; i < numOfCoins; i++) {
			for (int j = 0; j < inCoinList.size(); j++) {
				if (inCoinList.get(j) == coinList[i]) {
					status[i] = 1;
				}
			}
		}
		
		double[] CoinRatio = new double[numOfCoins]; // ratio for buy/sell quantity
		int[] finQty = new int[numOfCoins]; // final quantity for each coin
		String[] action = new String[numOfCoins]; // 0 = buy, 1 = sell, 2 = hold
		double[] trend = new double[numOfCoins];	//slope of trends of coins
		
		String[] coinIDList = new String[numOfCoins];
		double[] prevPrices = new double[numOfCoins];
		
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal = Calendar.getInstance();
		Date newdate = cal.getTime();
		cal.add(Calendar.DATE, -1); // decrement the date
		String ytd = dateformat.format(newdate);
		
		DataFetcher DF = new DataFetcher();
		
		int totalQty = 2500;
		int sum = 0;	//sum of all coin prices
		
		for ( double p : coinPriceList ) {
			sum += p;
		}
		
		for (int i = 0; i < numOfCoins; i++) {
			CoinRatio[i] = coinPriceList[i]/sum;	
		}
		
		
		for (int i = 0; i < numOfCoins; i++) {
			finQty[i] = (int) Math.ceil(totalQty * CoinRatio[i]);
		}
		
		
		for (int i = 0; i < numOfCoins; i++) {
			prevPrices[i] = DF.getPriceForCoin(coinList[i], ytd);
			
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
		
		Object[][] result = new Object[numOfCoins][4];
		TradeResult TR = new TradeResult();
		for (int i = 0; i < numOfCoins; i++) {
			if (status[i] == 1) {
				result[i][0]= coinList[i];
				result[i][1]= coinPriceList[i];
				result[i][2]= action[i];
				result[i][3]= finQty[i];
			}
			
			else
			{
				result[i][0]= coinList[i];
				result[i][1]= null;
				result[i][2]= "Failed (Invalid Coin)";
				result[i][3]= null;
			}
			
		}
		
		TR.trade(numOfCoins, result);
		
		
		return TR;
	}
	
}
