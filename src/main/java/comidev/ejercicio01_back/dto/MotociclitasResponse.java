package comidev.ejercicio01_back.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MotociclitasResponse {
    private int cantidad;

    public MotociclitasResponse(int cantidad) {
        this.cantidad = cantidad;
    }
}
