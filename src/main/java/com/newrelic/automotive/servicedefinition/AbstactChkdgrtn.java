

/*
* Copyright ©2016, Ciber Inc All Rights Reserved
*/


package com.newrelic.automotive.servicedefinition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.newrelic.automotive.aspect.LoggingAspect;
import com.newrelic.automotive.exception.*;
import com.newrelic.automotive.servicedefinition.IChkdgrtnInterface; 
/**
Check digit validation
*/
@Component
public abstract class AbstactChkdgrtn implements IChkdgrtnInterface{

	
				
				/**
				Documentation :: Check Digit Validation
				*/
				
				public Boolean chkdgrtn(String Custnum) throws Exception {
					
				//  Author NW: Uses an industry-standard "ISIN" check-digit
				    //  algorithm to check final digit of number is valid.
					// 
					//  1. Starting with the right-most digit, replace each alternate
					//  digit with the sum of the digits of its double
					//  2. Take the sum of these digits. Take its last digit, 'd'.
					//  3. The check digit is 9-d
					// 
					//  e.g. original number 0 0 0 4 0 0 5 4 7 [5]
					//  x2 alternates                   10  14
					//  add x2 digits        0   0   0   1   5
					//  replace digits       0 0 0 4 0 0 1 4 5 [5]
					// 
					//  sum of digits              4+    1+4+5 = 14
					//  last digit of sum                         4
					//
					//  check digit                9-4         = 5 - correct answer!
					
					Logger log = LoggerFactory.getLogger(LoggingAspect.class);
					
					Boolean checkDigitValid = false;
					int custNoLength = Custnum.length();
					log.info("customer no.: " + Custnum);
					if (custNoLength != 10)
					{
						log.info("Invalid Customer Number. Needs to be 10 digits");
						throw new Exception ("Invalid Customer Number. Needs to be 10 digits");
					}
					
					char[] custNoDigits = new char[10];
					Custnum.getChars(0, 10, custNoDigits, 0);
					
					//  1. Starting with the right-most digit, replace each alternate
					//  digit with the sum of the digits of its double
					int digit8 = Integer.parseInt(String.valueOf(custNoDigits[8]));
					int digit8x2 = digit8 * 2;
					log.info("digit8x2: " + digit8x2);
					int digit6 = Integer.parseInt(String.valueOf(custNoDigits[6]));
					int digit6x2 = digit6 * 2;
					log.info("digit6x2: " + digit6x2);
					int digit4 = Integer.parseInt(String.valueOf(custNoDigits[4]));
					int digit4x2 = digit4 * 2;
					log.info("digit3x2: " + digit4x2);
					int digit2 = Integer.parseInt(String.valueOf(custNoDigits[2]));
					int digit2x2 = digit2 * 2;
					log.info("digit2x2: " + digit2x2);
					int digit0 = Integer.parseInt(String.valueOf(custNoDigits[0]));
					int digit0x2 = digit0 * 2;
					log.info("digit0x2: " + digit0x2);
					
					// 2. Take the sum of these digits.
					int digit8x2sum = digit8x2;
					if (digit8x2 >= 5)
					{
						String digit8x2s = String.valueOf(digit8x2);
						digit8x2sum = Integer.parseInt(digit8x2s.substring(0, 1)) + Integer.parseInt(digit8x2s.substring(1, 2));
					}
					log.info("digit8x2sum: " + digit8x2sum);
					int digit6x2sum = digit6x2;
					if (digit6x2 >= 5)
					{
						String digit6x2s = String.valueOf(digit6x2);
						digit6x2sum = Integer.parseInt(digit6x2s.substring(0, 1)) + Integer.parseInt(digit6x2s.substring(1, 2));
					}
					log.info("digit6x2sum: " + digit6x2sum);
					int digit4x2sum = digit4x2;
					if (digit4x2 >= 5)
					{
						String digit4x2s = String.valueOf(digit4x2);
						digit4x2sum = Integer.parseInt(digit4x2s.substring(0, 1)) + Integer.parseInt(digit4x2s.substring(1, 2));
					}
					log.info("digit4x2sum: " + digit4x2sum);
					int digit2x2sum = digit2x2;
					if (digit2x2 >= 5)
					{
						String digit2x2s = String.valueOf(digit2x2);
						digit2x2sum = Integer.parseInt(digit2x2s.substring(0, 1)) + Integer.parseInt(digit2x2s.substring(1, 2));
					}
					log.info("digit2x2sum: " + digit2x2sum);
					int digit0x2sum = digit0x2;
					if (digit0x2 >= 5)
					{
						String digit0x2s = String.valueOf(digit0x2);
						digit0x2sum = Integer.parseInt(digit0x2s.substring(0, 1)) + Integer.parseInt(digit0x2s.substring(1, 2));
					}
					log.info("digit0x2sum: " + digit0x2sum);
					
					// Take its last digit, 'd'.
					char[] replaceDigits = new char[10];
					replaceDigits[0] = String.valueOf(digit0x2sum).charAt(0);
					replaceDigits[1] = custNoDigits[1];
					replaceDigits[2] = String.valueOf(digit2x2sum).charAt(0);
					replaceDigits[3] = custNoDigits[3];
					replaceDigits[4] = String.valueOf(digit4x2sum).charAt(0);
					replaceDigits[5] = custNoDigits[5];
					replaceDigits[6] = String.valueOf(digit6x2sum).charAt(0);
					replaceDigits[7] = custNoDigits[7];
					replaceDigits[8] = String.valueOf(digit8x2sum).charAt(0);
					replaceDigits[9] = custNoDigits[9];
					log.info("replaceDigits: " + replaceDigits.toString());
					
					int replaceDigitsSum = Integer.parseInt(String.valueOf(replaceDigits[0]))+
						Integer.parseInt(String.valueOf(replaceDigits[1])) +
						Integer.parseInt(String.valueOf(replaceDigits[2])) +
						Integer.parseInt(String.valueOf(replaceDigits[3])) +
						Integer.parseInt(String.valueOf(replaceDigits[4])) +
						Integer.parseInt(String.valueOf(replaceDigits[5])) +
						Integer.parseInt(String.valueOf(replaceDigits[6])) +
						Integer.parseInt(String.valueOf(replaceDigits[7])) +
						Integer.parseInt(String.valueOf(replaceDigits[8]));
					log.info("replaceDigitsSum: " + String.valueOf(replaceDigitsSum));
					
					// 3. The check digit is 9 minus sum of resulting digits
					String replaceDigitsSums = String.valueOf(replaceDigitsSum);
					int replaceDigitsSumsLastDigit = Integer.parseInt(replaceDigitsSums.substring(replaceDigitsSums.length()-1, replaceDigitsSums.length()));
					log.info("replaceDigitsSumsLastDigit: " + String.valueOf(replaceDigitsSumsLastDigit));
					int checkDigit = 9 - replaceDigitsSumsLastDigit;
					log.info("checkDigit: " + String.valueOf(checkDigit) + " vs. " + String.valueOf(custNoDigits[9]));
					
					if (checkDigit == Integer.parseInt(String.valueOf(custNoDigits[9])))
					{
						checkDigitValid = true;
						log.info("check digit valid");
					}
					else
					{
						log.info("check digit invalid");
					}
					
					return checkDigitValid; 
				}
			
}