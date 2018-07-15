package com.demo.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import com.demo.redis.publicar.PublicarMensaje;
import com.demo.redis.publicar.RedisPublicarMensaje;
import com.demo.redis.suscriptor.RedisSuscriptorMensaje;

@Configuration
public class RedisConfig {
	

	@Bean
	MessageListenerAdapter messageListener() { 
	    return new MessageListenerAdapter(new RedisSuscriptorMensaje());
	}
	
	@Bean
	RedisMessageListenerContainer redisContainer() {
	    RedisMessageListenerContainer container 
	      = new RedisMessageListenerContainer(); 
	    container.setConnectionFactory(jedisConnectionFactory()); 
	    container.addMessageListener(messageListener(), topic()); 
	    return container; 
	}
	
	@Bean
	PublicarMensaje redisPublisher() { 
	    return new RedisPublicarMensaje(redisTemplate(), topic());
	}
	
	@Bean
	ChannelTopic topic() {
	    return new ChannelTopic("mensajeDemo");
	}
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
		jedisConFactory.setHostName("localhost");
		jedisConFactory.setPort(6379);
		return jedisConFactory;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		return template;
	}
	

}
