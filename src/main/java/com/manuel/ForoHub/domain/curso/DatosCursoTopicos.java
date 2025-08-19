package com.manuel.ForoHub1.domain.curso;

import com.manuel.ForoHub1.domain.topico.DatosTopico;
import org.springframework.data.domain.Page;

public record DatosCursoTopicos(DatosCurso curso, Page<DatosTopico> topicos) {

}
