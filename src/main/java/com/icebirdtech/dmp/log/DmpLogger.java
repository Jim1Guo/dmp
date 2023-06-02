package com.icebirdtech.dmp.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.icebirdtech.dmp.modal.Transaction;
import com.icebirdtech.dmp.modal.TransactionResponse;

public class DmpLogger {

	private static final Logger logger = LoggerFactory.getLogger(DmpLogger.class);
	private static final String CARD_NUMBER_FORMAT = "####xxxxxxxx####";
	private static final String INFO_FORMAT = "ReqeustId: {} {}";
	private static final String TRANSACTION_FORMAT = "ReqeustId: {} {} TransactionInfo: Cardnum: {} Amount: {}";
	private static final String RESPONSE_FORMAT = "ReqeustId: {} {} TransactionInfo: Cardnum: {} Amount: {} Decision: {} History: {}";
	
	/**
	 * 
	 * @param string
	 */
	public static void logApplicationInfo(String log) {
		logger.info("{}", log);
	}
	
	/**
	 * Log info
	 * @param log
	 */
	public static void logInfo(String log) {
		logger.info(INFO_FORMAT, MDC.get("request-correlation-id"), log);
	}
	
	/**
	 * Log Error
	 * @param log
	 */
	public static void logError(Exception error) {
		logger.error(error.getMessage(), error);
	}
	
	/**
	 * Log the transaction info.
	 * @param log
	 * @param transaction
	 */
	public static void logTransaction(String log, Transaction transaction) {
		logger.info(TRANSACTION_FORMAT, MDC.get("request-correlation-id"), log, 
				maskCardNumber(transaction.getCardNum(), CARD_NUMBER_FORMAT), 
				transaction.getAmount());
	}
	
	/**
	 * Log the decision info.
	 * @param log
	 * @param response
	 */
	public static void logResponse(String log, TransactionResponse response) {
		logger.info(RESPONSE_FORMAT, MDC.get("request-correlation-id"), log, 
				maskCardNumber(response.getTransaction().getCardNum(), CARD_NUMBER_FORMAT), 
				response.getTransaction().getAmount(), response.getDecision(), response.getUsage().getUsageHistory());
	}
	
	/**
	 * Applies the specified mask to the card number.
	 *
	 * @param cardNumber The card number in plain format
	 * @param mask The number mask pattern. Use # to include a digit from the
	 * card number at that position, use x to skip the digit at that position
	 *
	 * @return The masked card number
	 */
	private static String maskCardNumber(Long cardNumber, String mask) {
		
		String cardNum = String.valueOf(cardNumber);
	    // format the number
	    int index = 0;
	    StringBuilder maskedNumber = new StringBuilder();
	    for (int i = 0; i < mask.length(); i++) {
	        char c = mask.charAt(i);
	        if (c == '#') {
	            maskedNumber.append(cardNum.charAt(index));
	            index++;
	        } else if (c == 'x') {
	            maskedNumber.append(c);
	            index++;
	        } else {
	            maskedNumber.append(c);
	        }
	    }
	    // return the masked number
	    return maskedNumber.toString();
	}

}
