package com.jm.plataforma_educativa.Repository;

import com.jm.plataforma_educativa.Entity.UserSec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserSec,Long> {

    Optional<UserSec> findByUsername(String username);
}
