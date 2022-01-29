package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.Entity.CharacterEntity;
import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;

import java.util.List;

public interface CharacterService {

    CharacterDTO save(CharacterDTO dto, Long idMovie);

    CharacterEntity getEntityById(Long id);

    CharacterDTO getById(Long id);

    List<CharacterBasicDTO> getByFilters(String name, Integer age, Long weight, List<Long> movies);

    CharacterDTO update(Long id, CharacterDTO character);

    void delete(Long id);
}
