package org.movie.domain.dao;

import org.movie.http.request.MovieDetail;
import org.movie.utility.AbstractComparator;
import org.movie.utility.FloatComparator;
import org.movie.utility.IntegerComparator;
import org.movie.utility.StringComparator;

public class Movie {

	public Movie() {
		// TODO Auto-generated constructor stub
	}

	public Movie(Long id, String movieName, Integer releaseYear, Float imdbRating, Float duration, String actor,
			String genre, String language, Boolean isAdult) {
		this.id = id;
		this.movieName = movieName;
		this.releaseYear = releaseYear;
		this.imdbRating = imdbRating;
		this.duration = duration;
		this.actor = actor;
		this.genre = genre;
		this.language = language;
		this.isAdult = isAdult;
	}

	private Long id;
	private String movieName;
	private Integer releaseYear;
	private Float imdbRating;
	private Float duration;
	private String actor;
	private String genre;
	private String language;
	private Boolean isAdult;

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Float getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(Float imdbRating) {
		this.imdbRating = imdbRating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getDuration() {
		return duration;
	}

	public void setDuration(Float duration) {
		this.duration = duration;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Boolean getIsAdult() {
		return isAdult;
	}

	public void setIsAdult(Boolean isAdult) {
		this.isAdult = isAdult;
	}

	public MovieDetail getMovieDetail() {
		MovieDetail movieDetail = new MovieDetail();
		movieDetail.setId(this.getId());
		movieDetail.setImdbRating(this.getImdbRating());
		movieDetail.setMovieName(this.getMovieName());
		movieDetail.setReleaseYear(this.getReleaseYear());
		movieDetail.setActor(this.getActor());
		movieDetail.setDuration(this.getDuration());
		movieDetail.setGenre(this.getGenre());
		movieDetail.setLanguage(this.getLanguage());
		movieDetail.setIsAdult(this.getIsAdult());
		return movieDetail;
	}

	public AbstractComparator getComparator(String fieldName) {

		AbstractComparator comparator = null;

		switch (fieldName) {

		case "movieName":
		case "actor":
		case "language":
		case "genre":
			comparator = new StringComparator();
			break;
		case "releaseYear":
			comparator = new IntegerComparator();
			break;
		case "imdbRating":
		case "duration":
			comparator = new FloatComparator();
			break;
		
		}

		return comparator;

	}

}
