package tdunu.MsSeguridad.service;

import tdunu.MsSeguridad.model.request.LoginRequest;
import tdunu.MsSeguridad.model.response.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);
}
