package io.javabrains.movieinfoservice.resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.movieinfoservice.model.Movie;

@RestController
@RequestMapping("/movie")
public class MovieResource {

	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieid) {
		return new Movie(movieid, movieid +" Flim", "Marvel Movies");

	}
}
