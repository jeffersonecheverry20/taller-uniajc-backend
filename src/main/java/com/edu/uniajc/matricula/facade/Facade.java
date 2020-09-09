package com.edu.uniajc.matricula.facade;

import com.edu.uniajc.matricula.modelo.Respuesta;
import org.springframework.http.ResponseEntity;

public interface Facade {

    public ResponseEntity operation(String json);

}
