package com.icebirdtech.dmp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.icebirdtech.dmp.modal.CardUsage;
import com.icebirdtech.dmp.modal.Decision;
import com.icebirdtech.dmp.modal.Transaction;
import com.icebirdtech.dmp.modal.TransactionResponse;
import com.icebirdtech.dmp.rules.RuleEngine;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DecisionServiceTest {
	@Mock	
	private CardUsageService cardUsageService;

	@Mock	
	private RuleEngine ruleEngine;
	
	@InjectMocks
	private DecisionService decisionService;
	
	private static final long CARD_NUMBER = 1000000000000000L;
	
	private int[] usageHistory = {4, 6, 10, 12, 1, 12, 2}; //47
	private CardUsage cardUsage = new CardUsage(CARD_NUMBER, usageHistory);
	
	@Test
	void testShouldCallRuleEngineAndIncreaseCardUsage() {
		Transaction transaction = Transaction.builder()
				.cardNum(CARD_NUMBER)
				.amount(25510.20)
				.build();
		Mockito.when(cardUsageService.getCardUsage(CARD_NUMBER)).thenReturn(cardUsage);
		Mockito.when(ruleEngine.apply(any())).thenReturn(Decision.APPROVED);

		TransactionResponse transactionResponse = decisionService.analyzeTransaction(transaction);
		assertEquals(Decision.APPROVED, transactionResponse.getDecision(), "Transaction Declined.");
		//Should increase card usage
		verify(ruleEngine, times(1)).apply(any());
		//Should increase card usage
		verify(cardUsageService, times(1)).increaseCardUsage(any());
	}

}
