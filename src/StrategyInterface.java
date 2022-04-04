package cryptoTrader.utils;

import java.text.ParseException;

/**
 * Interface used for each strategy, they will each be made to implement the following function(s)**/
public abstract class StrategyInterface {
	
	public abstract TradeResult trade(String[] coinList, double[] coinPriceList) throws ParseException;

}
