package com.example.anotacoes.controller.dto.request;

public record LoginRequest(
        String nomeUsuario,
        String senha
) {
}
