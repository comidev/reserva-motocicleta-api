package comidev.ejercicio01_back.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import comidev.ejercicio01_back.model.Usuario;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Long> {
    public Usuario getUsuarioByUsername(String username);

    public Optional<Usuario> findByUsername(String username);

    public Boolean existsByUsername(String username);
}
