package com.t3damoscurso.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "planes")
public class Plan {

    @Id
    private int id_plan;

    private String nombre;  // Ejemplo: 'Experto', 'Dúo', 'Corporativo'
    private double precio;  // Precio mensual o total
    private int maxUsuarios; // Máximo número de usuarios permitidos

    // Constructor, getters y setters serán generados por Lombok (@Data)
}
