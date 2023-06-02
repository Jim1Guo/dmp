
package com.icebirdtech.dmp.modal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * transaction
 * <p>
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    /**
     * cardnum
     */
    @Min(value = 1000000000000000L, message = "Invalid Card Number.")
    @Max(value = 9999999999999999L, message = "Invalid Card Number.")
    private Long cardNum;
    /**
     * amount
     * <p>
     */
    @DecimalMin(value = "0.01", message = "Amount must be larger than 0.")
    private Double amount;
}
