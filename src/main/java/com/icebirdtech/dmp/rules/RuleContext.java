package com.icebirdtech.dmp.rules;

import com.icebirdtech.dmp.modal.CardUsage;
import com.icebirdtech.dmp.modal.Transaction;

import lombok.Builder;
import lombok.Data;

/**
 * Rule context.
 * Data passed in to the rule engine.
 */
@Data
@Builder
public class RuleContext {
	private Transaction transaction;
	private CardUsage cardUsage;
}
