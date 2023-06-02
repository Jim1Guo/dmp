package com.icebirdtech.dmp.externalapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import com.icebirdtech.dmp.log.DmpLogger;

/**
 * Config to initialize WebClient
 *
 */
@Configuration
public class WebClientConfig {

	@Value( "${externalapi.baseurl}" )
	private String baseUrl;
	
	@Value( "${externalapi.cardHistoryService}" )
	private String cardHistoryServiceUri;
	
    @Bean
    public WebClient getWebClient() {
    	DmpLogger.logApplicationInfo("External API baseUrl: "+ baseUrl);
    	DmpLogger.logApplicationInfo("External API uri: "+ cardHistoryServiceUri);
        return WebClient.builder().baseUrl(baseUrl).build();
    }
}
