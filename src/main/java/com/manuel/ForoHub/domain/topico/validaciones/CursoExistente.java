package com.manuel.ForoHub1.domain.topico.validaciones;


import com.manuel.ForoHub1.domain.curso.CursoRepository;
import com.manuel.ForoHub1.domain.topico.DatosCrearTopico;
import com.manuel.ForoHub1.infra.errores.ValidacionDeIntegridad;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CursoExistente implements ValidadorTopico{

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public void validar(DatosCrearTopico datos) {
        if (datos.idCurso() == null) {
            throw new ValidacionDeIntegridad("El topico debe tener un curso asignado");
        }
        var curso = cursoRepository.findById(datos.idCurso());

        if (!curso.isPresent()) {
            throw new ValidationException("El curso al que desea agregar el topico no existe.");
        }
    }
}