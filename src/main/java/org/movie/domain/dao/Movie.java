package org.movie.domain.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.movie.http.request.MovieDetail;

@Entity
@Table(name = "Movie")
public class Movie {

	public Movie() {
		// TODO Auto-generated constructor stub
	}

	public Movie(Long id, String movieName, Integer releaseYear, Float imdbRating) {
		this.id = id;
		this.movieName = movieName;
		this.releaseYear = releaseYear;
		this.imdbRating = imdbRating;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "Movie_Name")
	private String movieName;

	@Column(name = "Release_Year")
	private Integer releaseYear;

	@Column(name = "IMDB_Rating")
	private Float imdbRating;

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

	public MovieDetail getMovieDetail() {
		MovieDetail movieDetail = new MovieDetail();
		movieDetail.setId(this.getId());
		movieDetail.setImdbRating(this.getImdbRating());
		movieDetail.setMovieName(this.getMovieName());
		movieDetail.setReleaseYear(this.getReleaseYear());
		return movieDetail;
	}

}
