package com.t3damoscurso.models;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "profesores")
public class Profesor {

    @Id
    private int id_profesor;

    private String nombre;
    private String especialidad;

    // Constructor, getters y setters serán generados por Lombok (@Data)
}
