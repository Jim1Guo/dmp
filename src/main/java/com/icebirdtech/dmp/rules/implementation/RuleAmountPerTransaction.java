package com.icebirdtech.dmp.rules.implementation;

import com.icebirdtech.dmp.modal.Decision;
import com.icebirdtech.dmp.rules.Rule;
import com.icebirdtech.dmp.rules.RuleContext;

/**
 * Rule on max amount per transaction. 
 * If the card has been used under 35 times in the last 7 days, 
 * decline the transaction if the (transaction amount/times used in last 7 days) > 500.
 *
 */
public class RuleAmountPerTransaction implements Rule {

	private static final int CARD_USAGE_THRESHOLD = 35;
	private static final Double AVERAGE_THRESHOLD = Double.valueOf(500);
	@Override
	public Decision apply(RuleContext context) {
		if (context.getCardUsage().getTotal() < CARD_USAGE_THRESHOLD) {
			Double average = context.getTransaction().getAmount()/context.getCardUsage().getTotal();
			if (average.compareTo(AVERAGE_THRESHOLD) > 0) {
				return Decision.DECLINED;
			}
		}
		return Decision.APPROVED;
	}

}
