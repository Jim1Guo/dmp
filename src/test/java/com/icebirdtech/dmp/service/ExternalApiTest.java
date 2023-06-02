package com.icebirdtech.dmp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.icebirdtech.dmp.externalapi.ExternalApi;
import com.icebirdtech.dmp.modal.CardUsage;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExternalApiTest {
	@Mock	
	private ExternalApi externalApi;

	@InjectMocks
	private CardUsageService cardUsageService;
	
	private static final long CARD_NUMBER = 1000000000000000L;
	private int[] usageHistory = {4, 6, 10, 12, 1, 12, 2}; //47
	private CardUsage cardUsage = new CardUsage(CARD_NUMBER, usageHistory);
	
	@Test
	void testShouldCallExternalApi() {

		Mockito.when(externalApi.retrieveCardUsage(CARD_NUMBER)).thenReturn(cardUsage);
		
		CardUsage usage = cardUsageService.getCardUsage(CARD_NUMBER);
		assertEquals(47, usage.getTotal(), "Total usage matches.");
		verify(externalApi, times(1)).retrieveCardUsage(CARD_NUMBER);
	}
}
