package comidev.ejercicio01_back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comidev.ejercicio01_back.dto.TokenResponse;
import comidev.ejercicio01_back.dto.TramoResponse;
import comidev.ejercicio01_back.dto.UsuarioRequest;
import comidev.ejercicio01_back.jwt.JwtService;
import comidev.ejercicio01_back.service.UsuarioService;

@RestController
@RequestMapping("/users")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody UsuarioRequest usuario) {
        String username = usuario.getUsername();
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(
                username,
                usuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = jwtService.createToken(username);
        return ResponseEntity.ok(new TokenResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> register(@RequestBody UsuarioRequest usuario) {
        String prevPassword = usuario.getPassword();
        String cryptPassword = passwordEncoder.encode(prevPassword);
        usuario.setPassword(cryptPassword);
        boolean registered = usuarioService.addUsuario(usuario);
        return registered ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @GetMapping("/tramos")
    public ResponseEntity<List<TramoResponse>> getTramos(@RequestHeader("Authorization") String token) {
        String username = jwtService.username(token);
        List<TramoResponse> tramos = usuarioService.getTramosByUsername(username);
        return tramos != null ? ResponseEntity.ok(tramos) : ResponseEntity.noContent().build();
    }
}
