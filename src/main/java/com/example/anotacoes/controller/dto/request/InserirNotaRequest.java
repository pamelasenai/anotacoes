package com.example.anotacoes.controller.dto.request;

public record InserirNotaRequest(
        String titulo,
        String conteudo,
        Long caderno
) {
}
