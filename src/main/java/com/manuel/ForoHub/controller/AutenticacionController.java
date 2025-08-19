package com.manuel.ForoHub1.controller;

//import ff.api.forohub.domain.usuario.DatosAutenticacionUsuario;
//import ff.api.forohub.domain.usuario.Usuario;
//import ff.api.forohub.infra.security.DatosJWTToken;
//import ff.api.forohub.infra.security.TokenService;
import com.manuel.ForoHub1.domain.usuario.DatosAutenticacionUsuario;
import com.manuel.ForoHub1.domain.usuario.Usuario;
import com.manuel.ForoHub1.infra.security.DatosJWTToken;
import com.manuel.ForoHub1.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity autenticacionUsuario(@RequestBody @Valid DatosAutenticacionUsuario datos) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(datos.email(), datos.contrasena());
            var autenticacion = manager.authenticate(authToken);

            var JWTtoken = tokenService.generarToken((Usuario) autenticacion.getPrincipal());

            return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
