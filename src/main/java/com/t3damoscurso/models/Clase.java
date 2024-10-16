package com.t3damoscurso.models;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "clases")
public class Clase {

    @Id
    private int id_clase;

    private String titulo;
    private String descripcion;
    private String urlVideo;  // Enlace a la clase grabada

    // Relación con el curso
    @ManyToOne
    @JoinColumn(name = "id_curso", insertable = false, updatable = false)
    private Curso curso;

    // Constructor, getters y setters serán generados por Lombok (@Data)
}

