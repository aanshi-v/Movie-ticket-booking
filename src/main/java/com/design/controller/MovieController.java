package com.design.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.design.dto.MovieSearchResponse;
import com.design.service.MovieService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {
	
	 private final MovieService movieService;

	    @GetMapping("/search")
	    public Page<MovieSearchResponse> search(@RequestParam int page, @RequestParam int size) {
	        return movieService.searchMovies(page, size);
	    }

}
