package com.icebirdtech.dmp.rules;

import java.util.List;

import com.icebirdtech.dmp.modal.Decision;

/**
 * RuleEngine simple implementation.
 *
 */
public interface RuleEngine {
	/**
	 * Get Rules
	 * @return list of Rules
	 */
	public List<Rule> getRules();
	
	/**
	 * Set Rules
	 * @param rules
	 */
	public void setRules(List<Rule> rules);

	/**
	 * Apply rules
	 * @param ruleContext 
	 * @return decision
	 */
	public Decision apply(RuleContext ruleContext);
}
