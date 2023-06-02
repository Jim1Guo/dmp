package com.icebirdtech.dmp.web.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.util.StringUtils;

@Component
@Order(1)
/**
 * Request correlation, create unique request id to help tracing the request.
 *
 */
public class RequestCorrelationFilter implements Filter {

	private static final String REQUEST_CORRELATION_ID = "request-correlation-id";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest mutableRequest = (HttpServletRequest)request;
		String requestId = mutableRequest.getHeader(REQUEST_CORRELATION_ID);
		if (StringUtils.isEmpty(requestId)) {
			requestId = UUID.randomUUID().toString();
		}
		MDC.put(REQUEST_CORRELATION_ID, requestId);
		try {
			chain.doFilter(mutableRequest, response);
		} finally {
			MDC.remove(REQUEST_CORRELATION_ID);
		}
	}

}
