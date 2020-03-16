package org.movie.http.request;

import javax.ws.rs.DefaultValue;

import org.hibernate.validator.constraints.NotEmpty;

public class MovieDetail {

	private Long id;
	
	@NotEmpty
	private String movieName;
	
	@NotEmpty
	private Integer releaseYear;
	
	@DefaultValue("5F")
	private Float imdbRating;
	private Float duration;
	private String actor;
	private String genre;
	
	@DefaultValue("English")
	private String language;
	
	
	private Boolean isAdult;
	
	public MovieDetail() {

	}

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

	
}
