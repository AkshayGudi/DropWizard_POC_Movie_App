package org.movie.configuration;

import org.movie.repo.MovieRepositiry;
import org.movie.resource.HealthCheckResource;
import org.movie.resource.MovieResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class MovieApplication extends Application<MovieConfiguration> {

	public static void main(String[] args) throws Exception {
		new MovieApplication().run("server", "movie.yml");
	}

	@Override
	public void run(MovieConfiguration configuration, Environment environment) throws Exception {

		final HealthCheckResource healthCheck = new HealthCheckResource("TEST");
		environment.healthChecks().register("movie-app", healthCheck);

		MovieRepositiry movieRepository = new MovieRepositiry();
		environment.jersey().register(new MovieResource(movieRepository));

	}

}
