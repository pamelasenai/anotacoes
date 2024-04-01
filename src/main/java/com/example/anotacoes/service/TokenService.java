package com.example.anotacoes.service;

import com.example.anotacoes.controller.dto.request.LoginRequest;
import com.example.anotacoes.datasource.entity.UsuarioEntity;
import com.example.anotacoes.datasource.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final BCryptPasswordEncoder bCryptEncoder;
    private final JwtEncoder jwtEncoder;
    private final UsuarioRepository usuarioRepository;
    private static long TEMPO_EXPIRACAO = 36000L;

    public String logar(LoginRequest loginRequest) throws Exception {
        UsuarioEntity usuarioEntity = usuarioRepository.findByNomeUsuario(loginRequest.nomeUsuario())
                .orElseThrow(() -> new Exception("Erro, usuário não existe"));

        if (!usuarioEntity.senhaValida(loginRequest, bCryptEncoder)) {
            throw new Exception("Erro ao Logar, senha incorreta");
        }

        Instant now = Instant.now();

        String scope = "usuario";

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("meubackend")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(TEMPO_EXPIRACAO))
                .subject(usuarioEntity.getId().toString())
                .claim("scope", scope)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
