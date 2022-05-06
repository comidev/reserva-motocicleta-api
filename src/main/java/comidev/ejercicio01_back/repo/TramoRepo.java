package comidev.ejercicio01_back.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import comidev.ejercicio01_back.model.Tramo;

@Repository
public interface TramoRepo extends JpaRepository<Tramo, Long> {

}
