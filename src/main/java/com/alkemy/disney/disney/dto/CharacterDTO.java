package com.alkemy.disney.disney.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CharacterDTO {
    private Long id;

    private String image;

    private String name;

    private Long age;

    private Long weight;

    private String story;

    private List<MovieDTO> movies;


}
