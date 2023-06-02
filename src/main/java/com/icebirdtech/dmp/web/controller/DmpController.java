package com.icebirdtech.dmp.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.icebirdtech.dmp.log.DmpLogger;
import com.icebirdtech.dmp.modal.Transaction;
import com.icebirdtech.dmp.modal.TransactionRequest;
import com.icebirdtech.dmp.modal.TransactionResponse;
import com.icebirdtech.dmp.service.DecisionService;

@Validated
@RestController
public class DmpController {

	@Autowired
	private DecisionService analyzeService;

	@PostMapping(value={"/analyzeTransaction"})
	public ResponseEntity<TransactionResponse> analyzeTransaction(@Valid @RequestBody TransactionRequest request) {
		try{
			Transaction transaction = request.getTransaction();
			DmpLogger.logTransaction("Transaction request received.", transaction);
			TransactionResponse response = analyzeService.analyzeTransaction(transaction);
			DmpLogger.logResponse("Transaction request processed.", response);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			DmpLogger.logError(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
