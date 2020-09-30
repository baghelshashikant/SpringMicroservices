package io.javabrains.moviecatalogservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.javabrains.moviecatalogservice.model.Catalogitem;
import io.javabrains.moviecatalogservice.model.Movie;
import io.javabrains.moviecatalogservice.model.Rating;

@Service
public class MovieInfoService {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getcatalogItemFallBack")
	public Catalogitem getcatalogItem(Rating rating) {
		System.out.println("getcatalogItem rating : "+rating);
		Movie movie = restTemplate.getForObject("http://movie-info-service/movie/" + rating.getMovieid(), Movie.class);
		System.out.println("movie : "+movie);
		return new Catalogitem(movie.getName(), movie.getDesc(), rating.getRating());
	}

	public Catalogitem getcatalogItemFallBack(Rating rating) {
		System.out.println("Movie name not found");
		return new Catalogitem("Movie name not found", "", rating.getRating());
	}
}
