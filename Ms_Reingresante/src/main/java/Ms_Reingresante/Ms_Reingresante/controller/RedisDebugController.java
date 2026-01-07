package Ms_Reingresante.Ms_Reingresante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisDebugController {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @GetMapping("/debug/redis")
    public String debugRedis() {
        try {
            var connection = redisConnectionFactory.getConnection();
            
            // Test 1: Ping
            String ping = connection.ping();
            
            // Test 2: Autenticación implícita (debería estar autenticado)
            connection.set("debug-test".getBytes(), "OK".getBytes());
            byte[] result = connection.get("debug-test".getBytes());
            
            connection.del("debug-test".getBytes());
            
            return String.format("""
                Redis Debug Info:
                - Ping: %s
                - SET/GET: %s
                - Connection Factory: %s
                - Class: %s
                """, 
                ping, 
                new String(result),
                redisConnectionFactory.getClass().getSimpleName(),
                connection.getClass().getSimpleName()
            );
            
        } catch (Exception e) {
            return String.format("""
                Redis ERROR:
                - Message: %s
                - Error Type: %s
                - Cause: %s
                """,
                e.getMessage(),
                e.getClass().getName(),
                e.getCause() != null ? e.getCause().getMessage() : "None"
            );
        }
    }
}