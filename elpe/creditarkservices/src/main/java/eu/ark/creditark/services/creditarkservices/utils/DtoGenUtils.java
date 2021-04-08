package eu.ark.creditark.services.creditarkservices.utils;

import eu.ark.creditark.services.creditarkservices.exceptions.ScenarioException;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.function.DoubleFunction;
import java.util.function.Function;

public class DtoGenUtils {
	private static final DecimalFormat df = new DecimalFormat("#.##");

	public static String numberToString(long input) {		
		return ""+input;
	}

	public static String numberToStringObj(Integer input) {
		if(null==input) return "";
		return ""+input;
	}

	private static Function<Double, String> doubleToString = new Function<Double, String>() {
		@Override
		public String apply(Double x) {
			if(Double.isNaN(x)) return "";
			else return df.format(x);
		}
	};
	public static String numberToString(double input) {
		if(Double.isNaN(input)) return "";
		return df.format(input);
	}

	public static String numberToStringUIFormated(double input) {
		if(Double.isNaN(input)) return "";
		return String.format("%,.2f", input);
	}

	public static String numberToStringUIIntFormated(double input) {
		if(Double.isNaN(input)) return "";
		return ""+(int)input;
	}

	public static String[] numberArrayToStringArray(double[] input) {
		if(null == input) return new String[]{};
		return (String[])Arrays.stream(input).mapToObj(new DoubleFunction<String>() {
			@Override
			public String apply(double x) {
				if(Double.isNaN(x)) return "";
				else return df.format(x);
			}
		}).toArray(String[]::new);
	}

	public static double[] stringArrayToDoubleArray(String[] input) {
		if(null == input) return new double[]{};
		return Arrays.stream(input).mapToDouble(x -> Double.parseDouble(x)).toArray();
	}

	public static String numberToString(Double input) {
		if(null==input || Double.isNaN(input)) return "";
		return df.format(input);
	}

	public static String numberToStringPercentage(Double input) {
		if(null==input || Double.isNaN(input)) return "";
		return df.format(input*100).concat("%");
	}
	public static String numberToStringPercentage(double input) {
		if(Double.isNaN(input)) return "";
		return df.format(input*100).concat("%");
	}


	public static double stringToDouble(String input) {
		if(null == input || input.length() == 0) return 0;
		try {
			return Double.parseDouble(input);
		} catch (Exception e) {}
		return 0;
	}

	public static int stringToInteger(String input) throws ScenarioException{
		if(null == input || input.length() == 0) return 0;
		try {
			return Integer.parseInt(input);
		} catch (Exception e) {
			throw new ScenarioException(input +" is not a valid value. A number is expected ");
		}
	}

	public static Integer stringToIntegerObj(String input)
			throws ScenarioException{
		if(null == input || input.length() == 0) return null;
		try {
			return Integer.parseInt(input);
		} catch (Exception e) {
			throw new ScenarioException(input +" is not a valid value. A number is expected ");
		}
	}

	public static Double percentageToNumber(String input)
			throws ScenarioException {
		if(null == input || input.length() == 0) return null;
		try {
			if(input.indexOf("%")<=0) return Double.parseDouble(input)/100;
			else return Double.parseDouble(input.substring(0, input.indexOf("%")))/100;
		} catch (Exception e) {
			throw new ScenarioException(input +" is not a valid value, value like 12 or 12% are expected");
		}
	}

	public static String numberToStringPercentageNoPostfix(double input) {
		if(Double.isNaN(input)) return "";
		return df.format(input*100);
	}

	public static int calcCreditAmount(double balance, int sdigits) {
		double div = (Math.pow(10, sdigits));
		double sum = 1;
		while(balance>1) {
			balance=(balance/div);
			sum*=div;
		}
		sum/=div;
		return (new BigDecimal(((int)(balance*div))*sum)).intValue();
	}

	public double percentages(double a, double b) {
		return a>b?-(100*(a-b)/b):(100*(b-a)/a);
	}
}
