/**
 * @author Megan Da Costa, Vito Wong, Xiaoyun Bonato, Sin Hong Ching Ingrid
 * TraderActions class conducts the organization of data for each trader**/
package cryptoTrader.utils;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import cryptoTrader.gui.NewUI;

public class TraderActions {
	
	private Object brokerName, strategyName, currCoin, currAction, currQuant, currPrice;
	private ArrayList<Object[]> brokerList = new ArrayList<Object[]>();
	private String date = NewUI.getInstance().getDate();
	
	/**
	 * @return 
	 * @throws ParseException **/
	public Object[][] clientInfoTable() throws ParseException{
		Object[][] rawData = NewUI.getInstance().getClientInfo();

		Object[][] data;
		
		for(int i = 0; i < NewUI.getInstance().getNumRows(); i++) {
			brokerName = rawData[i][0];
			strategyName = rawData[i][2];
			String[] coinName = (String[]) rawData[i][1]; 
			Strategy strat = new Strategy(brokerName, strategyName, coinName);
			TradeResult tr = strat.getTrade();
			Object[][] objArray = tr.getArray();

			currCoin = objArray[i][0];
			currAction = objArray[i][2];
			currQuant = objArray[i][3];
			currPrice = objArray[i][1];
				
			Object[] row = {brokerName, strategyName, currCoin, currAction, currQuant, currPrice, date};
			brokerList.add(row);
			
		}
		
		int bSize = brokerList.size();
		data = new Object[bSize][7];
		
		for(int i = 0; i < bSize; i++) {
			data[i] = brokerList.get(i);
		}
		
		return data;
	}

}
