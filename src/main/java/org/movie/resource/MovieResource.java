package org.movie.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.collections4.CollectionUtils;
import org.movie.domain.dao.Movie;
import org.movie.http.request.MovieDetail;
import org.movie.http.response.GenericResponse;
import org.movie.repo.MovieRepositiry;

@Path("/movie")
@Produces(MediaType.APPLICATION_JSON)
public class MovieResource {

	private MovieRepositiry movieRepository;

	public MovieResource(MovieRepositiry movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public GenericResponse<String> createMovie(MovieDetail movieDetail) {

		System.out.println("In create");

		Movie movie = new Movie();
		movie.setMovieName(movieDetail.getMovieName());
		movie.setImdbRating(movieDetail.getImdbRating());
		movie.setYearOfRelease(movieDetail.getReleaseYear());

		long create = movieRepository.addMovie(movie);

		System.out.println("done " + create);
		return new GenericResponse<String>("Success", 201);

	}

	@GET
	public GenericResponse<List<Movie>> getAllMovies() {
		List<Movie> movieList = movieRepository.getAllMovies();
		int code = CollectionUtils.isEmpty(movieList) ? 204 : 200;
		return new GenericResponse<List<Movie>>(movieList, code);
	}

	@GET
	@Path("{Id}")
	public GenericResponse<Movie> getMovie(@PathParam("Id") String id) {
		Movie movie = movieRepository.getMovieById(id);
		int code = movie == null ? 204 : 200;
		return new GenericResponse<Movie>(movie, code);
	}

	@PUT
	public GenericResponse<String> editMovie(MovieDetail movieDetail) {
		Boolean edited = movieRepository.editMovie(movieDetail);
		GenericResponse<String> response = edited ? new GenericResponse<String>("Success", 200)
				: new GenericResponse<String>("Unchanged", 200);
		return response;
	}

	@DELETE
	@Path("{Id}")
	public GenericResponse<String> deleteMovie(@PathParam("Id") String id) {
		Boolean deleted = movieRepository.deleteMovie(id);
		GenericResponse<String> response = deleted ? new GenericResponse<String>("Success", 200)
				: new GenericResponse<String>("Movie not found", 200);
		return response;
	}

}
