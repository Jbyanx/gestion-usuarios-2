package com.atl.curso.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_usuarios")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String password;

}
