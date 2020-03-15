package org.movie.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.movie.http.request.MovieDetail;

@Path("/testApp/{data}")
@Produces(MediaType.APPLICATION_JSON)
public class TestResource {

	@GET
	public MovieDetail testData(@PathParam("data") String movieName) {
		System.out.println("In Test");
		MovieDetail m = new MovieDetail();
		m.setMovieName(movieName);
		return m;
	}
	
}
