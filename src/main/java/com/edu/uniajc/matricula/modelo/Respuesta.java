package com.edu.uniajc.matricula.modelo;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class Respuesta <T> {

    public String codigo;
    public String mensaje;
    public T objeto;

}
