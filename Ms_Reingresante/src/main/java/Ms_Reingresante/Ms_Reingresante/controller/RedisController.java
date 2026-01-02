package Ms_Reingresante.Ms_Reingresante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/redis/{key}")
    public String getFromRedis(@PathVariable String key) {
        String value = redisTemplate.opsForValue().get(key);
        if (value == null) {
            return "No se encontró el dato para la clave: " + key;
        }
        return value;
    }
}
