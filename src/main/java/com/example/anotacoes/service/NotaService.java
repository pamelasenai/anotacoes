package com.example.anotacoes.service;

import com.example.anotacoes.controller.dto.request.InserirNotaRequest;
import com.example.anotacoes.controller.dto.response.NotaResponse;
import com.example.anotacoes.datasource.entity.CadernoEntity;
import com.example.anotacoes.datasource.entity.NotaEntity;
import com.example.anotacoes.datasource.entity.UsuarioEntity;
import com.example.anotacoes.datasource.repository.CadernoRepository;
import com.example.anotacoes.datasource.repository.NotaRepository;
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
public class NotaService {
    private final NotaRepository notaRepository;
    private final UsuarioRepository usuarioRepository;
    private final CadernoRepository cadernoRepository;

    public NotaResponse criar(InserirNotaRequest nota, JwtAuthenticationToken token) {
        Long idUsuario = Long.valueOf(token.getName());
        UsuarioEntity usuario = usuarioRepository.findById(idUsuario).orElseThrow(
                () -> new ExpressionException("Erro na autenticação")
        );
        CadernoEntity caderno = cadernoRepository.findById(nota.caderno()).orElseThrow(
                () -> new ExpressionException("Erro ao buscar caderno.")
        );
        NotaEntity notaFormatada = new NotaEntity();
        notaFormatada.setId(null);
        notaFormatada.setUsuario(usuario);
        notaFormatada.setTitulo(nota.titulo());
        notaFormatada.setConteudo(nota.conteudo());
        notaFormatada.setCaderno(caderno);
        NotaEntity novoNota = notaRepository.save(notaFormatada);
        return new NotaResponse(novoNota.getId(), novoNota.getTitulo(), novoNota.getConteudo(), novoNota.getCaderno().getId());
    }

    public List<NotaResponse> buscarTodos(JwtAuthenticationToken token) {
        Long idUsuario = Long.valueOf(token.getName());

        List<NotaEntity> notas = notaRepository.findAllByUsuarioId(idUsuario);

        List<NotaResponse> notaResponseList = new ArrayList<>();
        notas.forEach(n -> notaResponseList.add(
            new NotaResponse(n.getId(), n.getTitulo(), n.getConteudo(), n.getCaderno().getId())
        ));

        return notaResponseList;
    }

    public NotaResponse buscarPorId(Long id, JwtAuthenticationToken token) throws Exception {
        Long idUsuario = Long.valueOf(token.getName());

        NotaEntity nota = notaRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Nota com id " + id + " não encontrada.")
        );

        if (!idUsuario.equals(nota.getUsuario().getId())){
            throw new Exception("Erro na autenticação");
        };

        return new NotaResponse(nota.getId(), nota.getTitulo(), nota.getConteudo(), nota.getCaderno().getId());
    }

    public NotaResponse alterar(Long id, InserirNotaRequest nota, JwtAuthenticationToken token) throws Exception {
        Long idUsuario = Long.valueOf(token.getName());
        UsuarioEntity usuario = usuarioRepository.findById(idUsuario).orElseThrow(
                () -> new ExpressionException("Erro na autenticação")
        );
        CadernoEntity caderno = cadernoRepository.findById(nota.caderno()).orElseThrow(
                () -> new ExpressionException("Erro ao buscar caderno.")
        );
        buscarPorId(id, token);

        NotaEntity notaAlterado = new NotaEntity();
        notaAlterado.setId(id);
        notaAlterado.setTitulo(nota.titulo());
        notaAlterado.setConteudo(nota.conteudo());
        notaAlterado.setCaderno(caderno);
        notaAlterado.setUsuario(usuario);

        NotaEntity novaNota = notaRepository.save(notaAlterado);
        return new NotaResponse(
                novaNota.getId(),
                novaNota.getTitulo(),
                novaNota.getConteudo(),
                novaNota.getCaderno().getId()
        );
    }

    public void excluir(Long id, JwtAuthenticationToken token) throws Exception {
        buscarPorId(id, token);
        notaRepository.deleteById(id);
    }
}
