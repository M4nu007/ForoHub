package com.manuel.ForoHub1.domain.respuesta;

public record DatosRespuesta(Long id, String mensaje, Long idTopico, Long idAutor, Boolean solucionado) {

    public DatosRespuesta(Respuesta respuesta) {
        this(respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getTopico().getId(),
                respuesta.getAutorRespuesta().getId(),
                respuesta.getSolucion());
    }
}
