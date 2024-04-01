package com.example.anotacoes.controller;

import com.example.anotacoes.controller.dto.request.InserirUsuarioRequest;
import com.example.anotacoes.datasource.entity.UsuarioEntity;
import com.example.anotacoes.datasource.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsuarioController {
    private final BCryptPasswordEncoder bCryptEncoder;
    private final UsuarioRepository usuarioRepository;

    @PostMapping("/cadastro")
    public ResponseEntity<String> newUser(@RequestBody InserirUsuarioRequest inserirUsuarioRequest) throws Exception {
        boolean usuarioExistente = usuarioRepository.findByNomeUsuario(inserirUsuarioRequest.nomeUsuario())
                .isPresent();

        if (usuarioExistente){
            throw new Exception("Usuário já cadastrado.");
        }

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNomeUsuario(inserirUsuarioRequest.nomeUsuario());
        usuario.setSenha(bCryptEncoder.encode(inserirUsuarioRequest.senha()).toString());

        usuarioRepository.save(usuario);

        return new ResponseEntity<>("Criado", HttpStatus.CREATED);
    }

}
