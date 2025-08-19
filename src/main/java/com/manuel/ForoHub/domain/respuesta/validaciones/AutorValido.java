package com.manuel.ForoHub1.domain.respuesta.validaciones;

import com.manuel.ForoHub1.domain.respuesta.DatosCrearRespuesta;
import com.manuel.ForoHub1.domain.usuario.UsuarioRepository;
import com.manuel.ForoHub1.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutorValido implements ValidadorRespuesta {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void validar(DatosCrearRespuesta datos) {
        if (datos.idAutor() == null || !usuarioRepository.existsById(datos.idAutor())) {
            throw new ValidacionDeIntegridad("Autor no encontrado");
        }
    }
}
