package com.t3damoscurso.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.t3damoscurso.models.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

}
