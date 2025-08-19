package com.manuel.ForoHub1.domain.respuesta.validaciones;

import com.manuel.ForoHub1.domain.respuesta.DatosCrearRespuesta;
import com.manuel.ForoHub1.domain.respuesta.RespuestaRepository;
import com.manuel.ForoHub1.domain.topico.TopicoRepository;
import com.manuel.ForoHub1.domain.usuario.UsuarioRepository;
import com.manuel.ForoHub1.infra.errores.ValidacionDeIntegridad;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RespuestaValida implements ValidadorRespuesta {

    @Autowired
    RespuestaRepository respuestaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TopicoRepository topicoRepository;

    @Override
    public void validar(DatosCrearRespuesta datos) {
        if (datos.mensaje() == null) {
            throw new ValidacionDeIntegridad("mensaje no encontrado");
        }

        if (respuestaRepository.existsByTopicoAndMensajeAndAutorRespuesta(
                topicoRepository.getReferenceById(datos.idTopico()),
                datos.mensaje(),
                usuarioRepository.getReferenceById(datos.idAutor()))) {
            throw new ValidationException("Ya existe una respuesta igual para el topico");
        }
    }
}
