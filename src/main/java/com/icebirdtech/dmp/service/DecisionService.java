package com.icebirdtech.dmp.service;

import com.icebirdtech.dmp.modal.Transaction;
import com.icebirdtech.dmp.modal.TransactionResponse;

/**
 * Decision Management Service to make a decision on transactions.
 *
 */
public interface DecisionService {
	/**
	 * Analyze the transaction and apply business logic.
	 * @param transaction transaction
	 * @return transactionResponse with transaction, decision and card usage.
	 */
	public TransactionResponse analyzeTransaction(Transaction transaction);
}
