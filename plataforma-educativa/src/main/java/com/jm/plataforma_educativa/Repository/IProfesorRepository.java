package com.jm.plataforma_educativa.Repository;

import com.jm.plataforma_educativa.Entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfesorRepository extends JpaRepository<Profesor,Long> {
}
