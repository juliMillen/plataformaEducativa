package com.jm.plataforma_educativa.Service;

import com.jm.plataforma_educativa.Entity.Permission;
import com.jm.plataforma_educativa.Repository.iPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {

    @Autowired
    private iPermissionRepository permissionRepository;

    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    public Optional<Permission> findById(Long id) {
        return permissionRepository.findById(id);
    }

    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    public void update(Permission permission) {
        permissionRepository.save(permission);
    }

    public void deleteById(Long id) {
        permissionRepository.deleteById(id);
    }
}
