package com.icebirdtech.dmp.modal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TransactionRequest
 * <p>
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    /**
     * transaction
     */
	@Valid
	@NotNull(message = "Transaction is required.")
    private Transaction transaction;
}