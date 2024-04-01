package com.example.anotacoes.service;

import com.example.anotacoes.controller.dto.request.InserirUsuarioRequest;
import com.example.anotacoes.datasource.entity.UsuarioEntity;
import com.example.anotacoes.datasource.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final BCryptPasswordEncoder bCryptencoder;
    private final UsuarioRepository usuarioRepository;
    public void criarUsuario(InserirUsuarioRequest inserirUsuarioRequest) throws Exception {
        boolean usuarioExistente = usuarioRepository.findByNomeUsuario(inserirUsuarioRequest.nomeUsuario())
                .isPresent();

        if (usuarioExistente) {
            throw new Exception("Usuário já cadastrado.");
        }

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNomeUsuario(inserirUsuarioRequest.nomeUsuario());
        usuario.setSenha(bCryptencoder.encode(inserirUsuarioRequest.senha()).toString());

        usuarioRepository.save(usuario);
    }
}
