package com.manuel.ForoHub1.domain.respuesta.validaciones;

import com.manuel.ForoHub1.domain.respuesta.DatosCrearRespuesta;
import com.manuel.ForoHub1.domain.topico.TopicoRepository;
import com.manuel.ForoHub1.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicoValido implements ValidadorRespuesta {

    @Autowired
    TopicoRepository topicoRepository;

    @Override
    public void validar(DatosCrearRespuesta datos) {
        if (datos.idTopico() == null || !topicoRepository.existsById(datos.idTopico())) {
            throw new ValidacionDeIntegridad("Topico no encontrado");
        }
    }
}