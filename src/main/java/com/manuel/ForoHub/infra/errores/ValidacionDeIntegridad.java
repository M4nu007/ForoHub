package com.manuel.ForoHub1.infra.errores;

public class ValidacionDeIntegridad extends RuntimeException{
    public ValidacionDeIntegridad(String s) {
        super(s);
    }
}
