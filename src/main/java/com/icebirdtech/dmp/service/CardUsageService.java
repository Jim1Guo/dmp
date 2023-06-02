package com.icebirdtech.dmp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.icebirdtech.dmp.externalapi.ExternalApi;
import com.icebirdtech.dmp.modal.CardUsage;

/**
 * Card usage service to handle card usage history.
 * 
 * Load card usage from another API and cache the CardUsage to improve performance.
 * Update card usage when new transaction approved.
 * 
 */
@Service
public class CardUsageService {

	@Autowired
	ExternalApi externalApi;
	
	@Cacheable(
		      value = "cardUsageCache", 
		      key = "#cardNum")	   
	/**
	 * Get card usage
	 * @param cardNum cardNumber
	 * @return cardUsage
	 */
	public CardUsage getCardUsage(Long cardNum) {
		return externalApi.retrieveCardUsage(cardNum);
	}

	@CachePut(
		      value = "cardUsageCache", 
		      key = "#cardUsage.cardNumber")
	/**
	 * Update card usage.
	 * @param cardUsage
	 */
	public CardUsage increaseCardUsage(CardUsage cardUsage) {
		cardUsage.increaseUsage();
		return cardUsage;
	}
}
