package comidev.ejercicio01_back.jwt;

import comidev.ejercicio01_back.exceptions.unauthorized.UnauthorizedException;

public class JwtException extends UnauthorizedException {

    private static final String DESCRIPTION = "Error en el Token :(";

    public JwtException(String mensaje) {
        super(DESCRIPTION + ". " + mensaje);
    }
}
