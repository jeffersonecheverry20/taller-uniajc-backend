package com.edu.uniajc.matricula.exception;

public class MatriculaAcademicaException extends RuntimeException {

    private String code;

    public MatriculaAcademicaException(String code, String mensaje){
        super(mensaje);
        this.code = code;
    }

    public MatriculaAcademicaException(String code, String mensaje, Throwable causa){
        super(mensaje, causa);
        this.code = code;
    }

    public MatriculaAcademicaException(String code, Throwable causa){
        super(causa);
        this.code = code;
    }
}
