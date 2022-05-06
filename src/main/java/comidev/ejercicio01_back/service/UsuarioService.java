package comidev.ejercicio01_back.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import comidev.ejercicio01_back.dto.TramoResponse;
import comidev.ejercicio01_back.dto.UsuarioRequest;
import comidev.ejercicio01_back.model.Role;
import comidev.ejercicio01_back.model.Usuario;
import comidev.ejercicio01_back.repo.RoleRepo;
import comidev.ejercicio01_back.repo.UsuarioRepo;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioFound = usuarioRepo.findByUsername(username);
        if (usuarioFound.isPresent()) {
            Usuario usuario = usuarioFound.get();
            List<SimpleGrantedAuthority> roles = usuario.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                    .toList();
            return new User(usuario.getUsername(), usuario.getPassword(), roles);
        }
        return null;
    }

    public boolean addUsuario(UsuarioRequest usuario) {
        boolean exists = usuarioRepo.existsByUsername(usuario.getUsername());
        if (!exists) {
            Set<Role> roles = Set.of(roleRepo.getRoleByRoleName("ROLE_USER"));
            usuarioRepo.save(new Usuario(usuario, roles));
            return true;
        }
        return false;
    }

    public List<TramoResponse> getTramosByUsername(String username) {
        Usuario usuarioFound = usuarioRepo.getUsuarioByUsername(username);
        return usuarioFound == null
                ? null
                : usuarioFound.getTramos()
                        .stream()
                        .map(tramo -> new TramoResponse(tramo.getId()))
                        .collect(Collectors.toList());
    }

}
