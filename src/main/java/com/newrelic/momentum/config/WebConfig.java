
/*
* Copyright ©2016, Ciber Inc All Rights Reserved
*/


package com.newrelic.momentum.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.newrelic.automotive.aspect.AuditAfterAspect;
import com.newrelic.automotive.aspect.LoggingAspect;
import com.newrelic.momentum.converter.DefaultJacksonHttpMessageConverter;
import com.newrelic.momentum.rest.handler.DefaultRestErrorResolver;
import com.newrelic.momentum.rest.handler.RestExceptionHandler;

@Configuration
@ComponentScan({"com.newrelic.momentum", "com.newrelic.automotive"})
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter{	
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(converter());
	  }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(i18NInterceptor());
	}
	
	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		exceptionResolvers.add(restExceptionHandler());
	}
	
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.defaultContentType(MediaType.APPLICATION_JSON);
		configurer.favorPathExtension(false);
		configurer.favorParameter(true);
		configurer.parameterName("formatType");
		configurer.ignoreAcceptHeader(false);
		Map<String, MediaType> mediaTypesMap = new HashMap<String, MediaType>();
		mediaTypesMap.put("json", MediaType.APPLICATION_JSON);
		mediaTypesMap.put("json", MediaType.APPLICATION_XML);		
		configurer.mediaTypes(mediaTypesMap);
	}
	
	@Bean
	public DefaultJacksonHttpMessageConverter converter() {
		DefaultJacksonHttpMessageConverter converter = new DefaultJacksonHttpMessageConverter();
		converter.setPrettyPrint(true);
		return converter;
	}	
	
	@Bean
	public HandlerInterceptor i18NInterceptor(){
		LocaleChangeInterceptor i18n = new LocaleChangeInterceptor();
		return i18n;
	}
	
	@Bean
	public RestExceptionHandler restExceptionHandler(){
		RestExceptionHandler restExceptionHandler = new RestExceptionHandler();
		restExceptionHandler.setOrder(100);		
		HttpMessageConverter<?>[] messageConverters = new HttpMessageConverter<?>[]{converter()};
		restExceptionHandler.setMessageConverters(messageConverters);		
		restExceptionHandler.setErrorResolver(restErrorResolver());		
		return restExceptionHandler;
	}
	
	@Bean
	public DefaultRestErrorResolver restErrorResolver(){
		DefaultRestErrorResolver restErrorResolver = new DefaultRestErrorResolver();
		
		restErrorResolver.setLocaleResolver(new AcceptHeaderLocaleResolver());
		restErrorResolver.setDefaultMoreInfoUrl("asingh@ciber.com");
		
		Map<String, String> exceptionDefinitionMap = new HashMap<String, String>();
		exceptionDefinitionMap.put("com.newrelic.automotive.exception.UnknownResourceException", "404, _exmsg");
		exceptionDefinitionMap.put("Throwable", "500");		
		restErrorResolver.setExceptionMappingDefinitions(exceptionDefinitionMap);
		
		Map<String, String> definitions = restErrorResolver.createDefaultExceptionMappingDefinitions();
		restErrorResolver.setExceptionMappings(restErrorResolver.toRestErrors(definitions));
		
		restErrorResolver.setMessageSource(restErrorResolver.getMessageSource());
		
		return restErrorResolver;
	}	
	
	@Bean
	public LoggingAspect loggingAspect(){
		return new LoggingAspect();
	}
	
	@Bean
	public AuditAfterAspect auditAfterAspect(){
		return new AuditAfterAspect();
	}
	
}
