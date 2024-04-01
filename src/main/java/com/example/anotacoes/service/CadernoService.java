package com.example.anotacoes.service;

import com.example.anotacoes.controller.dto.request.InserirCadernoRequest;
import com.example.anotacoes.controller.dto.response.CadernoResponse;
import com.example.anotacoes.datasource.entity.CadernoEntity;
import com.example.anotacoes.datasource.entity.UsuarioEntity;
import com.example.anotacoes.datasource.repository.CadernoRepository;
import com.example.anotacoes.datasource.repository.UsuarioRepository;
import com.example.anotacoes.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.ExpressionException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CadernoService {
    private final CadernoRepository cadernoRepository;
    private final UsuarioRepository usuarioRepository;

    public CadernoResponse criar(InserirCadernoRequest caderno, JwtAuthenticationToken token) {
        Long idUsuario = Long.valueOf(token.getName());
        UsuarioEntity usuario = usuarioRepository.findById(idUsuario).orElseThrow(
                () -> new ExpressionException("Erro na autenticação")
        );
        CadernoEntity cadernoFormatado = new CadernoEntity();
        cadernoFormatado.setId(null);
        cadernoFormatado.setUsuario(usuario);
        cadernoFormatado.setNome(caderno.nome());
        CadernoEntity novoCaderno = cadernoRepository.save(cadernoFormatado);
        return new CadernoResponse(novoCaderno.getId(), novoCaderno.getNome());
    }

    public List<CadernoResponse> buscarTodos(JwtAuthenticationToken token) {
        Long idUsuario = Long.valueOf(token.getName());

        List<CadernoEntity> cadernos = cadernoRepository.findAllByUsuarioId(idUsuario);

        List<CadernoResponse> cadernoResponseList = new ArrayList<>();
        cadernos.forEach(c -> cadernoResponseList.add(
            new CadernoResponse(c.getId(), c.getNome())
        ));

        return cadernoResponseList;
    }

    public CadernoResponse buscarPorId(Long id, JwtAuthenticationToken token) throws Exception {
        Long idUsuario = Long.valueOf(token.getName());

        CadernoEntity caderno = cadernoRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Caderno com id " + id + " não encontrado.")
        );

        if (!idUsuario.equals(caderno.getUsuario().getId())){
            throw new Exception("Erro na autenticação");
        };

        return new CadernoResponse(caderno.getId(), caderno.getNome());
    }

    public CadernoResponse alterar(Long id, InserirCadernoRequest caderno, JwtAuthenticationToken token) throws Exception {
        Long idUsuario = Long.valueOf(token.getName());
        UsuarioEntity usuario = usuarioRepository.findById(idUsuario).orElseThrow();
        buscarPorId(id, token);

        CadernoEntity cadernoAlterado = new CadernoEntity();
        cadernoAlterado.setId(id);
        cadernoAlterado.setNome(caderno.nome());
        cadernoAlterado.setUsuario(usuario);

        CadernoEntity cadernoSalvo = cadernoRepository.save(cadernoAlterado);
        return new CadernoResponse(cadernoSalvo.getId(), cadernoSalvo.getNome());
    }

    public void excluir(Long id, JwtAuthenticationToken token) throws Exception {
        buscarPorId(id, token);
        cadernoRepository.deleteById(id);
    }
}
