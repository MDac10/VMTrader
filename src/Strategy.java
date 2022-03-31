package cryptoTrader.utils;

import java.lang.reflect.Array;

import cryptoTrader.gui.MainUI;
import cryptoTrader.gui.NewUI;

public class Strategy {
	private String action;
	
	public Strategy(Object name, Object strategy, String[] coins) {
		String date = NewUI.getInstance().getDate();
		DataFetcher data = new DataFetcher();
		
		for(int i = 0; i < Array.getLength(coins); i++) {
			String currCoin = coins[i];
			double price = data.getPriceForCoin(currCoin, date);
			
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
	
	public String getAction() {
		return action;
	}

}
