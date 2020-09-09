package com.edu.uniajc.matricula.modelo;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class Request <T>{

    private String operacion;
    private T objeto;

}
