package comidev.ejercicio01_back.exceptions;

import lombok.Getter;

@Getter
public class ErrorMessage {
    private String exception;
    private String message;
    private String path;

    public ErrorMessage(Exception exception, String path) {
        this.exception = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
        this.path = path;
    }

    @Override
    public String toString() {
        return "ErrorMessage{ "
                + "exception : " + exception + '\'' + ", "
                + "message : " + message + '\'' + ", "
                + "path : " + path + '\''
                + "  }";
    }
}
