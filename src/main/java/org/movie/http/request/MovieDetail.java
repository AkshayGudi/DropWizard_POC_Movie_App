package org.movie.http.request;

public class MovieDetail {

	private Long id;
	private String movieName;
	private Integer releaseYear;
	private Integer imdbRating;

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

	public Integer getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(Integer imdbRating) {
		this.imdbRating = imdbRating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
}
