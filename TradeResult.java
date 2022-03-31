package cryptoTrader.utils;

//All strategies will implement this interface when deciding their trade strategy
public abstract class TradeResult {
	
	public abstract String trade(String[] coinList, double[] coinPriceList);

}
