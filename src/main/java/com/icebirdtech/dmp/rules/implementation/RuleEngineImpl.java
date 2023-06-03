package com.icebirdtech.dmp.rules.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.icebirdtech.dmp.modal.Decision;
import com.icebirdtech.dmp.rules.Rule;
import com.icebirdtech.dmp.rules.RuleContext;
import com.icebirdtech.dmp.rules.RuleEngine;

/**
 * RuleEngine simple implementation.
 *
 */
@Component
public class RuleEngineImpl implements RuleEngine {

	@Autowired
	private List<Rule> rules = new ArrayList<>();
	
	/**
	 * Get Rules
	 * @return list of Rules
	 */
	public List<Rule> getRules() {
		return rules;
	}
	
	/**
	 * Set Rules
	 * @param rules
	 */
	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	/**
	 * Apply rules
	 * @param ruleContext 
	 * @return decision
	 */
	public Decision apply(RuleContext ruleContext) {
		//return TransactionResponse.Result.DECLINED;
		for (Rule rule : rules) {
			Decision result = rule.apply(ruleContext);
			if (result.equals(Decision.DECLINED)) {
				return result;
			}
		}
		return Decision.APPROVED;
	}
}
