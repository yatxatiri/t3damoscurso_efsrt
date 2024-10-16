package com.t3damoscurso.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    private int id_curso;

    private String nombre;
    private String descripcion;

    // Relación con la categoría del curso (ejemplo: 'Ciencias de la Computación')
    private String categoria;

    // Relación con profesores
    @ManyToOne
    @JoinColumn(name = "id_profesor", insertable = false, updatable = false)
    private Profesor profesor;

    private int es_gratuito;
    // Constructor, getters y setters serán generados por Lombok (@Data)
}
