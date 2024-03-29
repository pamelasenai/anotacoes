package com.example.anotacoes.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cadernos")
public class CadernoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private UsuarioEntity usuario;
}
