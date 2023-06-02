package com.icebirdtech.dmp.modal;

import java.io.Serializable;
import java.util.stream.IntStream;

/**
 * Card usage with history of last 7 days.
 *
 */
public class CardUsage implements Serializable  {
	private static final long serialVersionUID = 5213917918675342241L;
	private int[] usageHistory;
	private int total;
	private long cardNumber;
	
	/**
	 * Construct the CardUsage
	 * @param cardNumber 
	 * @param usageHistory 
	 */
	public CardUsage(long cardNumber, int[] usageHistory) {
		if (usageHistory == null) {
			usageHistory = new int[7]; //if no history yet.
		}
		this.cardNumber = cardNumber;
		this.usageHistory = usageHistory;
		this.total = IntStream.of(usageHistory).sum();
	}
	
	/**
	 * 
	 * @return cardNumber
	 */
	public long getCardNumber() {
		return cardNumber;
	}

	/**
	 * 
	 * @return total usage
	 */
	public int getTotal() {
		return total;
	}
	
	/**
	 * 
	 * @return usage history
	 */
	public int[] getUsageHistory() {
		return usageHistory;
	}
	
	/**
	 * Card used for transaction, increase the usage count.
	 */
	public void increaseUsage() {
		usageHistory[0] ++;
		total ++;
	}
}
