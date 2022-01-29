package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacterFiltersDTO {
    private String name;
    private Integer age;
    private Long weight;
    private List<Long> movies;

    public CharacterFiltersDTO(String name, Integer age, Long weight, List<Long> movies) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.movies = movies;
    }

}
