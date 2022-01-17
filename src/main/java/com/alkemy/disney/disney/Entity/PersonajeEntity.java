package com.alkemy.disney.disney.Entity;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;

import java.time.LocalDate;


@Entity
@Table(name = "personaje")
@Getter
@Setter
public class PersonajeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;

    private String nombre;
    private Long edad;
    private Long peso;
    private String historia;

    @ManyToMany(mappedBy = "personaje", cascade = CascadeType.ALL)
    private List<PeliculaEntity> pelicula = new ArrayList<>();


}
