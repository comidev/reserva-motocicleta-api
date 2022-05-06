package comidev.ejercicio01_back.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequest {
    private String username;
    private String password;

    public UsuarioRequest(String username) {
        this.username = username;
    }

    public UsuarioRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
