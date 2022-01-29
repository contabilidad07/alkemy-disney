package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.Entity.CharacterEntity;
import com.alkemy.disney.disney.Entity.MovieEntity;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class MovieMapper {

    @Autowired
    private CharacterMapper characterMapper;


    public MovieEntity movieDTO2Entity(MovieDTO dto){
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setImage(dto.getImage());
        movieEntity.setTitle(dto.getTitle());
        movieEntity.setCreationDate(dto.getCreationDate());
        movieEntity.setQualification(dto.getQualification());
        movieEntity.setGenreId(dto.getGenreId());

        Set<CharacterEntity> characterEntities = this.characterMapper.characterDTOList2EntitySet(dto.getCharacters());
        movieEntity.setCharacters(characterEntities);

        return movieEntity;
    }


    public MovieDTO movieEntity2DTO(MovieEntity entity, boolean loadCharacter){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(entity.getId());
        movieDTO.setImage(entity.getImage());
        movieDTO.setTitle(entity.getTitle());
        movieDTO.setCreationDate(entity.getCreationDate());
        movieDTO.setQualification(entity.getQualification());
        movieDTO.setGenreId(entity.getGenreId());

        if(loadCharacter) {
            List<CharacterDTO> characterDTOS = this.characterMapper.characterEntitySet2DTOList(entity.getCharacters(), false);
            movieDTO.setCharacters(characterDTOS);
        }
        return movieDTO;
    }


    public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> entities, boolean loadCharacter){
        List<MovieDTO> dtos = new ArrayList<>();
        for (MovieEntity entity : entities) {
            dtos.add(this.movieEntity2DTO(entity, loadCharacter));
        }
        return dtos;
    }


    public void movieEntityRefreshValues(MovieEntity entity, MovieDTO movieDTO) {
        entity.setImage(movieDTO.getImage());
        entity.setTitle(movieDTO.getTitle());
        entity.setCreationDate(movieDTO.getCreationDate());
        entity.setQualification(movieDTO.getQualification());
        entity.setGenreId(movieDTO.getGenreId());
        List<CharacterEntity> characterEntities = this.characterMapper.characterDTOList2EntityList(movieDTO.getCharacters());
        for (CharacterEntity character : characterEntities) {
            entity.getCharacters().add(character);
        }
    }


    public List<MovieBasicDTO> movieEntityList2BasicDTOList(List<MovieEntity> entities){
        List<MovieBasicDTO> dtos = new ArrayList<>();
        MovieBasicDTO basicDTO;
        for (MovieEntity entity : entities){
            basicDTO = new MovieBasicDTO();
            basicDTO.setId(entity.getId());
            basicDTO.setImage(entity.getImage());
            basicDTO.setTitle(entity.getTitle());
            basicDTO.setCreationDate(entity.getCreationDate());
            dtos.add(basicDTO);
        }
        return dtos;
    }


}
