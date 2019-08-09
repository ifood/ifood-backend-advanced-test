package br.com.ariki.music.suggestion.by.weather.config.logging;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "config.slf4jfilter")
public class Slf4jMDCFilterConfiguration {
	
	public static final String DEFAULT_RESPONSE_TOKEN_HEADER = "Response_Token";
	public static final String DEFAULT_MDC_UUID_TOKEN_KEY = "Slf4jMDCFilter.UUID";

	private String responseHeader = DEFAULT_RESPONSE_TOKEN_HEADER;
	private String mdcTokenKey = DEFAULT_MDC_UUID_TOKEN_KEY;
	private String requestHeader = null;

	@Bean
	public FilterRegistrationBean<Slf4jMDCFilter> servletRegistrationBean() {
		final FilterRegistrationBean<Slf4jMDCFilter> registrationBean = new FilterRegistrationBean<>();
		final Slf4jMDCFilter log4jMDCFilterFilter = new Slf4jMDCFilter(responseHeader, mdcTokenKey, requestHeader);
		registrationBean.setFilter(log4jMDCFilterFilter);
		registrationBean.setOrder(2);
		return registrationBean;
	}
}
