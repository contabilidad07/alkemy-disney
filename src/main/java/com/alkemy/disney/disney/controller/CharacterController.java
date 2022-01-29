package com.alkemy.disney.disney.controller;


import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;


    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestParam Long idMovie,@RequestBody CharacterDTO character) {
        CharacterDTO characterSaved = characterService.save(character, idMovie);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterSaved);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getById(@PathVariable Long id) {
        CharacterDTO character = this.characterService.getById(id);
        return ResponseEntity.ok().body(character);
    }


    @GetMapping
    public ResponseEntity<List<CharacterBasicDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Long weight,
            @RequestParam(required = false) List<Long> movies){
        List<CharacterBasicDTO> characters = this.characterService.getByFilters(name,age, weight, movies);
        return ResponseEntity.ok(characters);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> update(@PathVariable Long id, @RequestBody CharacterDTO character) throws ChangeSetPersister.NotFoundException {
        CharacterDTO result = this.characterService.update(id, character);
        return ResponseEntity.ok().body(result);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
