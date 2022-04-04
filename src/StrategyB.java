import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Vector;
import cryptoTrader.gui.NewUI;
import java.lang.reflect.Array;


public class StrategyB extends StrategyInterface {

    /*
     * Setting up date (to be used later)
     */

    private SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
    private String currdate = dateformat.format(new Date());
    private int numRows;

    /*
     * Getting the action necessary for each coin from strategy B
     */

    @Override
    public TradeResult trade(String[] coinList, double[] prices) {
        Object traderName;
        Scanner reader = null;
        numRows = coinList.length;
        int quantity = 100;
        double today_coinPrice;

        Object[][] today_combinedList = new Object[coinList.length][4];

        DataFetcher df = new DataFetcher();
        Date date = dateformat.parse(currdate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Date newdate = cal.getTime();

        /*
         *For each coin, get today's price, store data in combined list
         */

        today_coinPrice = df.getPriceForCoin(coinList[i], currdate);
        today_combinedList[i][0] = coinList[i]; // the name
        today_combinedList[i][1] = today_coinPrice;
        double todayPrice;
        todayPrice = (double) today_combinedList[i][1];

        System.out.println("Strategy A");


        /* Looping through the coinList */

        for (int i = 0; i < Array.getLength(coinList); i++) {


            /* Creating Strategy B */

            System.out.println("Strategy B:");

            /* Making Trader select a Crypto Coin */

            System.out.println("Select Crypto Coin: ");
            double selectCrypto = reader.nextDouble();
            String strSelect = Double.toString(selectCrypto);

            String searchedValue = strSelect;

            /* Searching if desired Crypto is in coinList */

            for (String x: coinList) {
                if (x == searchedValue) {
                    boolean found = true;
                    break;
                }
            }


            /* Finding the minimum and maximum price of coinList */

            Arrays.sort(prices);
            double priceMinimum = prices[0];
            double priceMaximum = prices[prices.length - 1];

            /* Trader selects their own Crypto price minimum */

            System.out.println("Select Lowest Crypto Price Limit: ");
            double lowestPriceLim = reader.nextDouble();

            if (lowestPriceLim >= priceMinimum) {
                boolean found = true;
            }

            /* Trader selects their own Crypto price maximum */

            System.out.println("Select Highest Crypto Price Limit: ");
            double highestPriceLim = reader.nextDouble();

            if (highestPriceLim <= priceMaximum) {
                boolean found = true;
            }


            // TODO Auto-generated method stub
            TradeResult tr = new TradeResult();

            tr.trade(numRows, today_combinedList);
            return tr;
        }
    }
}
