package io.javabrains.moviecatalogservice.resource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.javabrains.moviecatalogservice.model.Catalogitem;
import io.javabrains.moviecatalogservice.model.Movie;
import io.javabrains.moviecatalogservice.model.Rating;
import io.javabrains.moviecatalogservice.model.UserRating;
import io.javabrains.moviecatalogservice.service.MovieInfoService;
import io.javabrains.moviecatalogservice.service.UserRatingInfoService;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	MovieInfoService movieinfosrvice;

	@Autowired
	UserRatingInfoService userRatingInfoService;
	/*
	 * @Autowired private WebClient.Builder webClientBuilder;
	 */

	/*
	 * It can be in any class which has @componet at least
	 * 
	 * @Bean public RestTemplate gettemplate() { return new RestTemplate(); }
	 */

	@RequestMapping("/{userId}")
	public List<Catalogitem> getCatalog(@PathVariable("userId") String userId) {

		// RestTemplate restTemplate = new RestTemplate();
		// restTemplate.getForObject("http://localhost:8084/movies/", Movie.class);

		// GET ALL RELATED MOVIE ID ALONG WITH RATING

		// List<Rating> ratings = Arrays.asList(new Rating("Avangers", 9), new
		// Rating("Transformer", 8));

		// CREATING A GENERIC LIST REFERENCE
		/*
		 * ParameterizedTypeReference<List<Rating>> parameterizedTypeReference = new
		 * ParameterizedTypeReference<List<Rating>>() { };
		 * 
		 * List<Rating> ratings = (List<Rating>)
		 * restTemplate.getForObject("http://localhost:8085/ratingsdata/" + userId,
		 * parameterizedTypeReference.getClass());
		 */

		// UserRating userRating =
		// restTemplate.getForObject("http://localhost:8085/ratingsdata/" +
		// userId,UserRating.class);

		// Calling service discovery getting actual url by passing micro service name
		UserRating userRating = userRatingInfoService.getUserRating(userId);

		// FOR EACH MOVIE ID ,CALL MOVIE INFO SERVICE AND GET DETAILS
		// ratings.stream().map(rating -> new Catalogitem("Avangers", "Avangers
		// Film",4))
		// .collect(Collectors.toList());

		return userRating.getUserRating().stream().map(rating ->

		// Movie movie = restTemplate.getForObject("http://localhost:8084/movies/" +
		// rating.getMovieid(), Movie.class);

		/*
		 * Movie movie=webClientBuilder.build() .get()
		 * .uri("http://localhost:8084/movies/" + rating.getMovieid()) .retrieve()
		 * .bodyToMono(Movie.class) .block();
		 */

		movieinfosrvice.getcatalogItem(rating)

		).collect(Collectors.toList());

		// put all together
		// return Collections.singletonList(new Catalogitem("Avangers", "Marvel Movie",
		// 9));
	}

	/*
	 * No need of it as both method have its own fallback public List<Catalogitem>
	 * getFallBack(@PathVariable("userId") String userId) { return Arrays.asList(new
	 * Catalogitem("No Movies", "", 0)); }
	 */
}
