package com.icebirdtech.dmp.rules.implementation;

import com.icebirdtech.dmp.modal.Decision;
import com.icebirdtech.dmp.rules.Rule;
import com.icebirdtech.dmp.rules.RuleContext;

/**
 * Rule on amount per transaction.
 * If the amount of a transaction is over $50,000.00 decline the transaction.
 */
public class RuleTransactionAmount implements Rule {

	private static final Double TRANSACTION_LIMIT = Double.valueOf(50000);

	@Override
	public Decision apply(RuleContext context) {
		if (context.getTransaction().getAmount().compareTo(TRANSACTION_LIMIT) > 0)
			return Decision.DECLINED;
		return Decision.APPROVED;
	}

}
