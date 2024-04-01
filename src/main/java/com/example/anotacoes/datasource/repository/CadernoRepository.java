package com.example.anotacoes.datasource.repository;

import com.example.anotacoes.datasource.entity.CadernoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CadernoRepository extends JpaRepository<CadernoEntity, Long> {
    List<CadernoEntity> findAllByUsuarioId(Long idUsuario);
}
