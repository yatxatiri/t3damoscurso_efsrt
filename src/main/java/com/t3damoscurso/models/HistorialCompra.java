package com.t3damoscurso.models;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "historial_compras")
public class HistorialCompra {

    @Id
    private int id_compra;

    // Relación con usuarios
    @ManyToOne
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
    private Usuario usuario;

    // Relación con cursos (pueden ser cursos o planes adquiridos)
    @ManyToOne
    @JoinColumn(name = "id_curso", insertable = false, updatable = false)
    private Curso curso;

    // Si la compra es de un plan
    @ManyToOne
    @JoinColumn(name = "id_plan", insertable = false, updatable = false)
    private Plan plan;

    private String fechaCompra;
    private double total;  // Monto pagado por el curso o plan

    // Constructor, getters y setters serán generados por Lombok (@Data)
}
