package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
 public class MovieBasicDTO {
    private Long id;

    private String image;

    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

}
