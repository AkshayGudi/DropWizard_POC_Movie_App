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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.collections4.CollectionUtils;
import org.movie.domain.dao.Movie;
import org.movie.http.request.MovieDetail;
import org.movie.http.response.GenericResponse;
import org.movie.repo.MovieRepositiry;

/**
 * Rest API for Movie Resource
 * 
 * @author AkshayGudi
 *
 */
@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
public class MovieResource {

	private MovieRepositiry movieRepository;

	public MovieResource(MovieRepositiry movieRepository) {
		this.movieRepository = movieRepository;
	}

	/**
	 * method to add movies
	 * 
	 * @param movieDetail
	 * @return
	 */
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public GenericResponse<String> createMovie(MovieDetail movieDetail) {

		Movie movie = new Movie();
		movie.setMovieName(movieDetail.getMovieName());
		movie.setImdbRating(movieDetail.getImdbRating());
		movie.setReleaseYear(movieDetail.getReleaseYear());
		movie.setActor(movieDetail.getActor());
		movie.setDuration(movieDetail.getDuration());
		movie.setGenre(movieDetail.getGenre());
		movie.setLanguage(movieDetail.getLanguage());
		movie.setIsAdult(movieDetail.getIsAdult());
		
		movieRepository.addMovie(movie);

		return new GenericResponse<String>("Success", 201);

	}

	/**
	 * Method for fetch all movies and also to filter based in various attributes
	 * 
	 * @param uriInfo
	 * @return
	 */
	@GET
	public GenericResponse<List<MovieDetail>> getAllMovies(@Context UriInfo uriInfo) {
		MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();
		List<MovieDetail> movieDetailList = movieRepository.getAllMovies(queryParameters);
		int code = CollectionUtils.isEmpty(movieDetailList) ? 204 : 200;
		return new GenericResponse<List<MovieDetail>>(movieDetailList, code);
	}

	/**
	 * Getting Movie based on ID
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("{Id}")
	public GenericResponse<MovieDetail> getMovie(@PathParam("Id") String id) {
		MovieDetail movieDetail = movieRepository.getMovieById(id);
		int code = movieDetail == null ? 204 : 200;
		return new GenericResponse<MovieDetail>(movieDetail, code);
	}

	
	/**
	 * Modifying existing Movie Element
	 * 
	 * @param movieDetail
	 * @return
	 */
	@PUT
	public GenericResponse<String> editMovie(MovieDetail movieDetail) {
		Boolean edited = movieRepository.editMovie(movieDetail);
		GenericResponse<String> response = edited ? new GenericResponse<String>("Success", 200)
				: new GenericResponse<String>("Unchanged", 200);
		return response;
	}

	/**
	 * Deleting movie based on ID (unique)
	 * 
	 * @param id
	 * @return
	 */
	@DELETE
	@Path("{Id}")
	public GenericResponse<String> deleteMovie(@PathParam("Id") String id) {
		Boolean deleted = movieRepository.deleteMovie(id);
		GenericResponse<String> response = deleted ? new GenericResponse<String>("Success", 200)
				: new GenericResponse<String>("Movie not found", 200);
		return response;
	}
	
}
