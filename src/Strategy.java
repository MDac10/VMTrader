/**
 * @author Megan Da Costa, Vito Wong, Xiaoyun Bonato, Sin Hoi Ching Ingrid
 * @course 2212
 * Strategy class handles each of the strategies for each broker**/
package cryptoTrader.utils;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//import cryptoTrader.gui.MainUI;
import cryptoTrader.gui.NewUI;

public class Strategy {
	private double[] prices;
	private String defaultDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	private TradeResult trade;
	private int stratRows;
	
	/**
	 * @param name - Broker name
	 * @param strategy - Strategy type that broker selected on main page
	 * @param coins - List of cryptocoins that broker put on main page
	 * @throws ParseException **/
	public Strategy(Object name, Object strategy, String[] coins) throws ParseException {
		String date = NewUI.getInstance().getDate();
		if(date.isBlank()) {date = defaultDate;}
		
		DataFetcher data = new DataFetcher();
		
		prices = new double[Array.getLength(coins)];
		stratRows = Array.getLength(coins);
		
		for(int i = 0; i < Array.getLength(coins); i++) { 
			
			String currCoin = coins[i];
			//System.out.println("Strategy class: " + currCoin);
			double price = data.getPriceForCoin(currCoin, date);
			prices[i] = price;
			
		}
			
			if(strategy.equals("Strategy-A")) {
				StrategyA sa = new StrategyA();
				trade = sa.trade(coins, prices);
			
			}else if(strategy.equals("Strategy-B")) {
				StrategyB sb = new StrategyB();
				trade = sb.trade(coins, prices);
			
			}else if(strategy.equals("Strategy-C")) {
				StrategyC sc = new StrategyC();
				trade = sc.trade(coins, prices);
			
			}else if(strategy.equals("Strategy-D")) {
				StrategyD sd = new StrategyD();
				trade = sd.trade(coins, prices);
			
			} 
		
	}
	
	/**
	 * @return the TradeResult data structure holding the information necessary for the bar graph**/
	public TradeResult getTrade() {
		return trade;
	}
	
	/**
	 * @return the number of rows in each of the brokers trading instances**/
	public int getStratRows() {
		return stratRows;
	}

	/**
	 * @return a list containing all the prices of specified coins for a given broker based on their selected strategy**/
	public double[] getPrices() {
		return prices;
	}

}
