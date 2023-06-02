package com.icebirdtech.dmp.rules.implementation;

import com.icebirdtech.dmp.modal.Decision;
import com.icebirdtech.dmp.rules.Rule;
import com.icebirdtech.dmp.rules.RuleContext;

/**
 * Rule on card usage.
 * If the card has been used over 60 times in the last 7 days, decline the transaction.
 */
public class RuleCardUsed implements Rule {

	private static final int CARD_USAGE_LIMIT = 60;

	@Override
	public Decision apply(RuleContext context) {
		if (context.getCardUsage().getTotal() > CARD_USAGE_LIMIT)
			return Decision.DECLINED;
		return Decision.APPROVED;
	}
}
