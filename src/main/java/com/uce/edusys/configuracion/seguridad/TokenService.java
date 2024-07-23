package com.uce.edusys.configuracion.seguridad;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.uce.edusys.repository.modelo.Representante;

@Service
public class TokenService {
    private Map<String, String> tokenStorage = new HashMap<>();
    private final int TOKEN_EXPIRATION = 30; // minutos

    public String createToken(Representante representante) {
        String token = UUID.randomUUID().toString();
        tokenStorage.put(token, representante.getEmail());
        // Eliminar el token después de la expiración
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                tokenStorage.remove(token);
            }
        }, TOKEN_EXPIRATION * 60 * 1000);
        return token;
    }

    public String validateToken(String token) {
        return tokenStorage.get(token);
    }
}
