package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")

public class MovieController {
    @Autowired
    private MovieService movieService;


    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movie) {
        MovieDTO movieSalved = this.movieService.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieSalved);
    }


    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getById(@PathVariable Long id) {
        MovieDTO movie = this.movieService.getById(id);
        return ResponseEntity.ok(movie);
    }


    @GetMapping
    public ResponseEntity<List<MovieBasicDTO>> getDetailsByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long genreId,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ){
        List<MovieBasicDTO> movies = this.movieService.getByFilters(title, genreId, order);
        return  ResponseEntity.ok(movies);
    }


    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable Long id, @RequestBody MovieDTO movie) throws ChangeSetPersister.NotFoundException {
        MovieDTO result = this.movieService.update(id, movie);
        return ResponseEntity.ok().body(result);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PostMapping("{idMovie}/character/{idCharacter}")
    public ResponseEntity<MovieDTO> addCharacterToMovie(@PathVariable Long idMovie, @PathVariable Long idCharacter){
        movieService.addCharacter(idMovie, idCharacter );
        return ResponseEntity.ok().body(movieService.getById(idMovie));
    }


    @DeleteMapping("{idMovie}/character/{idCharacter}")
    public ResponseEntity<MovieDTO> deleteCharacterFromMovie(@PathVariable Long idMovie, @PathVariable Long idCharacter){
        movieService.deletedCharacter(idMovie, idCharacter);
        return ResponseEntity.ok().body(movieService.getById(idMovie));
    }
}
