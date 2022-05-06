package comidev.ejercicio01_back.exceptions.forbidden;

public class ForbiddenException extends RuntimeException {

    private static final String DESCRIPTION = "Forbbiden Exception (403)";

    public ForbiddenException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
