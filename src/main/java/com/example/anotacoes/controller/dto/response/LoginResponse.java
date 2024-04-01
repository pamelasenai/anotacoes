package com.example.anotacoes.controller.dto.response;

public record LoginResponse(
        String valorJWT,
        long tempoExpiracao
) {
}
