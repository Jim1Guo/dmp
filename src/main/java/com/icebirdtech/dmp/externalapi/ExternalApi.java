package com.icebirdtech.dmp.externalapi;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.icebirdtech.dmp.log.DmpLogger;
import com.icebirdtech.dmp.modal.CardUsage;

import reactor.core.publisher.Flux;

/**
 * External API call to retrieve card usage history.
 * an imaginary external microservice built by another team in our organization 
 * that returns how many times a card has been used in each of the past 7 days. 
 * This service can be called using the following endpoint:
 * https://www.random.org/integers/?num=7&min=0&max=12&col=1&base=10&format=plain&rnd=new
 *
 */
@Service
public class ExternalApi {

	@Autowired
	private WebClient webClient;

	@Value( "${externalapi.cardHistoryService}" )
	private String cardHistoryServiceUri;
	
	/**
	 * Retrieve card usage from external service.
	 * @param cardNum
	 * @return
	 */
	public CardUsage retrieveCardUsage(Long cardNum) {
	    Flux<String> usageStream = webClient.get().uri(cardHistoryServiceUri)
	            .retrieve()
	            .bodyToFlux(String.class);
	    List<String> usageList = usageStream.collectList().block();
//	    List<Integer> usageList = usageStream.collectList().block();
	    int[] usageArray = Arrays.copyOfRange(usageList.stream().mapToInt(i->Integer.parseInt(i)).toArray(), 0, 7);
	    DmpLogger.logInfo("Get card usage hitory from external API: " + Arrays.toString(usageArray));
	    return new CardUsage(cardNum, usageArray);
	}

}
