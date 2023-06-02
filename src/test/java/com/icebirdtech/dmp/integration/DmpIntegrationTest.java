package com.icebirdtech.dmp.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.icebirdtech.dmp.modal.Decision;
import com.icebirdtech.dmp.modal.Transaction;
import com.icebirdtech.dmp.modal.TransactionRequest;
import com.icebirdtech.dmp.modal.TransactionResponse;
import com.icebirdtech.dmp.web.controller.DmpController;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class DmpIntegrationTest {

	@Autowired
	private DmpController dmpController;
	
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
	
		ResponseEntity<TransactionResponse> transactionResponse = dmpController.analyzeTransaction(request);
		assertEquals(Decision.DECLINED, transactionResponse.getBody().getDecision(), "Transaction Declined.");
	}
}
