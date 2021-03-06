/**
 * @author Megan Da Costa, Vito Wong, Xiaoyun Bonato, Sin Hoi Ching Ingrid
 * Class DateFormat is meant to format the date when the user selects a calendar date in the MainUI**/
package cryptoTrader.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;

public class DateFormat extends AbstractFormatter{

	/**
	 * Used as a class to convert date into a specific format
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String datePatern = "dd-MM-yyyy";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePatern);
	
	@Override
	public Object stringToValue(String text) throws ParseException {
		
		return dateFormatter.parseObject(text);
		
	}

	@Override
	public String valueToString(Object value) throws ParseException {

		if (value != null) {
			Calendar cal = (Calendar) value;
			return dateFormatter.format(cal.getTime());
		}

		return "";
			
	}
}
