package org.movie.configuration;

import org.movie.domain.dao.Movie;
import org.movie.repo.MovieRepositiry;
import org.movie.resource.HealthCheckResource;
import org.movie.resource.MovieResource;
import org.movie.resource.TestResource;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MovieApplication extends Application<MovieConfiguration> {

	public static void main(String[] args) throws Exception {
		new MovieApplication().run("server", "movie.yml");
	}

	//Hibernate config
	private HibernateBundle<MovieConfiguration> hibernateBundle = new HibernateBundle<MovieConfiguration>(Movie.class) {

		public DataSourceFactory getDataSourceFactory(MovieConfiguration configuration) {
			return configuration.getDataSourceFactory();
		}

	};

	@Override
	public void initialize(final Bootstrap<MovieConfiguration> bootstrap) {

		bootstrap.addBundle(hibernateBundle);
	}

	@Override
	public void run(MovieConfiguration configuration, Environment environment) throws Exception {

		environment.jersey().register(new TestResource());

		final HealthCheckResource healthCheck = new HealthCheckResource("TEST");
		environment.healthChecks().register("template", healthCheck);

		MovieRepositiry movieRepository = new MovieRepositiry(hibernateBundle.getSessionFactory());
		environment.jersey().register(new MovieResource(movieRepository));

	}

}
