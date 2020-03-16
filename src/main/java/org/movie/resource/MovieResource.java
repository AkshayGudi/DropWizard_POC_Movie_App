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

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
public class MovieResource {

	private MovieRepositiry movieRepository;

	public MovieResource(MovieRepositiry movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public GenericResponse<String> createMovie(MovieDetail movieDetail) {

		Movie movie = new Movie();
		movie.setMovieName(movieDetail.getMovieName());
		movie.setImdbRating(movieDetail.getImdbRating());
		movie.setReleaseYear(movieDetail.getReleaseYear());

		movieRepository.addMovie(movie);

		return new GenericResponse<String>("Success", 201);

	}

	@GET
	public GenericResponse<List<MovieDetail>> getAllMovies() {
		List<MovieDetail> movieDetailList = movieRepository.getAllMovies();
		int code = CollectionUtils.isEmpty(movieDetailList) ? 204 : 200;
		return new GenericResponse<List<MovieDetail>>(movieDetailList, code);
	}

	@GET
	@Path("{Id}")
	public GenericResponse<MovieDetail> getMovie(@PathParam("Id") String id) {
		MovieDetail movieDetail = movieRepository.getMovieById(id);
		int code = movieDetail == null ? 204 : 200;
		return new GenericResponse<MovieDetail>(movieDetail, code);
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
