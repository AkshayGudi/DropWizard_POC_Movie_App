package org.movie.domain.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Movie")
public class Movie {

	public Movie() {
		// TODO Auto-generated constructor stub
	}

	public Movie(Long id, String movieName, Integer yearOfRelease, Integer imdbRating) {
		this.id = id;
		this.movieName = movieName;
		this.yearOfRelease = yearOfRelease;
		this.imdbRating = imdbRating;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="Movie_Name")
	private String movieName;
	
	@Column(name="Year_Of_Release")
	private Integer yearOfRelease;
	
	@Column(name="IMDB_Rating")
	private Integer imdbRating;

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Integer getYearOfRelease() {
		return yearOfRelease;
	}

	public void setYearOfRelease(Integer yearOfRelease) {
		this.yearOfRelease = yearOfRelease;
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
