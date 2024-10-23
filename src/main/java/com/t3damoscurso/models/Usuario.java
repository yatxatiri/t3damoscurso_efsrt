package com.t3damoscurso.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    private int id_usuario;

    private String nombre;
    private String correo;
    private String contrasena;
    private String rol; // Puede ser 'cliente', 'admin', etc.

    // Relación con los planes
    @ManyToOne
    @JoinColumn(name = "id_plan", insertable = false)
    private Plan objPlan;

    // Constructor, getters y setters serán generados por Lombok (@Data)
    private String fecha_registro;
}
