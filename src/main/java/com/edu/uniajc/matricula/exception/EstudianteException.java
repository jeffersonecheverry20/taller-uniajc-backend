package com.edu.uniajc.matricula.exception;

public class EstudianteException extends RuntimeException {

    private String code;

    public EstudianteException(String code, String mensaje){
        super(mensaje);
        this.code = code;
    }

    public EstudianteException(String code, String mensaje, Throwable causa){
        super(mensaje, causa);
        this.code = code;
    }

    public EstudianteException(String code, Throwable causa){
        super(causa);
        this.code = code;
    }

}
