package com.newrelic.momentum.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.newrelic.momentum.exception.MomentumException;

public class ValidationUtil {
	
	/**
	 * Method to validate the input value with REG-EX
	 * @param input
	 * @param regex
	 * @return
	 */
	public static boolean isValid(Object input, String regex) throws MomentumException {
		// TODO Auto-generated method stub
		String inputMatcher  = input.toString();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(inputMatcher);
		
		return matcher.matches();
	}
	
	/**
	 * Method to validate the equality of two Variables.
	 * @param input
	 * @param inputValue
	 * @return
	 */
	public static boolean isEqual(Object input, String inputValue) throws MomentumException {
		
		if (input !=null && inputValue !=null) {
			return input.equals(inputValue);
		}
		return false;
	}
	
}
