package com.jm.plataforma_educativa.Repository;

import com.jm.plataforma_educativa.Entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iPermissionRepository extends JpaRepository<Permission,Long> {
}
