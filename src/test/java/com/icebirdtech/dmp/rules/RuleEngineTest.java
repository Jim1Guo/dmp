package com.icebirdtech.dmp.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.icebirdtech.dmp.modal.CardUsage;
import com.icebirdtech.dmp.modal.Decision;
import com.icebirdtech.dmp.modal.Transaction;
import com.icebirdtech.dmp.rules.implementation.RuleAmountPerTransaction;
import com.icebirdtech.dmp.rules.implementation.RuleCardUsed;
import com.icebirdtech.dmp.rules.implementation.RuleEngineImpl;
import com.icebirdtech.dmp.rules.implementation.RuleTransactionAmount;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RuleEngineTest {

	private static final long CARD_NUMBER = 1000000000000000L;

	@Test
	void testRuleAmountOverLimit() {
		
		double TRANSACTION_AMOUNT = 55510.20; // >50000
		Transaction transaction = Transaction.builder()
					.cardNum(CARD_NUMBER)
					.amount(TRANSACTION_AMOUNT)
					.build();
		CardUsage cardUsage = new CardUsage(CARD_NUMBER, null);
		RuleContext context = new RuleContext(transaction, cardUsage);
		
		RuleEngine ruleEngine = new RuleEngineImpl();
		ruleEngine.getRules().add(new RuleTransactionAmount());
		
		Decision result = ruleEngine.apply(context);	
		assertEquals(Decision.DECLINED, result, "Transaction Declined over limit.");
	}

	@Test
	void testRuleUsageOverLimit() {
		
		double TRANSACTION_AMOUNT = 10.20;
		Transaction transaction = Transaction.builder()
					.cardNum(CARD_NUMBER)
					.amount(TRANSACTION_AMOUNT)
					.build();
		int[] usageHistory = {24, 6, 10, 12, 1, 12, 2}; //67 in 7 days > 60
		CardUsage cardUsage = new CardUsage(CARD_NUMBER, usageHistory);
		RuleContext context = new RuleContext(transaction, cardUsage);
		
		RuleEngine ruleEngine = new RuleEngineImpl();
		ruleEngine.getRules().add(new RuleCardUsed());
		
		Decision result = ruleEngine.apply(context);	
		assertEquals(Decision.DECLINED, result, "Transaction Declined over usage.");
	}
	

	@Test
	void testRuleAmountPerTransaction() {
		
		double TRANSACTION_AMOUNT = 20385;
		Transaction transaction = Transaction.builder()
					.cardNum(CARD_NUMBER)
					.amount(TRANSACTION_AMOUNT)
					.build();
		int[] usageHistory = {4, 6, 10, 2, 1, 2, 2}; 
		//27 in 7 days < 35
		//20385 / 27 = 755 per transaction > 500
		CardUsage cardUsage = new CardUsage(CARD_NUMBER, usageHistory);
		RuleContext context = new RuleContext(transaction, cardUsage);
		
		RuleEngine ruleEngine = new RuleEngineImpl();
		ruleEngine.getRules().add(new RuleAmountPerTransaction());
		
		Decision result = ruleEngine.apply(context);	
		assertEquals(Decision.DECLINED, result, "Transaction Declined over amount per transaction.");
	}

	@Test
	void testRuleApproved() {
		
		double TRANSACTION_AMOUNT = 35485;
		Transaction transaction = Transaction.builder()
					.cardNum(CARD_NUMBER)
					.amount(TRANSACTION_AMOUNT)
					.build();
		//47 in 7 days > 35
		//35485/47 = 755 per transaction > 500
		int[] usageHistory = {4, 6, 10, 12, 1, 12, 2}; 
		CardUsage cardUsage = new CardUsage(CARD_NUMBER, usageHistory);
		RuleContext context = new RuleContext(transaction, cardUsage);
		
		RuleEngine ruleEngine = new RuleEngineImpl();
		ruleEngine.getRules().add(new RuleTransactionAmount());
		ruleEngine.getRules().add(new RuleCardUsed());
		ruleEngine.getRules().add(new RuleAmountPerTransaction());
		
		Decision result = ruleEngine.apply(context);	
		assertEquals(Decision.APPROVED, result, "Transaction approved.");
	}

}
