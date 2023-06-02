package com.icebirdtech.dmp.rules;

import com.icebirdtech.dmp.modal.Decision;

/**
 * Rule interface
 *
 */
public interface Rule {
	/**
	 * Apply a rule with the given context
	 * @param context
	 * @return
	 */
	Decision apply(RuleContext context);

}
