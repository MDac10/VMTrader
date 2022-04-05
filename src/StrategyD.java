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
	
	//you will get the action necessary for each coin from strategy D
	//"Play Small" Strategy, all prices under 10CAD, the cheaper the coin is, the more you buy / sell.	
	//You sell if the trend is decreasing, you buy otherwise.
	@Override
	public TradeResult trade(String[] coinList, double[] coinPriceList) {
		
		int numOfCoins = coinList.length;
		boolean status;  //whether the transaction succeeded
		
		AvailableCryptoList ACL = new AvailableCryptoList();
		List<String> inCoinList = new ArrayList<>();
		String[] avaID = ACL.getAvailableCryptosID();
		for (String x : avaID) {
			if ((Double.parseDouble(ACL.getCurPrice(x))) < 10) {
				inCoinList.add(x);
			}
		}
		
		Object[] finCoinList = inCoinList.toArray();
		/**final String[] inCoinArray = new String[] 
			{"tether", "usd-coin", "ripple", "cardano", "dogecoin", "binance-usd", "terrausd", "shiba-inu", 
			"crypto-com-chain", "matic-network", "dai", "tron", "algorand", "stellar", "leo-token", "vechain",
			"hedera-hashgraph", "the-sandbox", "decentraland", "fantom", "theta-token", "tezos", "the-graph",
			"klay-token", "eos", "magic-internet-money", "frax", "flow", "iota", "defichain", "zilliqa", "ecash",
			"bittorrent", "gala", "harmony", "cdai", "theta-fuel", "compound-usd-coin", "celo", "enjincoin", 
			"blockstack", "havven", "radix", "chiliz", "nexo", "loopring", "stepn", "true-usd", "amp-token", 
			"celsius-degree-token", "lido-dao","basic-attention-token", "humans-ai", "mina-protocol", "kadena"};
		**/
		//List<String> inCoinList = new ArrayList<>(Arrays.asList(inCoinArray));
		
//		for ( String i : coinList) {
//			if ( inCoinList.contains(i) == false ) {
//				System.out.println("Fail transaction due to invalid cryptocoin.");
//				status = false;
//				return null;
//			}
//		}
		
		double[] reverseCoinRatio = new double[numOfCoins]; // reversed ratio for buy/sell quantity
		double sumOfRevCRatio = 0;	//sum of reversed ratio
		double[] finRatio = new double[numOfCoins];		//final ratio for buy/sell
		int[] finQty = new int[numOfCoins]; // final quantity for each coin
		String[] action = new String[numOfCoins]; // buy, sell,  hold
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
		
		for ( int i = 0; i < numOfCoins; i++ ) {
			sum += coinPriceList[i];
			System.out.println("Sum: " + coinPriceList[i]);
		}
		
		for (int i = 0; i < numOfCoins; i++) {
			reverseCoinRatio[i] = 1/(coinPriceList[i]/sum);	
			System.out.println("revCR: " + reverseCoinRatio[i]);
		}
		
		for (double c : reverseCoinRatio) {
			sumOfRevCRatio += c;
			System.out.println("sumR: " + c);
		}
		
		for (int i = 0; i < numOfCoins; i++) {
			finRatio[i] = (reverseCoinRatio[i]/sumOfRevCRatio);
			System.out.println("sfinR: " + finRatio[i] );
		}
		
		for (int i = 0; i < numOfCoins; i++) {
			finQty[i] = (int) Math.ceil(totalQty * finRatio[i]);
			System.out.println("price: " + finQty[i]);
		}
		
		
		for (int i = 0; i < numOfCoins; i++) {
			prevPrices[i] = DF.getPriceForCoin((String) finCoinList[i], ytd);
			
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
			result[i][0]= coinList[i];
			result[i][1]= coinPriceList[i];
			result[i][2]= action[i];
			result[i][3]= finQty[i];
		}
		
		TR.trade(numOfCoins, result);
		
		
		return TR;
	}
	
}
