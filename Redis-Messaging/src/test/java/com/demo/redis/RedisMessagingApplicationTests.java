package com.demo.redis;

import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.redis.config.RedisConfig;
import com.demo.redis.publicar.RedisPublicarMensaje;
import com.demo.redis.suscriptor.RedisSuscriptorMensaje;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RedisConfig.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class RedisMessagingApplicationTests {


//    private static redis.embedded.RedisServer redisServer;
    
    @Autowired
    private RedisPublicarMensaje redisPublicarMensaje;
  /*  
    @BeforeClass
    public static void startRedisServer() throws IOException {
        redisServer = new redis.embedded.RedisServer(6379);
        redisServer.start();
    }
    
    @AfterClass
    public static void stopRedisServer() throws IOException {
        redisServer.stop();
    }
*/
    @Test
    public void testEnviarRecibir() throws Exception {
        String mensaje = "mensaje " + UUID.randomUUID();
        redisPublicarMensaje.publicar(mensaje);
        Thread.sleep(100);
        assertTrue(RedisSuscriptorMensaje.listaMensajes.get(0).contains(mensaje));
    }
}
