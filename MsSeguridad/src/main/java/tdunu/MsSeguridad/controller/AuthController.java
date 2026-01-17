package tdunu.MsSeguridad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tdunu.MsSeguridad.model.request.LoginRequest;
import tdunu.MsSeguridad.model.response.LoginResponse;
import tdunu.MsSeguridad.service.AuthService;
import tdunu.MsSeguridad.service.KafkaProducerService;
import tdunu.MsSeguridad.util.ApiRoutes;

@RestController
@RequestMapping(ApiRoutes.AUTH.BASE)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final KafkaProducerService kafkaProducerService;

    @PostMapping(ApiRoutes.AUTH.LOGIN)
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request) {

        LoginResponse response = authService.login(request);

        kafkaProducerService.enviarEventoUsuario(
                "Usuario " + request.getCodUsuario() + " inició sesión"
        );

        return ResponseEntity.ok(response);
    }
}
