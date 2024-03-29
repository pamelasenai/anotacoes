package com.example.anotacoes.controller;

import com.example.anotacoes.controller.dto.request.InserirUsuarioRequest;
import com.example.anotacoes.datasource.entity.PerfilEntity;
import com.example.anotacoes.datasource.entity.UsuarioEntity;
import com.example.anotacoes.datasource.repository.PerfilRepository;
import com.example.anotacoes.datasource.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class UsuarioController {
    private final BCryptPasswordEncoder bCryptencoder;
    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;

    @PostMapping("/cadastro")
    public ResponseEntity<String> newUser(
            @RequestBody InserirUsuarioRequest inserirUsuarioRequest
    ) throws Exception {
        PerfilEntity perfilEntity = perfilRepository.findByNomePerfil("Usuario").get();

        boolean usuarioExistente = usuarioRepository.findByNomeUsuario(inserirUsuarioRequest.nomeUsuario())
                .isPresent();

        if(usuarioExistente) {
            throw new Exception("Usuário já cadastrado.");
        }

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNomeUsuario(inserirUsuarioRequest.nomeUsuario());
        usuario.setSenha(bCryptencoder.encode(inserirUsuarioRequest.senha()).toString());
        usuario.setPerfis(Set.of(perfilEntity));

        usuarioRepository.save(usuario);

        return new ResponseEntity<>("Novo usuário criado com sucesso.", HttpStatus.CREATED);
    }
}
