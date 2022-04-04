/**
 * @author Megan Da Costa, Vito Wong, Xiaoyun Bonato, Sin Hong Ching Ingrid
 * @course 2212
 * Strategy class handles each of the strategies for each broker**/
package cryptoTrader.utils;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;

import cryptoTrader.gui.MainUI;
import cryptoTrader.gui.NewUI;

public class Strategy {
	private String[] actions;
	private double[] quantities;
	private double[] prices;
	private String defaultDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	
	/**
	 * @param name - Broker name
	 * @param strategy - Strategy type that broker selected on main page
	 * @param coins - List of cryptocoins that broker put on main page**/
	public Strategy(Object name, Object strategy, String[] coins) {
		String date = NewUI.getInstance().getDate();
		if(date.isBlank()) {date = defaultDate;}
		
		DataFetcher data = new DataFetcher();
		
		for(int i = 0; i < Array.getLength(coins); i++) { 
			
			String currCoin = coins[i];
			double price = data.getPriceForCoin(currCoin, date);
			prices[i] = price;
			
		}
			
			if(strategy.equals("Strategy-A")) {
				StrategyA sa = new StrategyA();
				TradeResult saTrade = sa.trade(coins, prices);
			
			}else if(strategy.equals("Strategy-B")) {
				StrategyB sb = new StrategyB();
				TradeResult sbTrade = sb.trade(coins, prices);
			
			}else if(strategy.equals("Strategy-C")) {
				StrategyC sc = new StrategyC();
				TradeResult scTrade = sc.trade(coins, prices);
			
			}else if(strategy.equals("Strategy-D")) {
				StrategyD sd = new StrategyD();
				TradeResult sdTrade = sd.trade(coins, prices);
			
			}else {
				//Default (strategy is "None") means that no trades will occur so the broker will have 0 quantity of specified coins
				for(int i = 0; i < Array.getLength(coins); i++) {
					quantities[i] = 0;
				}
				
			}
		
	}
	
	/**
	 * @return a list containing all the actions performed for a given broker based on their selected strategy**/
	public String[] getActions() {
		return actions;
	}

	/**
	 * @return a list containing all the quantities of specified coins for a given broker based on their selected strategy**/
	public double[] getQuants() {
		return quantities;
	}

	/**
	 * @return a list containing all the prices of specified coins for a given broker based on their selected strategy**/
	public double[] getPrices() {
		return prices;
	}

}
