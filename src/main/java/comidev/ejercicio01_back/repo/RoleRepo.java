package comidev.ejercicio01_back.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import comidev.ejercicio01_back.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    public Role getRoleByRoleName(String roleName);
}
