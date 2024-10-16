package com.t3damoscurso.models;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "notas_venta")
public class NotaVenta {

    @Id
    private int id_nota;

    // Relación con el historial de compras
    @ManyToOne
    @JoinColumn(name = "id_historial_compra", insertable = false, updatable = false)
    private HistorialCompra historialCompra;

    private String fechaEmision;
    private double total;  // Monto total registrado en la nota de venta

    // Constructor, getters y setters serán generados por Lombok (@Data)
}
