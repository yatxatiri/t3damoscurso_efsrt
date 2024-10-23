package com.t3damoscurso.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.t3damoscurso.models.Plan;

public interface IPlanRepository extends JpaRepository<Plan, Integer> {

}
