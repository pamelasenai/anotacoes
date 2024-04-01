package com.example.anotacoes.datasource.repository;

import com.example.anotacoes.datasource.entity.NotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<NotaEntity, Long> {
    List<NotaEntity> findAllByUsuarioId(Long idUsuario);
}
