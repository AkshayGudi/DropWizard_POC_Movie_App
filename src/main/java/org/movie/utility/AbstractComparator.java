package org.movie.utility;

import java.util.List;

/**
 * An Abstract class to implement comparison for various Data types (Integer, String Float etc)
 * 
 * @author AkshayGudi
 *
 * @param <T>
 * @param <V>
 */
public abstract class AbstractComparator<T,V> {

	public abstract V getValueList(List<String> compareValues);
	public abstract T getFieldValue(Object fieldValue);
	
	public abstract Boolean compare(Object fieldValue, List<String> comparisonValues, String operator);
	
}
