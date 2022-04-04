/**
 * @author Megan Da Costa, Vito Wong, Xiaoyun Bonato, Sin Hong Ching Ingrid
 * TraderActions class conducts the organization of data for each trader**/
package cryptoTrader.utils;


//import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Date;

import cryptoTrader.gui.NewUI;

public class TraderActions {
	
	private Object brokerName, strategyName, currCoin, currAction, currQuant, currPrice, currDate;
	private ArrayList<Object[]> brokerList = new ArrayList<Object[]>();
	private static String date = NewUI.getInstance().getDate();
	
	/**
	 * @return an Object[][] array that holds each individual iteration of data for each broker
	 * @throws ParseException **/
	public Object[][] clientInfoTable() throws ParseException{
		
		Object[][] rawData = NewUI.getInstance().getClientInfo(); //The data that needs to be parsed and ordered

		Object[][] data; //The value that should be returned in the end
		
		for(int i = 0; i < NewUI.getInstance().getNumRows(); i++) {
			brokerName = rawData[i][0];
			strategyName = rawData[i][2];
			String[] coinName = (String[]) rawData[i][1]; 
			
			Strategy strat = new Strategy(brokerName, strategyName, coinName); //Creates a new Strategy object to parse through a brokers options when performing a trade
			TradeResult tr = strat.getTrade(); //Retrieves the TradeResult data structure per broker 
			Object[][] objArray = tr.getArray();
			
			for(int j = 0; j < strat.getStratRows(); j++) { //From the object array collected above, the following cna be drawn
				currCoin = objArray[j][0];
				currAction = objArray[j][2];
				currQuant = objArray[j][3];
				currPrice = objArray[j][1];
				
				if(date.isBlank()) {
					currDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				} else {
					currDate = (Object) date;
				}
				
				Object[] row = {brokerName, strategyName, currCoin, currAction, currQuant, currPrice, currDate};
				brokerList.add(row); //Adds each row to the array list of brokers
			}
			
		}
		
		int bSize = brokerList.size();
		data = new Object[bSize][7];
		
		for(int i = 0; i < bSize; i++) {
			data[i] = brokerList.get(i); //Converts the arrat list into an Object[][] array
		}
		
		return data;
	}

}
