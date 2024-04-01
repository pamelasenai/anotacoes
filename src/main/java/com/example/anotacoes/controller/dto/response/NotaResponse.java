package com.example.anotacoes.controller.dto.response;

public record NotaResponse(
        Long id,
        String titulo,
        String conteudo,
        Long caderno
) {
}
