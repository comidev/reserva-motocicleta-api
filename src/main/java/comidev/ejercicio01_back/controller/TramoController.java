package comidev.ejercicio01_back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comidev.ejercicio01_back.dto.MotociclitasResponse;
import comidev.ejercicio01_back.jwt.JwtService;
import comidev.ejercicio01_back.service.TramoService;

@RestController
@RequestMapping("/tramo")
public class TramoController {

    @Autowired
    private TramoService tramoService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/init")
    public ResponseEntity<HttpStatus> init() {
        boolean init = tramoService.init();
        return (init ? ResponseEntity.ok()
                : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<MotociclitasResponse>> getTramos() {
        List<MotociclitasResponse> tramos = tramoService.getTramos();
        return ResponseEntity.ok(tramos);
    }

    @GetMapping("/tomar/{id}")
    public ResponseEntity<Boolean> tomar(@PathVariable("id") Long id, @RequestHeader("Authorization") String token) {
        String username = jwtService.username(token);
        boolean response = tramoService.tomar(id, username);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/devolver/{id}")
    public ResponseEntity<Boolean> devolver(@PathVariable("id") Long id, @RequestHeader("Authorization") String token) {
        String username = jwtService.username(token);
        boolean response = tramoService.devolver(id, username);
        return ResponseEntity.ok(response);
    }

}
