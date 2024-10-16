package com.t3damoscurso.models;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class UsuarioPlanId implements Serializable {
	private int id_usuario;
    private int id_plan;
}
