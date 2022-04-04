package cryptoTrader.utils;

//Data structure to hold the data necessary for graphs
public class TradeResult {
	
	static class Trade{
		Object traderName;
		int numOfStrats;
		
		public Trade(Object traderName, int numOfStrats) {
			this.traderName = traderName;
			this.numOfStrats = numOfStrats;
		}
		
		public int getNumOfStrats() {
			return numOfStrats;
		}
		
		public Object getName() {
			return traderName;
		}
	}

}
