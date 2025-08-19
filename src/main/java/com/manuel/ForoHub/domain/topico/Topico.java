package com.manuel.ForoHub1.domain.topico;

import com.manuel.ForoHub1.domain.curso.Curso;
import com.manuel.ForoHub1.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @Column(unique = true)
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "autor_id")
    private Usuario autorTopico;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "curso_id")
    private Curso curso;

    public Topico(String titulo, String mensaje, Usuario autor, Curso curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = LocalDateTime.now();
        this.status = true;
        this.autorTopico = autor;
        this.curso = curso;
    }

    public void actualirTopico(DatosActualizarTopico datos) {
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
    }

    public void setStatus() {
        this.status = !status;
    }

}
