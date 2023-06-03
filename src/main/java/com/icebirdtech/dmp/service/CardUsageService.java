package com.icebirdtech.dmp.service;

import com.icebirdtech.dmp.modal.CardUsage;

/**
 * Card usage service to handle card usage history.
 * 
 * Load card usage from another API and cache the CardUsage to improve performance.
 * Update card usage when new transaction approved.
 * 
 */
public interface CardUsageService {
	/**
	 * Get card usage
	 * @param cardNum cardNumber
	 * @return cardUsage
	 */
	public CardUsage getCardUsage(Long cardNum);

	/**
	 * Update card usage.
	 * @param cardUsage
	 */
	public CardUsage increaseCardUsage(CardUsage cardUsage);
}
