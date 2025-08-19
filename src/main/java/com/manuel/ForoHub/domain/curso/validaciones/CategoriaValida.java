package com.manuel.ForoHub1.domain.curso.validaciones;

import com.manuel.ForoHub1.domain.curso.DatosCrearCurso;
import com.manuel.ForoHub1.infra.errores.ValidacionDeIntegridad;
import org.springframework.stereotype.Component;

@Component
public class CategoriaValida implements ValidadorCurso{

    @Override
    public void validar(DatosCrearCurso datos) {
        if (datos.categoria() == null) {
            throw new ValidacionDeIntegridad("Debe asignarle una categoria al curso");
        }
    }
}