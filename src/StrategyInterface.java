package cryptoTrader.utils;

import java.text.ParseException;

public abstract class StrategyInterface {
	
	public abstract TradeResult trade(String[] coinList, double[] coinPriceList) throws ParseException;

}
