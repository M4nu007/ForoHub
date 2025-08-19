package com.manuel.ForoHub1.domain.topico;


import com.manuel.ForoHub1.domain.respuesta.DatosRespuesta;
import org.springframework.data.domain.Page;

public record DatosTopicoRespuestas(DatosTopico topico, Page<DatosRespuesta> respuestas) {
}
