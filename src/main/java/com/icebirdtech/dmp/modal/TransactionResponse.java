
package com.icebirdtech.dmp.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * transactionResponse
 * <p>
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

    /**
     * transaction
     */
    private Transaction transaction;
    /**
     * result
     * <p>
     */
    private Decision decision;
    /**
     * usage
     */
    private CardUsage usage;


}
