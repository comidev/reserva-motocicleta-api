package comidev.ejercicio01_back.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comidev.ejercicio01_back.dto.MotociclitasResponse;
import comidev.ejercicio01_back.model.Tramo;
import comidev.ejercicio01_back.model.Usuario;
import comidev.ejercicio01_back.repo.TramoRepo;
import comidev.ejercicio01_back.repo.UsuarioRepo;

@Service
public class TramoService {
    @Autowired
    private TramoRepo tramoRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    public boolean init() {
        if (tramoRepo.count() == 0) {
            for (int i = 0; i < 24; i++) {
                tramoRepo.save(new Tramo());
            }
            return true;
        }
        return false;
    }

    public List<MotociclitasResponse> getTramos() {
        List<MotociclitasResponse> motocicletas = tramoRepo.findAll()
                .stream()
                .map(tramo -> getCantidad(tramo))
                .collect(Collectors.toList());
        return motocicletas;
    }

    private MotociclitasResponse getCantidad(Tramo tramo) {
        Set<Usuario> usuarios = tramo.getUsers();
        int cantidad = usuarios == null ? 0 : usuarios.size();
        return new MotociclitasResponse(8 - cantidad);
    }

    public boolean tomar(Long id, String username) {
        Tramo tramoFound = tramoRepo.getById(id);
        Usuario usuarioFound = usuarioRepo.getUsuarioByUsername(username);
        Set<Usuario> usuarios = tramoFound.getUsers();
        if (usuarios.size() < 8) {
            usuarios.add(usuarioFound);
            tramoFound.setUsers(usuarios);
            tramoRepo.save(tramoFound);
            return true;
        }
        return false;
    }

    public boolean devolver(Long id, String username) {
        Tramo tramoFound = tramoRepo.getById(id);
        Usuario usuarioFound = usuarioRepo.getUsuarioByUsername(username);
        Set<Usuario> usuarios = tramoFound.getUsers();
        if (usuarios.size() > 0) {
            usuarios.remove(usuarioFound);
            tramoFound.setUsers(usuarios);
            tramoRepo.save(tramoFound);
            return true;
        }
        return false;
    }
}
