package cryptoTrader.utils;

public abstract class StrategyInterface {
	
	public abstract TradeResult trade(String[] coinList, double[] coinPriceList);

}
