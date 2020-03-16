package org.movie.utility;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.collections4.CollectionUtils;
import org.movie.domain.dao.Movie;

/**
 * This is a utility class for Simulating Db functionality of filtering and
 * getting Movies based on various attributes
 * 
 * @author AkshayGudi
 *
 */
public class CompareUtil {

	public static List<Movie> filterAll(List<Movie> movies, MultivaluedMap<String, String> filters) {

		List<Movie> finalMovie = new ArrayList<Movie>();
		for (Movie movie : movies) {
			if (filter(movie, filters)) {
				finalMovie.add(movie);
			}
		}

		return finalMovie;

	}

	public static Boolean filter(Movie m, MultivaluedMap<String, String> filters) {

		Boolean passed = Boolean.TRUE;

		for (String key : filters.keySet()) {

			String operator = null;
			String[] splitKey = key.split("(:)");
			String fieldName = splitKey[0];
			if (splitKey.length > 1) {
				operator = splitKey[1];
			}

			List<String> values = filters.get(key);
			if (!compare(fieldName, m, values, operator)) {
				passed = Boolean.FALSE;
				break;
			}

		}

		return passed;
	}

	public static Boolean compare(String fieldName, Movie movie, List<String> values, String operator) {

		Boolean dataExists = Boolean.TRUE;
		Object fieldValue = null;
		Boolean passed = Boolean.TRUE;

		Field field;
		try {
			field = movie.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			fieldValue = field.get(movie);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			dataExists = Boolean.FALSE;
		}

		if (!CollectionUtils.isEmpty(values) && dataExists) {
			passed = movie.getComparator(fieldName).compare(fieldValue, values, operator);
		}

		return passed;
	}

}
