package com.t3damoscurso.models;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "usuario_plan")
public class UsuarioPlan {

	@EmbeddedId
	private UsuarioPlanId id;

    // Relación con usuarios
    @ManyToOne
    @MapsId("id_usuario")  // Mapea el campo id_usuario de UsuarioPlanId
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
    private Usuario usuario;

    // Relación con planes
    @ManyToOne
    @MapsId("id_plan")
    @JoinColumn(name = "id_plan", insertable = false, updatable = false)
    private Plan plan;

    private String fechaInicio;
    private String fechaFin;  // Fecha en la que el plan expira

    // Constructor, getters y setters serán generados por Lombok (@Data)
}

