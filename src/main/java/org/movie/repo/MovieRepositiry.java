package org.movie.repo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.movie.domain.dao.Movie;
import org.movie.http.request.MovieDetail;

import io.dropwizard.hibernate.AbstractDAO;

public class MovieRepositiry extends AbstractDAO<Movie> {

	Map<Long, Movie> movieDb = new HashMap<Long, Movie>();

	public MovieRepositiry(SessionFactory sessionFactory) {
		super(sessionFactory);
		movieDb.put(1L, new Movie(1L, "Avengers", 2000, 8));
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

	public List<Movie> getAllMovies() {
		List<Movie> movieList = new ArrayList<Movie>(movieDb.values());
		return movieList;

	}

	public Movie getMovieById(String id) {
		Long movieId = Long.parseLong(id);
		Movie movie = movieDb.get(movieId);

		return movie;

	}

	public Boolean editMovie(MovieDetail movieDetail) {

		Boolean changed = Boolean.TRUE;
		Long movieId = movieDetail.getId();

		if (movieDb.containsKey(movieId)) {

			Movie movie = new Movie();
			movie.setId(movieDetail.getId());
			movie.setImdbRating(movieDetail.getImdbRating());
			movie.setMovieName(movieDetail.getMovieName());
			movie.setYearOfRelease(movieDetail.getReleaseYear());

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
