package com.example.anotacoes.controller;

import com.example.anotacoes.controller.dto.request.InserirUsuarioRequest;
import com.example.anotacoes.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService service;

    @PostMapping("/cadastro")
    public ResponseEntity<String> newUser(@RequestBody InserirUsuarioRequest inserirUsuarioRequest) throws Exception {
        service.criarUsuario(inserirUsuarioRequest);
        return new ResponseEntity<>("Usuário cadastrado com sucesso.", HttpStatus.CREATED);
    }

}
