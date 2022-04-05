package cryptoTrader.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AvailableCryptoList {
	private static AvailableCryptoList instance = null;
	
	private static Map<String, String> availableCryptosMap = new HashMap<>();
	private static List<String> availableCryptosList = new ArrayList<>();
	private static List<String> availableCryptosID = new ArrayList<>();
	private static Map<String, String> availableCryptosPriceMap = new HashMap<>();
	
	public static AvailableCryptoList getInstance() {
		if (instance == null)
			instance = new AvailableCryptoList();
		
		return instance;
	}
	
	AvailableCryptoList() {
		findAvailableCryptos();
	}
	
	public void call() {
		String urlString = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=IBM&apikey=VNEY4VV2AWF1EB51";
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
				System.out.println(inline);
				JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
				int size = jsonArray.size();
				
				String name, id, symbol;
				for (int i = 0; i < size; i++) {
					JsonObject object = jsonArray.get(i).getAsJsonObject();
					id = object.get("id").getAsString();
					name = object.get("name").getAsString();
					symbol = object.get("symbol").getAsString();
					availableCryptosMap.put(symbol, id);
					availableCryptosList.add(symbol);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block e.printStackTrace();
		}
	}
	
	private static void findAvailableCryptos() {

		String urlString = 
				"https://api.coingecko.com/api/v3/coins/markets" + 
						"?vs_currency=cad&order=market_cap_desc&per_page=100&page=1&sparkline=false";
//		ALPHAVANTAGE API KEY = VNEY4VV2AWF1EB51
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
				JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
				int size = jsonArray.size();
				
				String name, id, symbol, curPrice;
				for (int i = 0; i < size; i++) {
					JsonObject object = jsonArray.get(i).getAsJsonObject();
					//name = object.get("name").getAsString();
					curPrice = object.get("current_price").getAsString();
					id = object.get("id").getAsString();
					symbol = object.get("symbol").getAsString();
					availableCryptosMap.put(symbol, id);
					availableCryptosList.add(symbol);
					availableCryptosID.add(id);
					availableCryptosPriceMap.put(id,curPrice);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block e.printStackTrace();
		}
	}
	
	public String[] getAvailableCryptos() {
		return availableCryptosList.toArray(new String[availableCryptosList.size()]);
	}
	
	public String[] getAvailableCryptosID() {
		return availableCryptosID.toArray(new String[availableCryptosID.size()]);
	}
	
	public String getCryptoID(String cryptoName) {
		return availableCryptosMap.get(cryptoName);
	}
	public String getCurPrice(String cryptoID) {
		return availableCryptosPriceMap.get(cryptoID);
	}

}
