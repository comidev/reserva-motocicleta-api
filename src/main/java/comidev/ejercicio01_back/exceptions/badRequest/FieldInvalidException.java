package comidev.ejercicio01_back.exceptions.badRequest;

public class FieldInvalidException extends BadRequestException {

    private static final String DESCRIPTION = "Field Invalid :(";

    public FieldInvalidException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
