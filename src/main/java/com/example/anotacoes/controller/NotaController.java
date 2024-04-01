package com.example.anotacoes.controller;

import com.example.anotacoes.controller.dto.request.InserirNotaRequest;
import com.example.anotacoes.controller.dto.response.NotaResponse;
import com.example.anotacoes.service.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notas")
public class NotaController {
    private final NotaService service;

    @GetMapping
    public ResponseEntity<List<NotaResponse>> get(
            JwtAuthenticationToken token
    ) {
        return ResponseEntity.ok().body((service.buscarTodos(token)));
    }

    @GetMapping("{id}")
    public ResponseEntity<NotaResponse> getPorId(
            JwtAuthenticationToken token,
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok().body(service.buscarPorId(id, token));
    }

    @PostMapping
    public ResponseEntity<NotaResponse> post(
            JwtAuthenticationToken token,
            @RequestBody InserirNotaRequest nota
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(nota, token));
    }

    @PutMapping("{id}")
    public ResponseEntity<NotaResponse> put(
            JwtAuthenticationToken token,
            @PathVariable Long id,
            @RequestBody InserirNotaRequest nota
    ) throws Exception {
        return ResponseEntity.ok().body(service.alterar(id, nota, token));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(
            JwtAuthenticationToken token,
            @PathVariable Long id
    ) throws Exception {
        service.excluir(id, token);
        return ResponseEntity.noContent().build();
    }
}
