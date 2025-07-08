package com.design.service;

import org.springframework.data.domain.Sort; 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.design.dto.MovieSearchResponse;
import com.design.entity.Movie;
import com.design.repository.MovieRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {

	private final MovieRepository movieRepo;

    public Page<MovieSearchResponse> searchMovies(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("releaseDate").descending());
        Page<Movie> movies = movieRepo.findAll(pageable);

        return movies.map(movie -> MovieSearchResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .genre(movie.getGenre())
                .language(movie.getLanguage())
                .releaseDate(movie.getReleaseDate())
                .build());
    }
}
