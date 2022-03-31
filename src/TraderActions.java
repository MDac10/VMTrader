/**
 * @author Megan Da Costa, Vito Wong, Xiaoyun Bonato, Sin Hong Ching Ingrid
 * TraderActions class conducts the organization of data for each trader**/
package cryptoTrader.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;

import cryptoTrader.gui.NewUI;

public class TraderActions {
	
	private Object brokerName, strategyName, currCoin, currAction, currQuant, currPrice;
	private ArrayList<Object[]> brokerList = new ArrayList<Object[]>();
	
	/**
	 * @return **/
	public Object[][] clientInfoTable(){
		Object[][] rawData = NewUI.getInstance().getClientInfo();

		Object[][] data;
		
		for(int i = 0; i < NewUI.getInstance().getNumRows(); i++) {
			brokerName = rawData[i][0];
			strategyName = rawData[i][2];
			String[] coinName = rawData[i][1].toString().split(","); 
			Strategy strat = new Strategy(brokerName, strategyName, coinName);
			String[] actions = strat.getActions();
			double[] quantities = strat.getQuants();
			double[] prices = strat.getPrices();
			
			for(int j = 0; j < Array.getLength(coinName); j++) {
				currCoin = coinName[j];
				currAction = actions[j];
				currQuant = quantities[j];
				currPrice = prices[j];
				
				Object[] row = {brokerName, strategyName, currCoin, currAction, currQuant, currPrice};
				brokerList.add(row);
			}
			
		}
		
		int bSize = brokerList.size();
		data = new Object[bSize][6];
		
		for(int i = 0; i < bSize; i++) {
			data[i] = brokerList.get(i);
			//data[i][0] = brokerList.get(i)[0];
			//data[i][1] = brokerList.get(i)[1];
			//data[i][2] = brokerList.get(i)[2];
			//data[i][3] = brokerList.get(i)[3];
			//data[i][4] = brokerList.get(i)[4];
			//data[i][5] = brokerList.get(i)[5];
		}
		
		return data;
	}

}
