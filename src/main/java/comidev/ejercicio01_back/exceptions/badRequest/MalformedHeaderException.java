package comidev.ejercicio01_back.exceptions.badRequest;

public class MalformedHeaderException extends BadRequestException {

    private static final String DESCRIPTION = "Field MalFormed :(";

    public MalformedHeaderException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
