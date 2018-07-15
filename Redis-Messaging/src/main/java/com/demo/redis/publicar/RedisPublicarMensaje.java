package com.demo.redis.publicar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

public class RedisPublicarMensaje implements PublicarMensaje {
	 
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ChannelTopic topic;
 
    public RedisPublicarMensaje() {
    }
 
    public RedisPublicarMensaje(
      RedisTemplate<String, Object> redisTemplate, ChannelTopic topic) {
      this.redisTemplate = redisTemplate;
      this.topic = topic;
    }
 
    public void publicar(String message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
        System.out.println("Mensaje Publicado: " + message);
    }
}