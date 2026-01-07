package Ms_Reingresante.Ms_Reingresante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/{key}")
    public ResponseEntity<String> getFromRedis(@PathVariable String key) {
        String value = redisTemplate.opsForValue().get(key);
        if (value == null) {
            return ResponseEntity.ok("No se encontró el dato para la clave: " + key);
        }
        return ResponseEntity.ok(value);
    }

    @PostMapping("/{key}")
    public ResponseEntity<String> setToRedis(@PathVariable String key, @RequestBody String value) {
        redisTemplate.opsForValue().set(key, value);
        return ResponseEntity.ok("Dato guardado correctamente: " + key + " = " + value);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testConnection() {
        try {
            String testKey = "test-connection";
            String testValue = "Redis funciona: " + System.currentTimeMillis();
            
            redisTemplate.opsForValue().set(testKey, testValue);
            String retrievedValue = redisTemplate.opsForValue().get(testKey);
            
            if (testValue.equals(retrievedValue)) {
                return ResponseEntity.ok("✅ Redis conectado correctamente!");
            } else {
                return ResponseEntity.ok("⚠ Redis responde pero valores no coinciden");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500)
                .body("❌ Error conectando a Redis: " + e.getMessage());
        }
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<String> deleteFromRedis(@PathVariable String key) {
        Boolean deleted = redisTemplate.delete(key);
        if (Boolean.TRUE.equals(deleted)) {
            return ResponseEntity.ok("Clave eliminada: " + key);
        }
        return ResponseEntity.ok("Clave no encontrada: " + key);
    }
}