package com.edu.uniajc.matricula.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class Listas<T> {

    List<T> listaObjetos = new ArrayList<>();

    public void add(T t){
        listaObjetos.add(t);
    }

}
