package com.manuel.ForoHub1.controller;


import com.manuel.ForoHub1.domain.topico.DatosActualizarTopico;
import com.manuel.ForoHub1.domain.topico.DatosCrearTopico;
import com.manuel.ForoHub1.domain.topico.DatosTopico;
import com.manuel.ForoHub1.domain.topico.TopicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@RestController
@ResponseBody
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService service;

    @PostMapping
    @Transactional
    public ResponseEntity crearTopico(@RequestBody @Valid DatosCrearTopico datos) {
        var response = service.crearTopico(datos);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datos) {
        var response = service.actualizarTopico(datos);
        return ResponseEntity.ok(response);
    }


    @GetMapping
    public ResponseEntity<Page<DatosTopico>> listarTopicos(@PageableDefault(size = 10)Pageable paginacion) {
        var response = service.listarTopicos(paginacion);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/resueltos")
    public ResponseEntity<Page<DatosTopico>> listarTopicosResueltos(@PageableDefault(size = 10) Pageable paginacion) {
        var response = service.listarTopicosResueltos(paginacion);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/irresolutos")
    public ResponseEntity<Page<DatosTopico>> listarTopicosIrresolutos(@PageableDefault(size = 10) Pageable paginacion) {
        var response = service.listarTopicosIrresolutos(paginacion);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity mostrarTopicoRespuestas(@PageableDefault(size = 10)Pageable paginacion,
                                                  @PathVariable Long id) {
        var response = service.mostrarTopico(id, paginacion);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        var response = service.eliminarTopico(id);
        return ResponseEntity.ok(response);
    }
}
