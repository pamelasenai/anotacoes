package com.example.anotacoes.datasource.repository;

import com.example.anotacoes.datasource.entity.NotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<NotaEntity, Long> {
}
