package com.demo.redis.suscriptor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class RedisSuscriptorMensaje implements MessageListener {
 
    public static List<String> listaMensajes = new ArrayList<>();
 
    

	@Override
	public void onMessage(Message message, byte[] arg1) {
		listaMensajes.add(message.toString());
        System.out.println("Mensaje Recibido: " + message.toString());	
	}
}