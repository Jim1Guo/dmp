package com.icebirdtech.dmp.web.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.icebirdtech.dmp.modal.CardUsage;
import com.icebirdtech.dmp.modal.Decision;
import com.icebirdtech.dmp.modal.Transaction;
import com.icebirdtech.dmp.modal.TransactionRequest;
import com.icebirdtech.dmp.modal.TransactionResponse;
import com.icebirdtech.dmp.service.DecisionService;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DmpControllerTest {

	@InjectMocks
	private DmpController dmpController;
	
	@Mock
	private DecisionService decisionService;
	
	private static final long CARD_NUMBER = 1000000000000000L;
	
	@Test
	void testShouldDeclineTransaction() {
		Transaction transaction = Transaction.builder()
				.cardNum(CARD_NUMBER)
				.amount(55510.20)
				.build();
		TransactionRequest request = TransactionRequest.builder()
				.transaction(transaction)
				.build();
		TransactionResponse response = TransactionResponse.builder()
				.transaction(transaction)
				.decision(Decision.DECLINED)
				.usage(new CardUsage(CARD_NUMBER, null))
				.build();
		Mockito.when(decisionService.analyzeTransaction(transaction)).thenReturn(response);
		
		ResponseEntity<TransactionResponse> transactionResponse = dmpController.analyzeTransaction(request);
		assertEquals(Decision.DECLINED, transactionResponse.getBody().getDecision(), "Transaction Declined.");
	}

	@Test
	void testShouldApproveTransaction() {
		Transaction transaction = Transaction.builder()
				.cardNum(CARD_NUMBER)
				.amount(10.20)
				.build();
		TransactionRequest request = TransactionRequest.builder()
				.transaction(transaction)
				.build();
		TransactionResponse response = TransactionResponse.builder()
				.transaction(transaction)
				.decision(Decision.APPROVED)
				.usage(new CardUsage(CARD_NUMBER, null))
				.build();
		Mockito.when(decisionService.analyzeTransaction(transaction)).thenReturn(response);
		
		ResponseEntity<TransactionResponse> transactionResponse = dmpController.analyzeTransaction(request);
		assertEquals(Decision.APPROVED, transactionResponse.getBody().getDecision(), "Transaction Approved.");
	}

}
