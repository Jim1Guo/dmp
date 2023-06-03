package com.icebirdtech.dmp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icebirdtech.dmp.modal.CardUsage;
import com.icebirdtech.dmp.modal.Decision;
import com.icebirdtech.dmp.modal.Transaction;
import com.icebirdtech.dmp.modal.TransactionResponse;
import com.icebirdtech.dmp.rules.RuleContext;
import com.icebirdtech.dmp.rules.RuleEngine;
import com.icebirdtech.dmp.service.CardUsageService;
import com.icebirdtech.dmp.service.DecisionService;

/**
 * Decision Management Service to make a decision on transactions.
 *
 */
@Service
public class DecisionServiceImpl implements DecisionService{

	@Autowired
	CardUsageService cardUsageService;
	
	@Autowired
	RuleEngine ruleEngine;
	
	/**
	 * Analyze the transaction and apply business logic.
	 * @param transaction transaction
	 * @return transactionResponse with transaction, decision and card usage.
	 */
	public TransactionResponse analyzeTransaction(Transaction transaction) { //throws TransactionValidationException {
		//Get card usage
		CardUsage cardUsage = cardUsageService.getCardUsage(transaction.getCardNum());
		//Apply rules to determine result
		Decision result = ruleEngine.apply(
				RuleContext.builder().transaction(transaction)
				.cardUsage(cardUsage).build());
		//Update usage count.
		cardUsageService.increaseCardUsage(cardUsage);
		
		//Build response.
		TransactionResponse response = TransactionResponse.builder()
			.transaction(transaction)
			.decision(result)
			.usage(cardUsage)
			.build();
		return response;
	}
}
