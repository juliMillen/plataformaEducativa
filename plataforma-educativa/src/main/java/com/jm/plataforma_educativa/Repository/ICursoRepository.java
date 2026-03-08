package com.jm.plataforma_educativa.Repository;

import com.jm.plataforma_educativa.Entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICursoRepository extends JpaRepository<Curso,Long> {
}
