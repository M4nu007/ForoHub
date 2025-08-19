package com.manuel.ForoHub1.domain.respuesta;

import com.manuel.ForoHub1.domain.respuesta.validaciones.ValidadorRespuesta;
import com.manuel.ForoHub1.domain.topico.DatosTopico;
import com.manuel.ForoHub1.domain.topico.EstadoTopicoRespuesta;
import com.manuel.ForoHub1.domain.topico.TopicoRepository;
import com.manuel.ForoHub1.domain.usuario.UsuarioRepository;
import com.manuel.ForoHub1.infra.errores.ValidacionDeIntegridad;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespuestaService {

    @Autowired
    private RespuestaRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private List<ValidadorRespuesta> validarRespuesta;

    public DatosRespuesta crearRespuesta(DatosCrearRespuesta datos) {

        validarRespuesta.forEach(v -> v.validar(datos));
        var mensaje = datos.mensaje();
        var autor = usuarioRepository.getReferenceById(datos.idAutor());
        var topico = topicoRepository.getReferenceById(datos.idTopico());
        var respuesta = new Respuesta(mensaje, autor, topico);
        repository.save(respuesta);
        return new DatosRespuesta(respuesta);
    }

    public DatosRespuesta editarRespuesta(DatosEditarRespuesta datos) {
        if (datos.mensaje() == null) {
            throw new ValidationException("respuesta no encontrada");
        }
        if (!repository.existsById(datos.idAutor())) {
            throw new ValidacionDeIntegridad("id de respuesta no válido");
        }
        var respuesta = repository.getReferenceById(datos.idAutor());
        respuesta.actualizarMensaje(datos.mensaje());
        return new DatosRespuesta(respuesta);
    }

    public EstadoTopicoRespuesta marcarDesmarcarComoSolucion(Long id) {
        if(id == null) {
            throw new ValidationException("Tiene que digitar el id de la respuesta");
        }

        if(!repository.existsById(id)) {
            throw new ValidacionDeIntegridad("No existe respuesta con id: " + id);
        }

        var respuesta = repository.getReferenceById(id);
        var topico = topicoRepository.getReferenceById(respuesta.getTopico().getId());
        respuesta.setSolucion();
        var solucionado = repository.existsByTopicoAndSolucion(topico, true);
        System.out.println(solucionado);
        if ((solucionado && topico.getStatus() == false) || (!solucionado && topico.getStatus() == true)) {
            topico.setStatus();
        }

        var resultado = new EstadoTopicoRespuesta(new DatosTopico(topico), new DatosRespuesta(respuesta));
        return resultado;
    }

    public void eliminarRespuesta(Long id) {
        if(id == null) {
            throw new ValidationException("Tiene que digitar el id de la respuesta");
        }

        if(!repository.existsById(id)) {
            throw new ValidacionDeIntegridad("No existe respuesta con id: " + id);
        }

        var idTopicoRespuesta = repository.getReferenceById(id).getTopico().getId();
        var topico = topicoRepository.getReferenceById(idTopicoRespuesta);

        repository.deleteById(id);

        var topicoSolucionado = repository.existsByTopicoAndSolucion(topico, true);
        var estadoTopico = topico.getStatus();
        if (estadoTopico && !topicoSolucionado) {
            topico.setStatus();
        }
    }


}
