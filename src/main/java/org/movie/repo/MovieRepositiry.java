package org.movie.repo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.collections4.CollectionUtils;
import org.movie.domain.dao.Movie;
import org.movie.http.request.MovieDetail;
import org.movie.utility.CompareUtil;

public class MovieRepositiry {

	Map<Long, Movie> movieDb = new HashMap<Long, Movie>();

	public MovieRepositiry() {
		movieDb.put(1L, new Movie(1L, "Titanic", 1997, 7.8F,2.5F,"Kate Winslet","Romance","English",Boolean.FALSE));
	}

	public long addMovie(Movie movie) {

		Optional<Long> maxId = movieDb.keySet().stream().max(new Comparator<Long>() {

			public int compare(Long o1, Long o2) {
				return (o1 - o2) > 0 ? 1 : (o1 - o2) < 0 ? -1 : 0;
			}
		});

		Long newId = maxId.get() + 1;

		movie.setId(newId);
		movieDb.put(newId, movie);
		return newId;
	}

	/*
	 * public List<MovieDetail> getAllMovies() { List<Movie> movieList = new
	 * ArrayList<Movie>(movieDb.values());
	 * 
	 * List<MovieDetail> movieDetailList = new ArrayList<MovieDetail>(); if
	 * (!CollectionUtils.isEmpty(movieList)) { for (Movie movie : movieList) {
	 * movieDetailList.add(movie.getMovieDetail()); } }
	 * 
	 * return movieDetailList;
	 * 
	 * }
	 */
	
	public List<MovieDetail> getAllMovies(MultivaluedMap<String, String> queryParameters) {
		List<Movie> movieList = new ArrayList<Movie>(movieDb.values());

		if(queryParameters!=null && !queryParameters.isEmpty()) {
			movieList = CompareUtil.filterAll(movieList, queryParameters);
		}
		
		List<MovieDetail> movieDetailList = new ArrayList<MovieDetail>();
		if (!CollectionUtils.isEmpty(movieList)) {
			for (Movie movie : movieList) {
				movieDetailList.add(movie.getMovieDetail());
			}
		}

		return movieDetailList;

	}
	

	public MovieDetail getMovieById(String id) {
		Long movieId = Long.parseLong(id);
		Movie movie = movieDb.get(movieId);
		MovieDetail movieDetail = movie != null ? movie.getMovieDetail() : null;
		return movieDetail;

	}

	public Boolean editMovie(MovieDetail movieDetail) {

		Boolean changed = Boolean.TRUE;
		Long movieId = movieDetail.getId();

		if (movieDb.containsKey(movieId)) {

			Movie movie = new Movie();
			movie.setId(movieDetail.getId());
			movie.setImdbRating(movieDetail.getImdbRating());
			movie.setMovieName(movieDetail.getMovieName());
			movie.setReleaseYear(movieDetail.getReleaseYear());
			movie.setActor(movieDetail.getActor());
			movie.setDuration(movieDetail.getDuration());
			movie.setGenre(movieDetail.getGenre());
			movie.setLanguage(movieDetail.getLanguage());
			movie.setIsAdult(movieDetail.getIsAdult());

			movieDb.put(movieId, movie);
		} else {
			changed = Boolean.FALSE;
		}

		return changed;
	}

	public Boolean deleteMovie(String id) {
		Boolean changed = Boolean.TRUE;

		Long movieId = Long.parseLong(id);
		if (movieDb.containsKey(movieId)) {
			movieDb.remove(movieId);
		} else {
			changed = Boolean.FALSE;
		}

		return changed;

	}

}
