package tdunu.MsSeguridad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tdunu.MsSeguridad.model.request.LoginRequest;
import tdunu.MsSeguridad.model.response.LoginResponse;
import tdunu.MsSeguridad.service.AuthService;
import tdunu.MsSeguridad.util.ApiRoutes;

@RestController
@RequestMapping(ApiRoutes.AUTH.BASE)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(ApiRoutes.AUTH.LOGIN)
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
