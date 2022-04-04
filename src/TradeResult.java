package cryptoTrader.utils;

//Data structure to hold the data necessary for graphs
public class TradeResult {

//	static class Trade{
	// i want it to return uhhh
	// name, action, qty
	String name;
	String action;
	int qty;

	Object traderName;
	int numOfStrats;

	public int getNumOfStrats() {
		return numOfStrats;
	}

	public Object getName() {
		return traderName;
	}
//	}

	private Object[][] output;

	public void trade(int nRows, Object[][] array) {
		// TODO Auto-generated method stub
		output = new Object[nRows][4];
		output = array;

	}

	public Object[][] getArray() {
		return output;
	}

}
