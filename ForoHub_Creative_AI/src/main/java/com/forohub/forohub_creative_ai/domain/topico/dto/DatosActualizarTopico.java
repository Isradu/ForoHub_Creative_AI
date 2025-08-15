package com.forohub.forohub_creative_ai.domain.topico.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DatosActualizarTopico {

    private Long id;
    private String titulo;
    private String mensaje;
    private String autor;
    private String curso;
}
