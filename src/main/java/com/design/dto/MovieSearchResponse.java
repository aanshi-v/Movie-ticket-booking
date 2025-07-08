package com.design.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MovieSearchResponse {
	
	private Long id;
    private String title;
    private String genre;
    private String language;
    private LocalDate releaseDate;

}
