package cryptoTrader.utils;

import java.lang.reflect.Array;

import cryptoTrader.gui.MainUI;
import cryptoTrader.gui.NewUI;

public class Strategy {
	private String[] actions;
	private String[] quantities;
	private double[] prices;
	
	public Strategy(Object name, Object strategy, String[] coins) {
		String date = NewUI.getInstance().getDate();
		DataFetcher data = new DataFetcher();
		
		for(int i = 0; i < Array.getLength(coins); i++) {
			String currCoin = coins[i];
			double price = data.getPriceForCoin(currCoin, date);
			prices[i] = price;
			
			if(strategy.equals("Strategy-A")) {
			
			}else if(strategy.equals("Strategy-B")) {
			
			}else if(strategy.equals("Strategy-C")) {
			
			}else if(strategy.equals("Strategy-D")) {
			
			}else {
			
			}
		}
		
	}
	
	public TradeResult trade(String[] coinList, String[] coinPriceList) {
		return null;
	}
	
	public String[] getActions() {
		return actions;
	}

	public String[] getQuants() {
		return quantities;
	}

	public double[] getPrices() {
		return prices;
	}

}

