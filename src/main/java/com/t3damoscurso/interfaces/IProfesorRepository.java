package com.t3damoscurso.interfaces;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.t3damoscurso.models.Profesor;

@Repository
public interface IProfesorRepository extends JpaRepository<Profesor, Integer> {
}

