package com.example.anotacoes.controller;

import com.example.anotacoes.controller.dto.request.InserirCadernoRequest;
import com.example.anotacoes.controller.dto.response.CadernoResponse;
import com.example.anotacoes.service.CadernoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cadernos")
public class CadernoController {
    private final CadernoService service;

    @GetMapping
    public ResponseEntity<List<CadernoResponse>> get(
            JwtAuthenticationToken token
    ) {
        return ResponseEntity.ok().body((service.buscarTodos(token)));
    }

    @GetMapping("{id}")
    public ResponseEntity<CadernoResponse> getPorId(
            JwtAuthenticationToken token,
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok().body(service.buscarPorId(id, token));
    }

    @PostMapping
    public ResponseEntity<CadernoResponse> post(
            JwtAuthenticationToken token,
            @RequestBody InserirCadernoRequest caderno
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(caderno, token));
    }

    @PutMapping("{id}")
    public ResponseEntity<CadernoResponse> put(
            JwtAuthenticationToken token,
            @PathVariable Long id,
            @RequestBody InserirCadernoRequest caderno
    ) throws Exception {
        return ResponseEntity.ok().body(service.alterar(id, caderno, token));
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
