package com.example.anotacoes.datasource.repository;

import com.example.anotacoes.datasource.entity.PerfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilRepository extends JpaRepository<PerfilEntity, Long> {
    Optional<PerfilEntity> findByNomePerfil(String nomePerfil);
}
