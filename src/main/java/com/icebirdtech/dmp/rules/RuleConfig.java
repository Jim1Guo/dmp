package com.icebirdtech.dmp.rules;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.icebirdtech.dmp.rules.implementation.RuleAmountPerTransaction;
import com.icebirdtech.dmp.rules.implementation.RuleCardUsed;
import com.icebirdtech.dmp.rules.implementation.RuleTransactionAmount;

/**
 * Rule Configuration
 *
 */
@Configuration
public class RuleConfig {

    @Bean
    @Order(1)
    public Rule getRuleTransactionAmount() {
        return new RuleTransactionAmount();
    }
	
    @Bean
    @Order(2)
    public Rule getRuleCardUsed() {
        return new RuleCardUsed();
    }
	
    @Bean
    @Order(3)
    public Rule getRuleAmountPerTransaction() {
        return new RuleAmountPerTransaction();
    }
}
