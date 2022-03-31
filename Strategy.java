package cryptoTrader.utils;

import java.lang.reflect.Array;

import cryptoTrader.gui.MainUI;
import cryptoTrader.gui.NewUI;

public class Strategy {
	private String[] actions;
	private double[] quantities;
	private double[] prices;
	
	public Strategy(Object name, Object strategy, String[] coins) {
		String date = NewUI.getInstance().getDate();
		DataFetcher data = new DataFetcher();
		
		for(int i = 0; i < Array.getLength(coins); i++) {
			String currCoin = coins[i];
			double price = data.getPriceForCoin(currCoin, date);
			prices[i] = price;
		}
			
			if(strategy.equals("Strategy-A")) {
				StrategyA sa = new StrategyA();
				sa.trade(coins, prices);
			
			}else if(strategy.equals("Strategy-B")) {
				StrategyB sb = new StrategyB();
				sb.trade(coins, prices);
			
			}else if(strategy.equals("Strategy-C")) {
				StrategyC sc = new StrategyC();
				sc.trade(coins, prices);
			
			}else if(strategy.equals("Strategy-D")) {
				StrategyD sd = new StrategyD();
				sd.trade(coins, prices);
			
			}else {
				//Default (strategy is "None") means that no trades will occur so the broker will have 0 quantity of specified coins
				for(int i = 0; i < Array.getLength(coins); i++) {
					quantities[i] = 0;
				}
				
			}
		
	}
	
	public String[] getActions() {
		return actions;
	}

	public double[] getQuants() {
		return quantities;
	}

	public double[] getPrices() {
		return prices;
	}

}
