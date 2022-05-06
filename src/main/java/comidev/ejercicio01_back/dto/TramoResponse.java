package comidev.ejercicio01_back.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TramoResponse {
    private Long id;

    public TramoResponse(Long id) {
        this.id = id;
    }
}
