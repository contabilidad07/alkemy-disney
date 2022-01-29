package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;

import java.util.List;

public interface MovieService {


    MovieDTO save(MovieDTO dto);

    MovieDTO getById(Long id);

    void addCharacterList(Long idMovie, List<Long> charactersId);

    void addCharacter(Long idMovie, Long idCharacter);

    List<MovieBasicDTO> getByFilters(String title, Long genreId, String order);

    MovieDTO update(Long id, MovieDTO pelicula);

    void delete(Long id);

    void deletedCharacter(Long idMovie, Long idCharacter);
}
