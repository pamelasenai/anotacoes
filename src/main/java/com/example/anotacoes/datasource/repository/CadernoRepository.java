package com.example.anotacoes.datasource.repository;

import com.example.anotacoes.datasource.entity.CadernoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadernoRepository extends JpaRepository<CadernoEntity, Long> {
}
