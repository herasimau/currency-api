package com.currency.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import java.util.List;
/**
 * @author herasimau on 28.08.2016.
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {



	static final String MESSAGE_PREFIX = "/topic";

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/currency").withSockJS();

	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker(MESSAGE_PREFIX);
		registry.setApplicationDestinationPrefixes("/app");

	}
	@Override
	public boolean configureMessageConverters(List<MessageConverter> converters) {
		converters.add(new StringMessageConverter());
		return false; // Prevent registration of default converters
	}


}