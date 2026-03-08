package com.jm.plataforma_educativa.Repository;

import com.jm.plataforma_educativa.Entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstudianteRepository extends JpaRepository<Estudiante,Long> {
}
