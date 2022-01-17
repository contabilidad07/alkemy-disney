package com.alkemy.disney.disney.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "película")
@Getter
@Setter
public class PeliculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String imagen;

    private String titulo;

    @Column(name = "fecha_creacion", nullable = false)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaCreacion;

    private Long calificacion;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)  //defino relacion manyToOne (muchos peliculas tienen un genero
    @JoinColumn(name = "genero_id", insertable = false, updatable = false)

    private GeneroEntity genero;    //creo atributo de la clase GeneroEntity llamado genero

    @Column(name = "genero_id", nullable = false) //mi columna continente_id en base de datos no puede ser nula
    private Long generoId;   //va a tener un identificador de continente


    //relacion n a n entre peliculas y personajes

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(     // se usa para anotar la tabla asociada, para relaciones manytomany, crea tabla intermedia
            name = "pelicula_personaje",  //nombre de la tabla intermedia creada
            joinColumns = @JoinColumn(name = "pelicula_id"),// clave externa de la relacion
            inverseJoinColumns = @JoinColumn(name = "personaje_id")) //otra columna de la clave externa,

    private Set<PersonajeEntity> personaje= new HashSet<>(); ; // en colecciones HashSet no permite objetos repetidos

}
