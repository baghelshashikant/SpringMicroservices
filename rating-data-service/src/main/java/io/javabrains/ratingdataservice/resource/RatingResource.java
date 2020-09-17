package io.javabrains.ratingdataservice.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.ratingdataservice.model.Rating;
import io.javabrains.ratingdataservice.model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {

	@RequestMapping("{userId}")
	public UserRating getrating(@PathVariable("userId") String userId) {
		List<Rating> ratings = Arrays.asList(new Rating("Avangers", 9), new Rating("Tranformer", 8));
		UserRating userRating = new UserRating();
		userRating.setUserRating(ratings);
		userRating.setUserId(userId);
		return userRating;
	}
}
