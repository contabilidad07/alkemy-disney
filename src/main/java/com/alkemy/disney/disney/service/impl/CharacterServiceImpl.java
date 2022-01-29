package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.Entity.CharacterEntity;
import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.CharacterFiltersDTO;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.CharacterMapper;
import com.alkemy.disney.disney.repository.CharacterRepository;
import com.alkemy.disney.disney.repository.specifications.CharacterSpecification;
import com.alkemy.disney.disney.service.CharacterService;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CharacterServiceImpl implements CharacterService {
    @Autowired
    private CharacterSpecification characterSpecification;

    @Autowired
    private MovieService movieService;

    @Autowired
    private CharacterMapper characterMapper;

    @Autowired
    private CharacterRepository characterRepository;


    public CharacterDTO save(CharacterDTO dto, Long idMovie) {
        CharacterEntity entity = this.characterMapper.characterDTO2Entity(dto);
        CharacterEntity entitySaved = this.characterRepository.save(entity);
        movieService.addCharacter(idMovie, entitySaved.getId());
        CharacterDTO result = this.characterMapper.characterEntity2DTO(entitySaved, true);
        return result;
    }


    public CharacterDTO getById(Long id) {
        Optional<CharacterEntity> entity = this.characterRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Id character not found");
        }
        CharacterDTO characterDTO = this.characterMapper.characterEntity2DTO(entity.get(), true);
        return characterDTO;
    }


    public List<CharacterBasicDTO> getByFilters(String name, Integer age, Long weight, List<Long> movies) {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, weight, movies);
        List<CharacterEntity> entities = this.characterRepository.findAll(this.characterSpecification.getByFilters(filtersDTO));
        List<CharacterBasicDTO> dtos = this.characterMapper.characterEntityList2BasicDTOList(entities);
        return dtos;
    }


    public void delete (Long id){
        Optional<CharacterEntity> entity = this.characterRepository.findById(id);
        if (!entity.isPresent()){
            throw new ParamNotFound("Id character not found");
        }
        this.characterRepository.deleteById(id);
    }


    public CharacterDTO update (Long id, CharacterDTO character) {
        Optional<CharacterEntity> entity = this.characterRepository.findById(id);
        if (!entity.isPresent()){
            throw new ParamNotFound( "Id character not found");
        }
        this.characterMapper.characterEntityRefreshValues(entity.get(), character);
        CharacterEntity entitySaved = this.characterRepository.save(entity.get());
        CharacterDTO result = this.characterMapper.characterEntity2DTO(entitySaved, true);
        return result;
    }


    public CharacterEntity getEntityById(Long id) {
        CharacterEntity characterEntity = characterRepository.getById(id);
        if ( characterEntity == null){
            throw new ParamNotFound("Id character not found");
        }
        return characterEntity;
    }




}
